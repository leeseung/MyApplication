package com.example.lee.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("그리드뷰 영화포스터 ");


        final GridView gv = (GridView) findViewById(R.id.gridView1);
        MyGridAdapter gAdapter = new MyGridAdapter(this);
        gv.setAdapter(gAdapter);


    }

    public class MyGridAdapter extends BaseAdapter {
        Context context;

        public MyGridAdapter(Context c) {
            context = c;
        }

        @Override
        public int getCount() {
            return posterID.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        Integer[] posterID = {
                R.drawable.mov01, R.drawable.mov02, R.drawable.mov03, R.drawable.mov04
                , R.drawable.mov05, R.drawable.mov06, R.drawable.mov07, R.drawable.mov08,
                R.drawable.mov09, R.drawable.mov10
        };

        String[] movieName = {                      // 영화제목 배열
                "하류인생", "올드보이", "I am", "특종", "쎄시봉"
                , "Great", "트랜스머신", "악마를 보았다"
                , "아마데우스", "트루맨"
        };



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View gridView;
            ImageView imageView;
            LayoutInflater inflater = getLayoutInflater();
            gridView= inflater.inflate(R.layout.dialog,null);
            imageView = new ImageView(context);
            imageView = (ImageView)gridView.findViewById(R.id.ivPoster);
            TextView textView = (TextView) gridView.findViewById(R.id.tvPoster);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5, 5, 5, 5);
            textView.setPadding(1,1,1,1);
            imageView.setImageResource(posterID[position]);

            textView.setText(movieName[position]);


            final int pos = position;
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    View dialogView = (View) View.inflate(MainActivity.this, R.layout.content_main, null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    ImageView ivPoster = (ImageView) dialogView.findViewById(R.id.ivBig);
                    ivPoster.setImageResource(posterID[pos]);

                    dlg.setTitle(movieName[pos]);
                    dlg.setIcon(R.drawable.ic_launcher);
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기", null);
                    dlg.show();
                }
            });

            return gridView;
        }


}

}

