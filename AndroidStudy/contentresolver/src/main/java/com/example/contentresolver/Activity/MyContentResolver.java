package com.example.contentresolver.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.view.View;

import com.example.contentresolver.R;
import com.example.contentresolver.databinding.ActivityMainBinding;
import com.example.contentresolver.databinding.ActivityMyContentResolverBinding;

public class MyContentResolver extends AppCompatActivity {
    private ActivityMyContentResolverBinding binding = null;
    private ContentResolver contentResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyContentResolverBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        contentResolver = getContentResolver();

        setLinsteners();
    }

    private void setLinsteners() {
        binding.btnGetCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = contentResolver.query(CallLog.Calls.CONTENT_URI, null, null, null, null, null);
                StringBuffer result = new StringBuffer();
                while(cursor.moveToNext()){
                    int index = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                    String name = cursor.getString(index);
                    result.append(name + "\n");
                }
                binding.tvCall.setText(result);
                
            }
        });
    }
}