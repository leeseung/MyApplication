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

class SecondActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Second 액티비티");



        Intent inIntent = getIntent();
        final int hapValue = inIntent.getIntExtra("Num1",0)
                + inIntent.getIntExtra("Num2",0);
        Button btnReturn = (Button) findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(),MainActivity.class);
                outIntent.putExtra("Hap",hapValue);
                setResult(RESULT_OK,outIntent);
                finish();
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