package com.example.activitytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button btn_jumpToNormalActivity;
    private Button btn_jumpToDialogActivity;
    private EditText edt_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("msg114514", "MainActivity：onCreate");

        findViews();
        //跳转到Normal
        btn_jumpToNormalActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToNormalActivity();
            }
        });
        //跳转到DialogActivity
        btn_jumpToDialogActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToDialogActivity();
            }
        });

        if(savedInstanceState != null){
            String text = savedInstanceState.getString("edt_text");
            edt_text.setText(text);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        String text = edt_text.getText().toString();
        outState.putString("edt_text", text);
    }

    private void jumpToDialogActivity() {
        Intent intent = new Intent();
        intent.setClass(this, DialogActivity.class);
        startActivity(intent);
    }

    private void jumpToNormalActivity() {
        Intent intent = new Intent();
        intent.setClass(this, NormalActivity.class);
        startActivity(intent);
    }

    private void findViews() {
        btn_jumpToNormalActivity = findViewById(R.id.btn_jumpToNormalActivity);
        btn_jumpToDialogActivity = findViewById(R.id.btn_jumpToDialogActivity);
    }

    @Override
    protected void onStart() {
        Log.i("msg114514", "MainActivity：onStrat");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.i("msg114514", "MainActivity：onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i("msg114514", "MainActivity：onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i("msg114514", "MainActivity：onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("msg114514", "MainActivity：onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("msg114514", "MainActivity：onDestroy");
        super.onDestroy();
    }


}