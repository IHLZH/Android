package com.example.activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BaiduActivity extends AppCompatActivity {
    private Button btn_jumpToBaidu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu);

        findViews();

        btn_jumpToBaidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.baidu.com"));
            }
        });
    }

    private void findViews() {
        btn_jumpToBaidu = findViewById(R.id.btn_jumpToBaidu);
    }

}