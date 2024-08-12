package com.example.service.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.example.service.R;
import com.example.service.Service.MyBindService;
import com.example.service.Service.MyForeGroundService;
import com.example.service.Service.MyService;
import com.example.service.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private Boolean isBound = false;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setLinsteners();
    }

    private void setLinsteners() {
        binding.btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMyService();
            }
        });
        binding.btnStartBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBindService();
            }
        });
        binding.btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                stopService(intent);
            }
        });
        binding.btnStopService2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                intent.putExtra("key_stop", "stop");
                startService(intent);
            }
        });
        binding.btnUnBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBound){
                    unbindService(connection);
                    isBound = false;
                }else {
                    Log.i("MyBindService", "onClick: service已解绑");
                }
            }
        });
        binding.btnStartForeGroundService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyForeGroundService.class);
                startService(intent);
            }
        });
        binding.btnStopForeGroundService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyForeGroundService.class);
                intent.putExtra("key_stop", "stopForeGround");
                startService(intent);
            }
        });
    }

    private void startBindService() {
        Intent intent = new Intent(this, MyBindService.class);
        isBound = bindService(intent, connection, BIND_AUTO_CREATE);
    }
    private MyBindService.MyBinder myBindService;
    private ServiceConnection connection = new ServiceConnection() {
        //创建连接时回调
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //这里 IBinder类型的service 就是我们要绑定的那个service
            //通过这个service，Activity就可以调用MyBindService.MyBinder中的方法
            myBindService = (MyBindService.MyBinder)service;
            myBindService.Test();
        }
        //断开连接时回调
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected: ");
            //Intent intent = new Intent(MainActivity.this, MyBindService.class);
            //stopService(intent);
        }
    };

    private void startMyService() {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        Log.i("MyBindService", "onDestroy: Activity销毁");
        super.onDestroy();
    }
}