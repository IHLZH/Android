package com.example.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeWorkActivity extends AppCompatActivity {

    private EditText edt_user;
    private EditText edt_passward;
    private Button btn_login;
    private Button btn_change;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_work);

        findViews();

        btn_change.setOnClickListener(v -> {
            Toast.makeText(this, "改变成功！", Toast.LENGTH_SHORT).show();
            edt_passward.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        });



        btn_login.setOnClickListener(v -> {
            String textUser = edt_user.getText().toString();
            String textPassWard = edt_passward.getText().toString();
            if(textUser.equals("")){
                System.out.println("用户名不能为空");
                Toast.makeText(
                        HomeWorkActivity.this,
                        "用户名不能为空",
                        Toast.LENGTH_SHORT
                ).show();
            }
            else if(textPassWard.equals("")){
                System.out.println("密码不能为空");
                Toast.makeText(
                        HomeWorkActivity.this,
                        "密码不能为空",
                        Toast.LENGTH_SHORT
                ).show();
            }
            else if(textPassWard.length() > 0 && textPassWard.length() < 6){
                System.out.println("密码长度不能小于6");
                Toast.makeText(
                        HomeWorkActivity.this,
                        "密码长度不能小于6",
                        Toast.LENGTH_SHORT
                ).show();
            }
            else{
                Toast.makeText(
                        HomeWorkActivity.this,
                        "恭喜，登陆成功",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    private void findViews() {
        edt_user = findViewById(R.id.edt_user);
        edt_passward = findViewById(R.id.edt_passward);
        btn_login = findViewById(R.id.btn_login);
        btn_change = findViewById(R.id.btn_change);
    }
}