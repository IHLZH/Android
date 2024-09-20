package com.example.sharedpreferences.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sharedpreferences.DB.DBHelper;
import com.example.sharedpreferences.Entity.User;
import com.example.sharedpreferences.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {
    private ActivityMain2Binding binding;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setLinsteners();
    }

    private void setLinsteners() {
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setAge(Integer.valueOf(binding.edtAge.getText().toString()));
                user.setName(binding.edtName.getText().toString());
                user.setWeight(Float.valueOf(binding.edtWeight.getText().toString()));
                user.setHeight(Integer.valueOf(binding.edtHing.getText().toString()));
                long row = dbHelper.insert(user);
                if(row > 0){
                    Toast.makeText(MainActivity2.this, "插入成功！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        dbHelper = DBHelper.getInstance(this);
        dbHelper.openReadLink();
        dbHelper.openWriteLink();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}