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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;



class MainActivity extends Activity {
    EditText editNum1,editNum2;
    RadioGroup Group;
    RadioButton plus,minus,multi,div;
    Button btnac;
    String ViewReslut;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("메인 액티비티");
        editNum1 = (EditText) findViewById(R.id.edtNum1);
        editNum2 = (EditText) findViewById(R.id.edtNum2);
        Group = (RadioGroup)findViewById(R.id.group);
        btnac = (Button)findViewById(R.id.btnNewActivity);


        btnac.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                android.content.Intent intent = new android.content.Intent(getApplicationContext(), SecondActivity.class);

                if (editNum1.getText().toString().isEmpty() || editNum2.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.putExtra("Num1", Integer.parseInt(editNum1.getText().toString()));
                intent.putExtra("Num2", Integer.parseInt(editNum2.getText().toString()));
                intent.putExtra("Type", ViewReslut);
                startActivityForResult(intent, 0);
            }
        });
        Group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (Group.getCheckedRadioButtonId()) {
                    case R.id.plus:
                        ViewReslut = "plus";
                        break;
                    case R.id.minus:
                        ViewReslut= "minus";
                        break;
                    case R.id.multi:
                        ViewReslut = "mul";
                        break;
                    case R.id.div:
                        ViewReslut = "div";
                        break;
                }
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        int value = data.getIntExtra("Value", 0);
        if(resultCode == 100)
            Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();
        else if(resultCode == RESULT_OK)
            Toast.makeText(getApplicationContext(), "합계 : " + value, Toast.LENGTH_SHORT).show();
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
