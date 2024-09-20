package com.example.broadcast.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyLocalBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyLocalBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: 收到了本地广播");
    }
}
