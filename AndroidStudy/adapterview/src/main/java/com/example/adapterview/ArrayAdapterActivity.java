package com.example.adapterview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ArrayAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_adapter);

        List<String> learnList = new ArrayList<>();
        learnList.add("Android基础编程");
        learnList.add("智能设备方向基础课");
        learnList.add("数据库系统概论");
        learnList.add("操作系统");

        //适配器 将item与适配器绑定
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_learn, learnList);
        //获取AdapterView
        ListView lv_learn = findViewById(R.id.lv_learn);
        //AdapterView与适配器绑定
        lv_learn.setAdapter(adapter);
        //
        lv_learn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(
                        ArrayAdapterActivity.this,
                        learnList.get(position),
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }
}