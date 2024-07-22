package com.example.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ThreadHomeWorkActivity extends AppCompatActivity {
    private ProgressBar pb_bar;
    private TextView tv_text;
    private Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_home_work);

        findViews();

        setLinstener();
    }

    private void setLinstener() {
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadResource();
            }
        });
    }

    private void loadResource() {
        Handler handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what){
                    case 114514:
                        Integer Progress = (Integer) msg.obj;
                        tv_text.setText("资源加载进度为：" + Progress + "%");
                        pb_bar.setProgress(Progress);
                        break;
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(Integer time = 100; time >= 0; time--){
                    if(time % 10 == 0){
                        Integer Progress = 100 - time;
                        Message message = handler.obtainMessage();
                        message.obj = Progress;
                        message.what = 114514;
                        handler.sendMessage(message);
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    private void findViews() {
        pb_bar = findViewById(R.id.pb_bar);
        tv_text = findViewById(R.id.tv_text);
        btn_start = findViewById(R.id.btn_start);
    }
}