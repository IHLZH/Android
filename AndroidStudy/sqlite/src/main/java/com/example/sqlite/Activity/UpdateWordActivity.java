package com.example.sqlite.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sqlite.R;

public class UpdateWordActivity extends AppCompatActivity {
    private EditText edt_update_word, edt_update_trans;
    private Button btn_update_confirm;
    private String word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_word);

        findViews();
        get_intent();
        setLinsteners();
    }

    private void setLinsteners() {
        btn_update_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmUpdate();
            }
        });
    }

    private void confirmUpdate() {
        String translation = edt_update_trans.getText().toString().trim();
        ContentValues values = new ContentValues();
        values.put("trans", translation);
        String whereClause = "word=?";
        String[] whereArgs = {word};
        int row = MainActivity.wordDB.update("tb_word", values, whereClause, whereArgs);
        if(row > 0){
            Log.i("update", "修改成功");
        }else{
            Log.i("update", "修改失败");
        }
    }

    private void get_intent() {
        Intent intent = getIntent();
        word = intent.getStringExtra("word");
        edt_update_word.setText(word);
    }

    private void findViews() {
        edt_update_word = findViewById(R.id.edt_update_word);
        edt_update_trans = findViewById(R.id.edt_update_trans);
        btn_update_confirm = findViewById(R.id.btn_update_confirm);
    }
}