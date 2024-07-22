package com.example.login.Acticity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.login.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class loginActivity extends AppCompatActivity {
    private EditText edt_account;
    private EditText edt_pwd;
    private Button btn_login;
    private Button btn_toregister;
    private final String Host = "http://10.7.85.144:8080/AndroidServlet/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();

        initEvent();
    }

    private void initEvent() {
        btn_toregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this, registerActivity.class);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = edt_account.getText().toString().trim();
                String pwd = edt_pwd.getText().toString().trim();
                login(account, pwd);
            }
        });
    }

    private void login(String account, String pwd) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("account", account)
                .add("pwd", pwd)
                .build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(Host + "/login")
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                
            }
        });
    }

    private void findViews() {
        edt_account = findViewById(R.id.edt_account);
        edt_pwd = findViewById(R.id.edt_pwd);
        btn_login = findViewById(R.id.btn_login);
        btn_toregister = findViewById(R.id.btn_toregister);
    }
}