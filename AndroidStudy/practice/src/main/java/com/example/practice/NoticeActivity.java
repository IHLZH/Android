package com.example.practice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NoticeActivity extends AppCompatActivity {
    private Button btn_custom_button;
    private Button btn_dialog;
    private Button btn_custom_dialog;
    private Button btn_notice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        //自定义Toast，区别就是View需要自己设置，不过一般不使用
        btn_custom_button = findViewById(R.id.btn_custom_button);
        btn_custom_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomToast();
            }
        });
        //对话框
        btn_dialog = findViewById(R.id.btn_dialog);
        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        //自定义对话框
        btn_custom_dialog = findViewById(R.id.btn_custom_dialog);
        btn_custom_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {showCustomDialog();}
        });
        //通知栏通知
        btn_notice = findViewById(R.id.btn_notice);
        btn_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {showNotice();}
        });
    }

    private void showNotice() {
        //获取通知服务
        NotificationManager notificationManager = (NotificationManager) getApplication().getSystemService(
                Context.NOTIFICATION_SERVICE
        );
        //创建通知渠道
        NotificationChannel channel = new NotificationChannel(
                "channelId",
                "channelName",
                notificationManager.IMPORTANCE_DEFAULT
        );
        //关联通知服务和通知渠道
        notificationManager.createNotificationChannel(channel);
        //创建通知构造器对象
        Notification.Builder notificationBuilder = new Notification.Builder(
                this,
                "channelId"
        )
                .setSmallIcon(R.mipmap.genshin)
                .setContentTitle("启动原神！")
                .setContentText("您今天启动原神了吗？");
        //通过构造器创建通知对象
        Notification notification = notificationBuilder.build();
        //由通知服务发布通知消息
        notificationManager.notify(0, notification);
    }

    private void showCustomDialog() {
        //设置对话框自定义视图
        AlertDialog.Builder customDialogBuild = new AlertDialog.Builder(this)
                .setView(getCustomView());
        AlertDialog customDialog = customDialogBuild.create();
        customDialog.show();
    }

    private void showDialog() {
        //创建对话框构造器对象
        AlertDialog.Builder dialog_build = new AlertDialog.Builder(this)
                .setTitle("关闭应用！")
                .setMessage("确定要关闭应用吗？")
                .setIcon(R.mipmap.genshin)
                .setCancelable(false) //点击屏幕外围不关闭
                .setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(NoticeActivity.this, "启动成功！", Toast.LENGTH_SHORT).show();
                        // 关闭当前Activity
                        NoticeActivity.this.finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(NoticeActivity.this, "启动失败！", Toast.LENGTH_SHORT).show();
                        // 关闭对话框
                        dialog.dismiss();
                    }
                });
        //通过构造器构造对话框对象
        AlertDialog dialog = dialog_build.create();
                //显示对话框
        dialog.show();
    }



    private void showCustomToast() {
        Toast customToast = new Toast(this);
        customToast.setView(getCustomView());
        //设置Toast位置
        customToast.setGravity(Gravity.CENTER, 0, 0);
        customToast.show();
    }

    private View getCustomView() {
        View view = LayoutInflater.from(this).inflate(R.layout.custom_view_layout, null);
        return view;
    }
}