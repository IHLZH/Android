package com.example.broadcast.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.broadcast.R;
import com.example.broadcast.broadcastreceiver.MyBroadcastReceiver;
import com.example.broadcast.broadcastreceiver.MyBroadcastReceiver2;
import com.example.broadcast.broadcastreceiver.MyBroadcastReceiver3;
import com.example.broadcast.broadcastreceiver.MyLocalBroadcastReceiver;
import com.example.broadcast.util.TextUtil;

import org.w3c.dom.Text;

public class MyActivity2 extends AppCompatActivity {
    private MyBroadcastReceiver myBroadcastReceiver;
    private MyBroadcastReceiver2 myBroadcastReceiver2;
    private MyBroadcastReceiver3 myBroadcastReceiver3;
    private MyLocalBroadcastReceiver myLocalBroadcastReceiver;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my2);

        //注册
        registerMyReceiver();

        //发送广播
        sendTheBroadcast();
    }

    private void registerMyReceiver() {
        myBroadcastReceiver = new MyBroadcastReceiver();
        myBroadcastReceiver2 = new MyBroadcastReceiver2();
        myBroadcastReceiver3 = new MyBroadcastReceiver3();
        myLocalBroadcastReceiver = new MyLocalBroadcastReceiver();

        IntentFilter intentFilter = new IntentFilter(TextUtil.ACTION_SEND_RESULT);
        IntentFilter intentFilter2 = new IntentFilter(TextUtil.ACTION_SEND_RESULT);
        IntentFilter intentFilter3 = new IntentFilter(TextUtil.ACTION_SEND_RESULT);

        intentFilter.setPriority(100);
        intentFilter2.setPriority(101);
        intentFilter3.setPriority(102);

        registerReceiver(myBroadcastReceiver, intentFilter);
        registerReceiver(myBroadcastReceiver2, intentFilter2);
        registerReceiver(myBroadcastReceiver3, intentFilter3);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter1 = new IntentFilter(TextUtil.ACTION_SEND_LOACL_RESULT);
        localBroadcastManager.registerReceiver(myLocalBroadcastReceiver, intentFilter1);
    }

    private void sendTheBroadcast() {
        Button btn_send2 = findViewById(R.id.btn_send2);
        btn_send2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(TextUtil.ACTION_SEND_RESULT);
                intent.setAction(TextUtil.ACTION_SEND_LOACL_RESULT);
                intent.putExtra(TextUtil.KEY_RESULT, "这是广播2");
                //sendBroadcast(intent);
                //sendOrderedBroadcast(intent, null);
                localBroadcastManager.sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(myLocalBroadcastReceiver);
    }
}