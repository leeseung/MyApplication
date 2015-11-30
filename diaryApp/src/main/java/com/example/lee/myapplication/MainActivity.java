package com.example.lee.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    TextView textView;   // 날짜를 나타내는 텍스트뷰
    Button saveButton;   // 저장을 하는 버튼
    EditText editDiary;  // 일기내용을 보여주는 에디트텍스트
    String filename;     // 파일명을 담당할 문자열형 변수
    int nCheck = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("일기장 어플리케이션");
        //dp = (DatePicker) findViewById(R.id.datePicker1);
        textView = (TextView) findViewById(R.id.textView);
        editDiary = (EditText) findViewById(R.id.edtInfor);
        saveButton = (Button) findViewById(R.id.btnSave);


        String strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();  //StorageDirectory의 절대경로를 가져와서 문자형으로 저장
        final File mydiary = new File(strSDpath + "/mydiary"); //mydiary를 추가하기위한  파일객체 생성
        mydiary.mkdir(); //디렉토리 생성

        Calendar cal = Calendar.getInstance();  // 현재 날짜를 받아와서 저장
        int cYar = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);


        filename = Integer.toString(cYar) + "년" + Integer.toString(cMonth + 1) + "월" //초기에 현재날짜의 년,월,일을 임의의 형태의 파일이름으로 저장
                + Integer.toString(cDay) + "일.txt";
        String str1 = readDiary(filename);  // 파일이름을 읽어옴
        editDiary.setText(str1);
        saveButton.setEnabled(true);//읽어온 파일의 내용을 에디트 텍스트에 set함
        textView.setText(Integer.toString(cYar) + "년" + Integer.toString(cMonth + 1) + "월" //텍스트뷰에 임의의 형태의 날짜 이름으로 set하여 보여줌
                + Integer.toString(cDay) + "일.txt");


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   // 저장버튼 입력시에
                try {
                    String temp = mydiary + "/" + filename; //추가한 sd카드 아래 mydiary 디렉토리에 만든 파일이름을 temp에 저장
                    FileOutputStream outFs = new FileOutputStream(temp); //파일 출력스트림을 생성
                    String str = editDiary.getText().toString(); //문자열 형으로 변환
                    outFs.write(str.getBytes()); //출력스트림에 읽은 파일 저장
                    outFs.close(); //출력스트림 닫기
                    Toast.makeText(getApplicationContext(), filename + "이 저장됨", 0).show(); //파일이 저장됨을 출력
                    //dp.setVisibility(View.VISIBLE);
                } catch (IOException e) {
                    editDiary.setHint("일기 없음"); //파일이 저장되지 않았을때 처리
                }
            }
        });
        textView.setOnTouchListener(new View.OnTouchListener() { //텍스트뷰가 터치되었을때

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                DatePickerView(v);  //날짜를 선택받고 처리하는 메서드
                return true;
            }
        });

    }
    public void DatePickerView(View v) { //날짜를 선택받고 처리하는 메서드
        Calendar cal = Calendar.getInstance(); //초기에 현재날짜의 년,월,일을 정수형으로 저장
        int cYar = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this, listener, cYar, cMonth, cDay); // 초기 DatePickerDialog가 나타낼 날짜
        dialog.show();
        // DatePickerDialog를 보여줌


    }
    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() { //DatePickerDialog가 처리한 리스너

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) { //날짜가 선택되어졌을때
            if (nCheck == 0) {            //이는 .안드로이드 OS버전문제로 onDateSet이 2번호출되어지는 것에 대한 방어코드로 완벽한 해결책을 찾지못하여서
                nCheck = 1;                    // 임시적으로 두번째 DatePickerDialog는 적용되지않게 만 처리하였습니다.
                filename = Integer.toString(year) + "년" + Integer.toString(monthOfYear + 1) + "월"          //DatePickerDialog 에서 선택한 년,월,일을
                        + Integer.toString(dayOfMonth) + "일.txt";                                            //fIlename에 저장
                String str = readDiary(filename);                          //저장한 이름의 파일이 존재하면 읽어와서 문자열형 변수에저장
                editDiary.setText(str);                                    // 읽은파일의 내용을 에디트텍스트에 나타냄
                textView.setText(Integer.toString(year) + "년" + Integer.toString(monthOfYear + 1) + "월" //텍스트뷰에 선택한 날짜를 보여줌
                        + Integer.toString(dayOfMonth) + "일.txt");
            } else {
                nCheck = 0;    //두번째 DatePickerDialog는 설정하여도 바로리턴시킴
                return;
            }
        }
    };

    String readDiary(String fName) {   // 파일을 읽는메서드

        String diaryStr = null;
        FileInputStream inFs;
        try {
            inFs = new FileInputStream("/sdcard/mydiary/" + fName);  // 파일입력스트림에 경로를 새로만든 mydiary디렉토리 아래 파일로지정
            byte[] txt = new byte[inFs.available()];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
            editDiary.setText(diaryStr);
            saveButton.setText("수정하기");
        } catch (IOException e) {
            editDiary.setHint("일기 없음");
            saveButton.setText("새로 저장");
        }
        return diaryStr;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {                      //옵션메뉴를 구현하기위한 메서드
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu); //옵션메뉴를 기술한 xml을 불러옴
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final String strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        final File mydiary = new File(strSDpath + "/mydiary");
        switch (item.getItemId()) {     //클릭한 옵션메뉴가 어떤것인지 판단
            case R.id.reRead:         // 다시읽기를 선택했다면
                String str = readDiary(filename); //해당 파일이름을 읽어와 저장
                editDiary.setText(str); //에디트 텍스트에 set함
                return true;
            case R.id.Delete:    //파일 삭제를 선택했다면
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder( //AlertDialog를 생성
                        this);
                // AlertDialog 셋팅
                alertDialogBuilder
                        .setMessage(textView.getText() + "를 삭제하겠습니까?") //다이얼로그의 메세지
                        .setCancelable(false)
                        .setPositiveButton("삭제",
                                new DialogInterface.OnClickListener() { //삭제를 누를시
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        // 프로그램을 종료한다
                                        String temp = mydiary + "/" + filename; //파임경로와 이름을 temp에 저장
                                        File file = new File(temp); //파일 객체생성
                                        file.delete();  //파일 삭제
                                        Toast.makeText(getApplicationContext(), "해당파일을 삭제하였습니다", Toast.LENGTH_LONG).show(); //토스트 메세지 보냄
                                        editDiary.setText("");
                                        editDiary.setHint("일기 없음");
                                    }
                                })
                        .setNegativeButton("취소",             //취소를 누를경우
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        Toast.makeText(getApplicationContext(), "삭제를 취소하였습니다.", Toast.LENGTH_LONG).show(); //토스트 메세지 보냄
                                        // 다이얼로그를 취소한다
                                        dialog.cancel();
                                    }
                                });
                // 다이얼로그 생성
                AlertDialog alertDialog = alertDialogBuilder.create();
                // 다이얼로그 보여주기
                alertDialog.show();
                break;
            case R.id.fontSizeBig:     // 글씨를 크게변경
                editDiary.setTextSize(30);
                Toast.makeText(getApplicationContext(), "글씨크기 ( 크게 )", Toast.LENGTH_LONG).show();
                return true;
            case R.id.fontSizeNormal:  //글씨를 중간으로변경
                editDiary.setTextSize(20);
                Toast.makeText(getApplicationContext(), "글씨크기 ( 중간 )", Toast.LENGTH_LONG).show();
                return true;
            case R.id.fontSizeSmall: //글씨를 작게변경
                editDiary.setTextSize(10);
                Toast.makeText(getApplicationContext(), "글씨크기 ( 작게 )", Toast.LENGTH_LONG).show();
                return true;
        }

        return false;   //옵션메뉴중 선택되지 않으면 false리턴
    }
}

