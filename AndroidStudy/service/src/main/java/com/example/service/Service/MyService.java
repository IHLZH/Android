package com.example.service.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("MyService", "onBind: ");
        return null;
    }

    @Override
    public void onCreate() {
        Log.i("MyService", "onCreate: ");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("MyService", "onStartCommand: ");
        String keyStop = intent.getStringExtra("key_stop");
        if(TextUtils.equals(keyStop, "stop")){
            stopSelf();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i("MyService", "onDestroy: ");
        super.onDestroy();
    }
}
