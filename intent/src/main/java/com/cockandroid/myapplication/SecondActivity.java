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

public class SecondActivity extends Activity {
    int Viewvalue;
    Button btnReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        setTitle("Second Activity");
        final int value = 0;
        final boolean divByZero = false;

        Intent inIntent = getIntent();
        switch (inIntent.getStringExtra("ViewReslut")) {
            case "plus":
                Viewvalue = inIntent.getIntExtra("Num1", 0) + inIntent.getIntExtra("Num2", 0);
                break;
            case "minus":
                Viewvalue = inIntent.getIntExtra("Num1", 0) - inIntent.getIntExtra("Num2", 0);
                break;
            case "mul":
                Viewvalue = inIntent.getIntExtra("Num1", 0) * inIntent.getIntExtra("Num2", 0);
                break;
            case "div":
                if (inIntent.getIntExtra("Num2", 0) == 0) {
                    Viewvalue = 0;
                } else
                    Viewvalue = inIntent.getIntExtra("Num1", 0) / inIntent.getIntExtra("Num2", 0);
                break;

        }


        btnReturn = (Button)findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
                outIntent.putExtra("result", Viewvalue);
                if (divByZero) setResult(100, outIntent);
                else setResult(RESULT_OK, outIntent);

                finish();
            }
        });



    }
}