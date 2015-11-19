package com.cockandroid.myapplication;
import android.view.Menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends Activity {
    EditText edtNum1, edtNum2;
    RadioButton plus, minus, multi, div;
    Button btnresult;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("계산기");

        edtNum1 = (EditText)findViewById(R.id.edtnum1);
        edtNum2 = (EditText)findViewById(R.id.edtnum2);

        plus = (RadioButton)findViewById(R.id.plus);
        minus = (RadioButton)findViewById(R.id.minus);
        multi = (RadioButton)findViewById(R.id.multiplication);
        div = (RadioButton)findViewById(R.id.division);

        btnresult = (Button)findViewById(R.id.btnresult);

        btnresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("Num1", Integer.parseInt(edtNum1.getText().toString())); // 인텐트를 생성하고,
                intent.putExtra("Num2", Integer.parseInt(edtNum2.getText().toString())); // 에디트 넘1,넘2에 두 값을 넣었다.

                // 라디오버튼 계산부분
                if(plus.isChecked()){
                    intent.putExtra("re", "+");
                }else if(minus.isChecked()){
                    intent.putExtra("re", "-");
                }else if(multi.isChecked()){
                    intent.putExtra("re", "*");
                }else if(div.isChecked()){
                    intent.putExtra("re", "/");
                }

                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){ //setResult()에서 보낸 값이 RESULT_OK면 인텐트에서 돌려받은 값을 토스트로 뿌려줌
            result = data.getIntExtra("result", 0);
            Toast.makeText(getApplicationContext(), "결과 : " + result, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "오류입니다", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}