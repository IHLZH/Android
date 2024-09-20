package com.example.wordbook.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.wordbook.R;
import com.example.wordbook.adapter.WordsAdapter;
import com.example.wordbook.databinding.ActivityMainBinding;
import com.example.wordbook.entity.Word;
import com.example.wordbook.utils.db.WordDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private WordDatabase wordDB;


    class Test{
        private int ac;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initData();
        initWords();
        setLinsteners();
    }

    private void setLinsteners() {
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddWordDialog();
            }
        });
        binding.btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = binding.edtQueryWord.getText().toString();
                queryByWord(word);
            }
        });
    }

    private void queryByWord(String word) {
        List<Word> wordList = wordDB.getWordDao().queryByWord(word);
        WordsAdapter wordsAdapter = new WordsAdapter(this, wordList);
        binding.rvWords.setAdapter(wordsAdapter);
    }

    private void showAddWordDialog() {
        View item_dialog_add = getCustomView();
        EditText edt_add_word = item_dialog_add.findViewById(R.id.edt_add_word);
        EditText edt_add_trans = item_dialog_add.findViewById(R.id.edt_add_trans);

        AlertDialog.Builder dialog_build = new AlertDialog.Builder(this)
                .setView(item_dialog_add)
                .setTitle("添加单词")
                .setCancelable(false)
                .setPositiveButton("添加", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String word = edt_add_word.getText().toString();
                        String trans = edt_add_trans.getText().toString();
                        Word word1 = new Word(word, trans);
                        long[] insert = wordDB.getWordDao().insert(word1);
                        if(insert.length > 0){
                            Log.i(TAG, "onClick: 插入成功");
                        }else Log.i(TAG, "onClick: 插入失败");
                        initWords();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog dialog = dialog_build.create();
        dialog.show();
    }

    private View getCustomView() {
        return LayoutInflater.from(this).inflate(R.layout.item_dialog_add, null);
    }

    private void initData() {
        if(wordDB == null)wordDB = WordDatabase.getInstance(MainActivity.this);
    }

    private void initWords() {
        List<Word> wordList = wordDB.getWordDao().queryAll();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        WordsAdapter wordsAdapter = new WordsAdapter(this, wordList);
        binding.rvWords.setLayoutManager(manager);
        binding.rvWords.setAdapter(wordsAdapter);
    }
}