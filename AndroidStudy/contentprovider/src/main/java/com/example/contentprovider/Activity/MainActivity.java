package com.example.contentprovider.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.contentprovider.R;
import com.example.contentprovider.Utils.SqliteHelper;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase bookDB;
    private Button btn_query;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SqliteHelper sqliteHelper = new SqliteHelper(
                        getApplicationContext(),
                        "bookDB.db",
                        null,
                        1
                );
                bookDB = sqliteHelper.getWritableDatabase();
                Cursor cursor = bookDB.query("tb_books", null, null, null, null, null, null);
                StringBuffer result = new StringBuffer();
                while(cursor.moveToNext()){
                    int indexOfName = cursor.getColumnIndex("name");
                    int indexOfId = cursor.getColumnIndex("id");
                    int id = cursor.getInt(indexOfId);
                    String name = cursor.getString(indexOfName);
                    result.append("id: " + id + ", name: " + name + "\n");
                }
                tv_result.setText(result);
            }
        });
    }

    private void findViews() {
        btn_query = findViewById(R.id.btn_query);
        tv_result = findViewById(R.id.tv_result);
    }


}