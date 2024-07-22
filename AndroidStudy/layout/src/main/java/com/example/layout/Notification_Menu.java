package com.example.layout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

public class Notification_Menu extends AppCompatActivity {
    private Button btn_menu;
    private Button btn_ejectmenu;
    private PopupMenu popupMenu;

    private void findViews() {
        btn_menu = findViewById(R.id.btn_menu);
        btn_ejectmenu = findViewById(R.id.btn_ejectmenu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_menu);
        findViews();

        //上下文菜单
        //上下文菜单比正常菜单多了一步 注册UI控件
        registerForContextMenu(btn_menu);

        //普通菜单 选项菜单

        //弹出式菜单
        //1.创建popupMenu对象，并与UI控件关联起来
        popupMenu = new PopupMenu(this, btn_ejectmenu);
        //2.加载菜单资源，并关联给popupMenu的菜单
        popupMenu.getMenuInflater().inflate(
                R.menu.file_menu,
                popupMenu.getMenu()
        );
        //3.注册菜单项点击事件监听器
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.menu_file:
                        Log.i("msg_menu", "文件");
                        break;
                    case R.id.menu_edit:
                        Log.i("msg_menu", "编辑文件");
                        break;
                    case R.id.menu_open:
                        Log.i("msg_menu", "打开文件");
                        break;
                    case R.id.menu_saveas:
                        Log.i("msg_menu", "保存文件");
                        break;
                }
                return true; //是否解决了交互事件
            }
        });
        //4.显示菜单
        setPopupListeners();
    }

    private void setPopupListeners() {
        btn_ejectmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出弹出式菜单
                popupMenu.show();
            }
        });
    }

    //选项菜单 普通菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //1.使用菜单加载器 加载菜单资源
        //2.把菜单资源关联给Activity默认的选项菜单
        getMenuInflater().inflate(R.menu.file_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //给选项菜单注册item选择事件监听器
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //判断当前选择的是哪一个菜单项
        switch(item.getItemId()){
            case R.id.menu_file:
                Log.i("msg_menu", "文件"); //日志打印
                break;
            case R.id.menu_edit:
                Log.i("msg_menu", "编辑文件");
                break;
            case R.id.menu_open:
                Log.i("msg_menu", "打开文件");
                break;
            case R.id.menu_saveas:
                Log.i("msg_menu", "保存文件");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //上下文菜单
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.file_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public boolean onContextItemSelected(@NonNull MenuItem item){
        switch(item.getItemId()){
            case R.id.menu_file:
                Log.i("msg_menu", "文件");
                break;
            case R.id.menu_edit:
                Log.i("msg_menu", "编辑文件");
                break;
            case R.id.menu_open:
                Log.i("msg_menu", "打开文件");
                break;
            case R.id.menu_saveas:
                Log.i("msg_menu", "保存文件");
                break;
        }
        return super.onContextItemSelected(item);
    }
}