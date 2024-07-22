package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class EventActivity extends AppCompatActivity{
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        findViews();
        setOnLinsteners();
    }

    private void setOnLinsteners() {
        Linstener linstener = new Linstener();
        Linstener2 linstener2 = new Linstener2();
        //点击事件
        btn1.setOnClickListener(linstener);
        //触摸事件 触摸事件先于点击事件发生
    }

    private void findViews() {
        btn1 = findViewById(R.id.btn1);
    }

    class Linstener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btn1:
                    Log.i("btn", "bnt1 OnClick");
                    break;
            }
        }
    }

    class Linstener2 implements View.OnTouchListener{
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            Log.i("btn", "btn1 onTouch");
            switch (event.getAction()){
                case 1:
                    Log.i("btn", "bnt1 Up");
                    break;
                case 0:
                    Log.i("btn", "bnt1 Down");
                    break;
            }
            //返回是否成功解决事件
            return true;
        }
    }
}