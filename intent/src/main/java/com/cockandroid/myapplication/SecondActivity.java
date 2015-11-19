package com.cockandroid.myapplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends Activity {
    Button btnresult;
    int value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        setTitle("세컨드 액티비티");

        Intent Intent = getIntent(); // intent 받아옴!!

        String re = Intent.getStringExtra("re"); // 라디오버튼으로 클릭한거 가져와서 계산하는 소스
        if(re.equals("+")){
            value = Intent.getIntExtra("Num1", 0) + Intent.getIntExtra("Num2", 0);
        }else if(re.equals("-")){
            value = Intent.getIntExtra("Num1", 0) - Intent.getIntExtra("Num2", 0);
        }else if(re.equals("*")){
            value = Intent.getIntExtra("Num1", 0) * Intent.getIntExtra("Num2", 0);
        }else if(re.equals("/")){
            value = Intent.getIntExtra("Num1", 0) / Intent.getIntExtra("Num2", 0);
        }

        btnresult = (Button)findViewById(R.id.btnback);
        btnresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
                outIntent.putExtra("result", value); //계산 받아서 메인액티비티로 돌아가면 토스트창으로 뿌려줌
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });
    }
}
