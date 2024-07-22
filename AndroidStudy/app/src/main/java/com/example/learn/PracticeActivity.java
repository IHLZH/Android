package com.example.learn;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.animation.AnimatableView;

public class PracticeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_login;
    private Button btn_enroll;
    private EditText edt_name;
    private EditText edt_passward;
    private LinearLayout layout;
    private void findViews() {
        btn_login = findViewById(R.id.btn_login);
        btn_enroll = findViewById(R.id.btn_enroll);
        edt_name = findViewById(R.id.edt_name);
        edt_passward = findViewById(R.id.edt_passward);
        layout = findViewById(R.id.layout);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        findViews();


        //给按钮添加监听事件的三种方法
        //1.匿名内部类

//        btn_login.setOnClickListener(v -> {
//            String userName = edt_name.getText().toString();
//            String passward = edt_passward.getText().toString();
//            //弹出文本提示
//            Toast.makeText(PracticeActivity.this, "欢迎！" + userName, Toast.LENGTH_SHORT).show();
//        });

        //2.内部类 便于代码复用 建议使用
//        Listener listener = new Listener();
 //       btn_login.setOnClickListener(listener);  //注册监听器
//        btn_enroll.setOnClickListener(listener);
        btn_enroll.setOnClickListener(this);
        btn_login.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btn_login){
            String userName = edt_name.getText().toString();
            String passward = edt_passward.getText().toString();
            Toast.makeText(PracticeActivity.this, "欢迎登录！" + userName, Toast.LENGTH_SHORT).show();
        }
        else{
            String userName = edt_name.getText().toString();
            Toast.makeText(PracticeActivity.this, "注册成功！" + userName, Toast.LENGTH_SHORT).show();
            //日志打印
            Log.i("LoginActivity", "用户注册");
        }
    }

    class Listener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //因为同一对象被多个方法调用，所以需要用id来区分
            int id = v.getId();
            if(id == R.id.btn_login){
                String userName = edt_name.getText().toString();
                String passward = edt_passward.getText().toString();
                Toast.makeText(PracticeActivity.this, "欢迎登录！" + userName, Toast.LENGTH_SHORT).show();
            }
            else{
                String userName = edt_name.getText().toString();
                Toast.makeText(PracticeActivity.this, "注册成功！" + userName, Toast.LENGTH_SHORT).show();
                //日志打印
                Log.i("LoginActivity", "用户注册");
            }
        }
    }
}
