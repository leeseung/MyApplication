package com.cockandroid.myapplication;


import android.app.Activity;
import android.content.Context;
//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("메인 액티비티");



        Button btnNewActivity = (Button)findViewById(R.id.btnNewActivity);
        btnNewActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText editNum1 = (EditText) findViewById(R.id.edtNum1);
                EditText editNum2 = (EditText) findViewById(R.id.edtNum2);
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("Num1", Integer.parseInt(editNum1.getText().toString()));
                intent.putExtra("Num2", Integer.parseInt(editNum2.getText().toString()));
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
