package com.example.login.Acticity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.login.R;
import com.example.login.entity.Userinfo;
import com.example.login.utils.HostUtil;
import com.example.login.utils.Result;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class registerActivity extends AppCompatActivity {

    private EditText edt_register_account;
    private EditText edt_register_pwd;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViews();

        initEvent();
    }

    private void initEvent() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = edt_register_account.getText().toString().trim();
                String pwd = edt_register_pwd.getText().toString().trim();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        register(account, pwd);
                    }
                }).start();
            }
        });
    }

    private void register(String account, String pwd) {
        InputStream inputStream = null;
        try {
            URL url = new URL(HostUtil.HOST + "/register?account=" + account + "&pwd=" + pwd);
            inputStream = url.openStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String json = bufferedReader.readLine();
            Gson gson = new Gson();
            Result result = gson.fromJson(json, new TypeToken<Result<Userinfo>>() {}.getType());
            if(result.getCode() != 200){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(
                                registerActivity.this,
                                result.getMsg(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                });
            }else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(
                                registerActivity.this,
                                result.getMsg(),
                                Toast.LENGTH_LONG
                        ).show();
                        gotologin();
                    }
                });
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void gotologin() {
        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);
    }

    private void findViews() {
        edt_register_account = findViewById(R.id.edt_register_account);
        edt_register_pwd = findViewById(R.id.edt_register_pwd);
        btn_register = findViewById(R.id.btn_register);
    }
}