package com.example.activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FourthActivity_name extends AppCompatActivity {
    private EditText edt_name;
    private Button btn_backToFirstActivity;
    private Integer num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        findViews();

        setListeners();

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        num = intent.getIntExtra("num", 1);
        edt_name.setText(name);
    }

    private void setListeners() {
        Listeners listeners = new Listeners();
        btn_backToFirstActivity.setOnClickListener(listeners);
    }

    private void findViews() {
        edt_name = findViewById(R.id.edt_name);
        btn_backToFirstActivity = findViewById(R.id.btn_backToFirstActivity);
    }

    class Listeners implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_backToFirstActivity:
                    backToFirstActivity();
                    break;
            }
        }
    }

    private void backToFirstActivity() {
        Intent intent = new Intent();
        intent.putExtra("msg", edt_name.getText().toString());
        intent.putExtra("number", num);
        intent.setClass(this, FirstActivity.class);
        setResult(1, intent);
        FourthActivity_name.this.finish();
    }
}