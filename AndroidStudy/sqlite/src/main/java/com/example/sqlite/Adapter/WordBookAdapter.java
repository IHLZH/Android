package com.example.sqlite.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.example.sqlite.Activity.MainActivity;
import com.example.sqlite.Activity.UpdateWordActivity;
import com.example.sqlite.Entity.Words;
import com.example.sqlite.R;
import com.example.sqlite.Utils.SQLiteHelperUtil;

import java.util.ArrayList;
import java.util.List;

public class WordBookAdapter extends BaseAdapter {
    private Context context;
    private Integer layoutId;
    private List<Words> wordsList;
    public WordBookAdapter(){};
    public WordBookAdapter(Context context, Integer layoutId, List<Words> wordsList){
        this.context = context;
        this.layoutId = layoutId;
        this.wordsList = wordsList;
    }
    @Override
    public int getCount() {
        return wordsList.size();
    }

    @Override
    public Object getItem(int position) {
        return wordsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layoutId, null);
        }
        TextView tv_words = convertView.findViewById(R.id.tv_words);
        Button btn_update = convertView.findViewById(R.id.btn_update);
        Button btn_delete = convertView.findViewById(R.id.btn_delete);

        Words words = wordsList.get(position);
        tv_words.setText(words.getWord() + ": " + words.getTranslation());

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUpdate(words);
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteWord(words);
            }
        });
        return convertView;
    }



    private void goToUpdate(Words words) {
        Intent intent = new Intent();
        intent.setClass(context, UpdateWordActivity.class);
        intent.putExtra("word", words.getWord());
        context.startActivity(intent);
    }

    private void deleteWord(Words words) {
        String whereClause = "word = ? ";
        String[] whereArgs = {String.valueOf(words.getWord())};
        int row = MainActivity.wordDB.delete("tb_word", whereClause, whereArgs);
        if(row > 0){
            Log.i("delete", "删除成功");
        }else {
            Log.i("delete", "删除失败");
        }
        wordsList.remove(words);
        notifyDataSetChanged();
        Log.i("word", words.getWord());
    }
}
