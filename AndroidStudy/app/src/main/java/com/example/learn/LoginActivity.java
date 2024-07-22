package com.example.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button btn_login;
    private Button btn_register;
    private EditText edt_name;
    private EditText edt_passward;
    private LinearLayout layout;
    private void findViews() {
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        edt_name = findViewById(R.id.edt_name);
        edt_passward = findViewById(R.id.edt_passward);
        layout = findViewById(R.id.layout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //获取控件，按钮与文件框
        //Alt + 回车 自动生成方法
        findViews();
        //1.通过匿名内部类
        btn_login.setOnClickListener( v ->{
                //获取输入内容，用对话框提示出来
                String name = edt_name.getText().toString(); //获取用户名
                String passWard = edt_passward.getText().toString(); //获取密码
                //对话框提示
                Toast.makeText(LoginActivity.this,
                        "登陆成功：" + name,
                        Toast.LENGTH_LONG).show();
                //在哪显示 + 显示内容 + 显示时长

        });
        //绑定事件
        btn_register.setOnClickListener(v -> {
            String name = edt_name.getText().toString();
            Toast.makeText(LoginActivity.this,
                    "注册成功：" + name,
                    Toast.LENGTH_LONG).show();
        });

        //2.通过成员内部类
//        LoginListener listener = new LoginListener();
//        btn_login.setOnClickListener(listener);
//        btn_register.setOnClickListener(listener);

        //layout.getBackground().setAlpha(100); //设置背景透明度
    }

    class LoginListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            //View v是用户点击的那一个按钮
            //获取用户点击的按钮的id
            int id = v.getId();
            //用户点击控件的id与资源文件中的id对比，就能知道是哪一个
            if(id == R.id.btn_login){
                String name = edt_name.getText().toString(); //获取用户名
                String passWard = edt_passward.getText().toString(); //获取密码
                //对话框提示
                Toast.makeText(LoginActivity.this, name + ":" + passWard, Toast.LENGTH_LONG).show();
            }else if(id == R.id.btn_register){
                Toast.makeText(LoginActivity.this, "欢迎注册！", Toast.LENGTH_LONG).show();
                //打印注册日志
                Log.i("LoginActivity", "用户注册");
            }
        }
    }
}