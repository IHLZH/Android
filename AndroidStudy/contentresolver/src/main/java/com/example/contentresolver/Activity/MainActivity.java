package com.example.contentresolver.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.contentresolver.Utils.AlertDialogUtil;
import com.example.contentresolver.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding = null;
    private ContentResolver contentResolver;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        contentResolver = getContentResolver();
        uri = Uri.parse("content://com.example.contentprovider.ContentProvider.provider/tb_books");

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialogUtil.showDialog(
                        MainActivity.this,
                        "添加",
                        "id",
                        "书名",
                        new AlertDialogUtil.DialogClickCallback() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, String input1, String input2) {
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("id", Integer.valueOf(input1));
                                contentValues.put("name", input2);
                                Uri insert = contentResolver.insert(uri, contentValues);
                                if (insert != null) {
                                    Toast.makeText(MainActivity.this, "插入成功", Toast.LENGTH_SHORT).show();
                                } else
                                    Toast.makeText(MainActivity.this, "插入失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialogUtil.showDialog(
                        MainActivity.this,
                        "删除",
                        "id",
                        "书名",
                        new AlertDialogUtil.DialogClickCallback() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, String input1, String input2) {
                                String selection = "id = ?";
                                String[] selectionArgs = {input1};
                                int delete = contentResolver.delete(uri, selection, selectionArgs);
                                if(delete > 0){
                                    Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                                }else
                                    Toast.makeText(MainActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialogUtil.showDialog(
                        MainActivity.this,
                        "修改",
                        "id",
                        "书名",
                        new AlertDialogUtil.DialogClickCallback() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, String input1, String input2) {
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("name", input2);
                                String selection = "id = ?";
                                String[] selectionArgs = {input1};
                                int update = contentResolver.update(uri, contentValues, selection, selectionArgs);
                                if(update > 0){
                                    Toast.makeText(MainActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                }else
                                    Toast.makeText(MainActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });
        binding.btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = contentResolver.query(uri, null, null, null, null);
                StringBuffer result = new StringBuffer();
                if(cursor == null){
                    Toast.makeText(MainActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
                }else{
                    while(cursor.moveToNext()){
                        int indexOfName = cursor.getColumnIndex("name");
                        int indexOfId = cursor.getColumnIndex("id");
                        int id = cursor.getInt(indexOfId);
                        String name = cursor.getString(indexOfName);
                        result.append("id: " + id + ", name: " + name + "\n");
                    }
                }
                binding.tvResult.setText(result);
            }
        });

    }
}