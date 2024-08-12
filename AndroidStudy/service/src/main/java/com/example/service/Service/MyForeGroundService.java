package com.example.service.Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyForeGroundService extends Service {
    private final String TAG = "MyForeGroundService";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");

        //创建一个通知
        NotificationManager notificationManager = (NotificationManager) getApplication().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel(
                "channel_id",
                "channel_name",
                notificationManager.IMPORTANCE_HIGH
        );
        notificationManager.createNotificationChannel(channel);
        Notification.Builder builder = new Notification.Builder(
                this,
                "channel_id"
        );
        Notification notification = builder.build();
        //将服务提升为前台服务
        startForeground(1, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        String keyStop = intent.getStringExtra("key_stop");
        if(TextUtils.equals(keyStop, "stopForeGround")){
            stopForeground(true);//true表示移除通知
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }

}
