package com.example.broadcast.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MyBroadcastReceiver2 extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = getResultExtras(true);//true表示如果传递过来的数据为空，则返回一个空的Map
        String name = bundle.getString("name");
        Log.i(TAG, "发件人: " + name + " " + "收件人: " + "MyBroadcastReceiver2");
        Log.i(TAG, "MyBroadcastReceiver2" + " onReceive: 收到静态广播消息");
    }
}
