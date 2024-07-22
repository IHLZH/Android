package com.example.sqlite.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqlite.Entity.Words;
import com.example.sqlite.Utils.MySqliteHelper;
import com.example.sqlite.R;
import com.example.sqlite.Utils.SQLiteHelperUtil;

public class MainActivity extends AppCompatActivity {
    private EditText edt_word, edt_trans;
    private Button btn_input, btn_query, btn_search, btn_delete, btn_update;
    private TextView tv_words;
    public static SQLiteDatabase wordDB;
    private MySqliteHelper mySqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        //if(wordDB == null)initDatabase();
        initDatabaseHelper();

        setlisteners();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mySqliteHelper.close();
        wordDB.close();
    }

    private void initDatabaseHelper() {
        mySqliteHelper = new MySqliteHelper(
                this,
                "wordDB.db",
                null,
                1
        );

        SQLiteHelperUtil.wordDB = mySqliteHelper.getWritableDatabase();
        this.wordDB = SQLiteHelperUtil.wordDB;
        Log.i("wordDB", "数据库初始化成功！");
    }

    private void setlisteners() {
        Listener listener = new Listener();
        btn_input.setOnClickListener(listener);
        btn_query.setOnClickListener(listener);
        btn_search.setOnClickListener(listener);
        btn_delete.setOnClickListener(listener);
        btn_update.setOnClickListener(listener);
    }

    private void initDatabase() {
        String filePath = getFilesDir().getAbsolutePath() + "wordDB.db";
        wordDB = SQLiteDatabase.openOrCreateDatabase(filePath, null);
        String table = "create table tb_word(" +
                "id integer primary key autoincrement," +
                "word varchar(20) not null," +
                "translation varchar(20) not null);";
        wordDB.execSQL(table);
        Log.i("wordDB", "数据库初始化成功！");
    }

    private void findViews() {
        edt_word = findViewById(R.id.edt_word);
        edt_trans = findViewById(R.id.edt_trans);
        btn_input = findViewById(R.id.btn_input);
        btn_query = findViewById(R.id.btn_query);
        btn_search = findViewById(R.id.btn_search);
        btn_delete = findViewById(R.id.btn_delete);
        btn_update = findViewById(R.id.btn_update);
        tv_words = findViewById(R.id.tv_words);
    }

    public class Listener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_input:
                    inputWord();
                    break;
                case R.id.btn_query:
                    goToWordBook();
                    break;
                case R.id.btn_search:
                    searchWord();
                    break;
                case R.id.btn_delete:
                    deleteWord();
                    break;
                case R.id.btn_update:
                    updateWord();
                    break;
            }
        }
    }

    private void goToWordBook() {
        Intent intent = new Intent();
        intent.setClass(this, WordBookActivity.class);
        startActivity(intent);
    }

    private void goToSearch() {
        Intent intent = new Intent();
        intent.setClass(this, SearchWordActivity.class);
        startActivity(intent);
    }

    private void inputWord() {
        String word = edt_word.getText().toString().trim();
        String translation = edt_trans.getText().toString().trim();

        ContentValues contentValues = new ContentValues();
        contentValues.put("word", word);
        contentValues.put("translation", translation);

        long id = wordDB.insert("tb_word", null, contentValues);
        if(id > 0){
            Toast.makeText(this, "录入成功！", Toast.LENGTH_SHORT).show();
        }
    }

    private void searchWord() {
        String word = edt_word.getText().toString().trim();
        String selection = "word=?";
        String[] selectionArgs = { String.valueOf(word) };
        Cursor cursor = wordDB.query("tb_word", null, selection, selectionArgs , null, null, null);
        StringBuffer result = new StringBuffer();
        while(cursor.moveToNext()){
            int idOfWord = cursor.getColumnIndex("word");
            String word1 = cursor.getString(idOfWord);
            int idOfTranslation = cursor.getColumnIndex("translation");
            String translation = cursor.getString(idOfTranslation);
            result.append(word1 + ": " + translation + "\n");
        }
        tv_words.setText(result.toString());

        //ContentValues contentValues = new ContentValues();
        //wordDB.update("tb_word", contentValues, "id=?", {"1"});
    }

    private void deleteWord() {
        String word = edt_word.getText().toString().trim();
        String translation = edt_trans.getText().toString().trim();
        Words words = new Words(word, translation);

        String whereClause = "word = ?";
        String[] whereArgs = {String.valueOf(words.getWord())};

        int row = wordDB.delete("tb_word", whereClause, whereArgs);
        if(row > 0){
            Log.i("delete", "删除成功");
        }else {
            Log.i("delete", "删除失败");
        }
    }

    private void updateWord(){
        String word = edt_word.getText().toString().trim();
        String translation = edt_trans.getText().toString().trim();
        Words words = new Words(word, translation);

        String whereClause = "word=?";
        String[] whereArgs = {word};
        ContentValues values = new ContentValues();
        values.put("translation", translation);

        int row = wordDB.update("tb_word", values, whereClause, whereArgs);
        if(row > 0){
            Log.i("update", "更新成功");
        }else {
            Log.i("update", "更新失败");
        }
    }
}