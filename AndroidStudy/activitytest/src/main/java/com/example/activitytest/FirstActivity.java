package com.example.activitytest;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {
    private Button btn_jump;
    private Button btn_jump_student;
    private ActivityResultLauncher<Intent> launcher;
    private ListView lv_name;
    private ArrayList nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        findViews();

        setListeners();

        //初始化数据源
        initData();
        //创建Adapter
        ArrayAdapter adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                nameList
        );
        //绑定Adapter
        lv_name.setAdapter(adapter);
        //获取launcher
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        switch (result.getResultCode()){
                            case 1:
                                Intent intent = result.getData();
                                String name = intent.getStringExtra("msg");
                                Integer num = intent.getIntExtra("number", 1);
                                nameList.set(num, name);
                                adapter.notifyDataSetChanged();
                                break;
                        }
                    }
                }
        );
        //设置监听
        lv_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(FirstActivity.this, FourthActivity_name.class);
                intent.putExtra("name", nameList.get(position).toString());
                intent.putExtra("num", position);
                launcher.launch(intent);
            }
        });
    }

    private void initData() {
        nameList = new ArrayList<>();
        nameList.add("张三");
        nameList.add("李四");
        nameList.add("蔡徐坤");
    }

    private void setListeners() {
        Listener listener = new Listener();
        btn_jump.setOnClickListener(listener);
        btn_jump_student.setOnClickListener(listener);
    }

    private void findViews() {
        btn_jump = findViewById(R.id.btn_jump);
        btn_jump_student = findViewById(R.id.btn_jump_student);
        lv_name = findViewById(R.id.lv_name);
    }

    class Listener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_jump:
                    jumpActivity();
                    break;
                case R.id.btn_jump_student:
                    jumpStudent();
                    break;
            }
        }
    }

    private void jumpStudent() {
        Intent intent = new Intent();
        //1.封装学生信息
        Student student = new Student("喜多郁代","114514",14);
        //2.将信息封装到Bundle中
        Bundle bundle = new Bundle();
        bundle.putSerializable("stu", student);
        //3.将bandle封装到intent中
        intent.putExtra("msg", bundle);
        intent.setClass(this, ThirdActivity.class);
        startActivity(intent);
    }

    private void jumpActivity() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.baidu.com/"));
    }
}