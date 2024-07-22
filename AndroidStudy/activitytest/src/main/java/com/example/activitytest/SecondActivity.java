package com.example.activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView tv_view;

    private Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        findViews();

        obtainIntent();

        setListeners();
    }

    private void setListeners() {
        Listener listener = new Listener();
        btn_back.setOnClickListener(listener);
    }

    private void obtainIntent() {
        Intent intent = getIntent();
        String message = intent.getStringExtra("msg");
        tv_view.setText(message);
    }

    private void findViews() {
        tv_view = findViewById(R.id.tv_view);
        btn_back = findViewById(R.id.btn_back);
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