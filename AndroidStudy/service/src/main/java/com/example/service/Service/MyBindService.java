package com.example.service.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyBindService extends Service {
    private final String TAG = "MyBindService";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        return new MyBinder(this);
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate: ");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }
    public void Test(){
        //Log.i(TAG, "Test: MyBindService的Test方法被调用");
    }

    public class MyBinder extends Binder{
        private MyBindService myBindService;
        public MyBinder(){}
        public MyBinder(MyBindService bindService){
            this.myBindService = bindService;
        }
        public void Test(){
            //Log.i(TAG, "Test: MyBinder的Test方法被调用");
            //这样MyBinder就可以调用MyBindService中的方法
            //MyBinder作为一个中间人 Activity调用MyBinder的方法 -> MyBinder再调用Service的方法
            myBindService.Test();
        }
    }
}
