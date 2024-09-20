package com.example.broadcast.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MyBroadcastReceiver3 extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        //向下游传递额外信息
        Bundle bundle = intent.getExtras();
        bundle.putString("name", "MyBroadcastReceiver3" + " 喜多郁代");
        setResultExtras(bundle);
        Log.i(TAG, "MyBroadcastReceiver3" + " onReceive: 收到静态广播消息");
    }
}
