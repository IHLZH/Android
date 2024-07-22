package com.example.practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

public class MenuActivity extends AppCompatActivity {
    private Button btn_context_menu;
    private Button btn_pop_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViews();

        //选项菜单

        //上下文菜单比选项菜单多一步注册控件
        registerForContextMenu(btn_context_menu);

        //弹出式菜单
        //创建弹出式菜单对象
        PopupMenu popupMenu = new PopupMenu(this, btn_pop_menu);
        //加载菜单布局
        popupMenu.getMenuInflater().inflate(R.menu.layout_menu, popupMenu.getMenu());
        //设置菜单点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.menu_open:
                        Log.i("menu", "打开文件成功");
                        break;
                    case R.id.menu_save:
                        Log.i("menu", "保存文件成功");
                        break;
                    case R.id.menu_copy:
                        Log.i("menu", "复制成功");
                        break;
                    case R.id.menu_paste:
                        Log.i("menu", "粘贴成功");
                        break;
                }
                return true;
            }
        });
        //设置按钮监听事件
        btn_pop_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();
            }
        });
    }

    private void findViews() {
        btn_context_menu = findViewById(R.id.btn_context_menu);
        btn_pop_menu = findViewById(R.id.btn_pop_menu);
    }

    //选项菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_open:
                Log.i("menu", "打开文件成功");
                break;
            case R.id.menu_save:
                Log.i("menu", "保存文件成功");
                break;
            case R.id.menu_copy:
                Log.i("menu", "复制成功");
                break;
            case R.id.menu_paste:
                Log.i("menu", "粘贴成功");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //上下文菜单
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.layout_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_open:
                Log.i("menu", "打开文件成功");
                break;
            case R.id.menu_save:
                Log.i("menu", "保存文件成功");
                break;
            case R.id.menu_copy:
                Log.i("menu", "复制成功");
                break;
            case R.id.menu_paste:
                Log.i("menu", "粘贴成功");
                break;
        }
        return super.onContextItemSelected(item);
    }


}