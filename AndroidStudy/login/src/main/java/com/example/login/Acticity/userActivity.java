package com.example.login.Acticity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.login.R;

public class userActivity extends AppCompatActivity {
    private ImageView img_avater;
    private TextView tv_account;
    private TextView tv_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        findViews();
    }

    private void findViews() {
        img_avater = findViewById(R.id.img_avater);
        tv_account = findViewById(R.id.tv_account);
        tv_pwd = findViewById(R.id.tv_pwd);
    }
}