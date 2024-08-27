package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.example.sharedpreferences.databinding.ActivityMainBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initShared();
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initEvents();
            }
        });
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
        binding.btnSaveTosd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTosd();
            }
        });
        initData();

    }

    private void saveTosd() {
        //是否存在sd卡
        String state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){
            //获取sd卡目录，并自定义txtFile目录
            String path = getExternalFilesDir(null).getAbsolutePath() + "/txtFile/";
            File file = new File(path);
            if(!file.exists()){
                file.mkdir();
            }

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(path + "text.txt");
                fileOutputStream.write("我上早八".getBytes());
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void saveData() {
        try {
            FileOutputStream fileOutputStream = openFileOutput("happy.txt", Context.MODE_PRIVATE);
            fileOutputStream.write("我上早八".getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initData() {
        boolean remember = sharedPreferences.getBoolean("remember", false);
        binding.cbRemember.setChecked(remember);
        if(remember){
            binding.edtAccount.setText(sharedPreferences.getString("account", ""));
            binding.edtPwd.setText(sharedPreferences.getString("pwd", ""));
        }
    }

    private void initEvents() {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean("remember", binding.cbRemember.isChecked());
        if(binding.cbRemember.isChecked()){
            edit.putString("account", binding.edtAccount.getText().toString());
            edit.putString("pwd", binding.edtPwd.getText().toString());
        }
        //同步保存
        edit.commit();
        //异步保存
        //edit.apply();
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    private void initShared() {
        sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
    }
}