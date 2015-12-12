package com.cockandroid.myapplication;


import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    myDBHelper myhelper;
    EditText editName, editNumber, editNameResult, editNumberResult;
    Button btnInit,btnInsert,btnSelect,btnDelete,btnUpdate;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("가수 그룹 관리 DB");

        editName = (EditText) findViewById(R.id.edtName);
        editNumber = (EditText) findViewById(R.id.edtNum);
        editNameResult = (EditText) findViewById(R.id.edtNameResult);
        editNumberResult = (EditText) findViewById(R.id.edtNumberResult);

        btnInit = (Button)findViewById(R.id.btnInit);
        btnInsert = (Button)findViewById(R.id.btnInsert);
        btnSelect =(Button)findViewById(R.id.btnQuery);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        btnUpdate =(Button)findViewById(R.id.btnUpdates);

        myhelper = new myDBHelper(this);
        btnInit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sqlDB = myhelper.getWritableDatabase();
                myhelper.onUpgrade(sqlDB, 1, 2);
                sqlDB.close();

            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                sqlDB = myhelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO groupTBL VALUES ('"
                        + editName.getText().toString() + "' , "
                        + editNumber.getText().toString() + ");");
                sqlDB.close();
                Toast.makeText(getApplicationContext(),"입력됨",Toast.LENGTH_SHORT).show();
                Query();
            }

        });
        btnSelect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Query();
            }

        });


        btnDelete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                sqlDB = myhelper.getWritableDatabase();
                sqlDB.execSQL("DELETE FROM groupTBL WHERE gName = '"
                        + editName.getText().toString() + "';");
                sqlDB.close();
                Toast.makeText(getApplicationContext(),"삭제됨",Toast.LENGTH_SHORT).show();
                Query();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                sqlDB = myhelper.getWritableDatabase();
                sqlDB.execSQL("UPDATE groupTBL SET gNumber = " + editNumber.getText().toString()
                        + " WHERE gName = '" + editName.getText().toString() + "';");
                sqlDB.close();
                Toast.makeText(getApplicationContext(),"수정됨",Toast.LENGTH_SHORT).show();
                Query();
            }
        });
    }

    public void Query() {
        sqlDB = myhelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT * FROM groupTBL;", null);

        String strNames = "그룹 이름" + "\r\n" + "----------" + "\r\n";
        String strNums = "인원" + "\r\n" + "----------" + "\r\n";

        while(cursor.moveToNext()) {
            strNames += cursor.getString(0) + "\r\n";
            strNums += cursor.getString(1) + "\r\n";
        }

        editNameResult.setText(strNames);
        editNumberResult.setText(strNums);

        cursor.close();
        sqlDB.close();
    }

    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "groupDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO
            db.execSQL("CREATE TABLE groupTBL ( gName CHAR(20) PRIMARY KEY, gNumber INTEGER);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);
        }
    }



}

