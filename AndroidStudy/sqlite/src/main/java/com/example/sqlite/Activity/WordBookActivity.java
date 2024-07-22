package com.example.sqlite.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import com.example.sqlite.Adapter.WordBookAdapter;
import com.example.sqlite.Entity.Words;
import com.example.sqlite.R;
import com.example.sqlite.Utils.SQLiteHelperUtil;

import java.util.ArrayList;
import java.util.List;

public class WordBookActivity extends AppCompatActivity {
    private List<Words> wordsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_book);

        initData();
        initListView();
    }

    private void initData() {
        Cursor cursor = MainActivity.wordDB.query("tb_word", null, null, null, null, null, null);
        while(cursor.moveToNext()){
            int idOfWord = cursor.getColumnIndex("word");
            int idOfTranslation = cursor.getColumnIndex("translation");
            String word = cursor.getString(idOfWord);
            String translation = cursor.getString(idOfTranslation);
            Words words = new Words(word, translation);
            wordsList.add(words);
        }
    }

    private void initListView() {
        ListView listView = findViewById(R.id.lv_words);
        WordBookAdapter wordBookAdapter = new WordBookAdapter(
                this,
                R.layout.item_word_book,
                wordsList
        );
        listView.setAdapter(wordBookAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //initListView();
    }
}