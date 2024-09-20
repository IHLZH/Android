package com.example.broadcast.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.broadcast.R;
import com.example.broadcast.util.TextUtil;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent != null){
                String extra = intent.getStringExtra(TextUtil.KEY_RESULT);
                Toast.makeText(context, "接收到的结果为：" + extra, Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: ");

        //发送广播
        sendTheBroadcast();
        //注册接收者
        registerTheReceiver();
        setListeners();
    }

    private void setListeners() {
        Button btn_goto_myactivity = findViewById(R.id.btn_goto_myactivity);
        btn_goto_myactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MyActivity2.class);
                startActivity(intent);
            }
        });
    }

    private void sendTheBroadcast() {
        Button btn_send = findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(TextUtil.ACTION_SEND_RESULT);
                intent.putExtra(TextUtil.KEY_RESULT, "这是广播1");
                sendBroadcast(intent);
            }
        });
    }

    private void registerTheReceiver() {
        //注册过滤器
        //过滤器 设置action 表示只接受包含该action的广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(TextUtil.ACTION_SEND_RESULT);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
        unregisterReceiver(broadcastReceiver);
    }
}