package com.example.activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {
    private Button btn_back;

    private TextView tv_student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        findViews();

        setListeners();

        obtainIntent();
    }

    private void obtainIntent() {
        //1.获取intent
        Intent intent = getIntent();
        //2.获取Bundle
        Bundle bundle = intent.getBundleExtra("msg");
        //3.获取学生对象
        Student student = (Student)bundle.getSerializable("stu");
        tv_student.setText(student.toString());
    }

    private void setListeners() {
        Listener listener = new Listener();
        btn_back.setOnClickListener(listener);
    }

    private void findViews() {
        btn_back = findViewById(R.id.btn_back);
        tv_student = findViewById(R.id.tv_student);
    }

    class Listener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_back:
                   backToFirstActivity();
                    break;
            }
        }
    }

    private void backToFirstActivity() {
        Intent intent = new Intent();
        intent.setClass(this, FirstActivity.class);
        startActivity(intent);
    }
}