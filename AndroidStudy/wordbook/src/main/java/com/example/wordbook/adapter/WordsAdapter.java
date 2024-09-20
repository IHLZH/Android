package com.example.wordbook.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordbook.R;
import com.example.wordbook.activity.MainActivity;
import com.example.wordbook.entity.Word;
import com.example.wordbook.utils.db.WordDatabase;

import java.util.List;

public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.ViewHolder> {
    private Context context;

    private List<Word> wordList;
    WordDatabase wordDB;

    public WordsAdapter(Context context, List<Word> wordList) {
        this.context = context;
        this.wordList = wordList;
        wordDB = WordDatabase.getInstance(context);
    }

    @NonNull
    @Override
    public WordsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rv_words, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WordsAdapter.ViewHolder holder, int position) {
        Word word = wordList.get(position);
        holder.item_rv_tv_word.setText(word.getWord());
        holder.item_rv_tv_trans.setText(word.getTranslation());
        holder.item_rv_btn_edit.setOnClickListener(new MyLinsteners(word, position));
        holder.item_rv_btn_delete.setOnClickListener(new MyLinsteners(word, position));
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView item_rv_tv_word;
        private TextView item_rv_tv_trans;
        private Button item_rv_btn_delete;
        private Button item_rv_btn_edit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_rv_tv_word = itemView.findViewById(R.id.item_rv_tv_word);
            item_rv_tv_trans = itemView.findViewById(R.id.item_rv_tv_trans);
            item_rv_btn_edit = itemView.findViewById(R.id.item_rv_btn_edit);
            item_rv_btn_delete = itemView.findViewById(R.id.item_rv_btn_delete);
        }

        public TextView getItem_rv_tv_word() {
            return item_rv_tv_word;
        }

        public TextView getItem_rv_tv_trans() {
            return item_rv_tv_trans;
        }

        public Button getItem_rv_btn_delete() {
            return item_rv_btn_delete;
        }

        public Button getItem_rv_btn_edit() {
            return item_rv_btn_edit;
        }
    }

    class MyLinsteners implements View.OnClickListener{
        private Word word;
        private int position;

        public MyLinsteners() {
        }


        public MyLinsteners(Word word, int position) {
            this.word = word;
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id){
                case R.id.item_rv_btn_edit:
                    updateWord(word, position);
                    break;
                case R.id.item_rv_btn_delete:
                    wordDB.getWordDao().delete(word);
                    wordList = wordDB.getWordDao().queryAll();
                    notifyItemChanged(position);
                    break;
            }
        }
    }

    private void updateWord(Word word, int position) {
        View item_dialog_add = getCustomView();
        EditText edt_add_word = item_dialog_add.findViewById(R.id.edt_add_word);
        EditText edt_add_trans = item_dialog_add.findViewById(R.id.edt_add_trans);

        AlertDialog.Builder dialog_build = new AlertDialog.Builder(context)
                .setView(item_dialog_add)
                .setTitle("编辑单词")
                .setCancelable(false)
                .setPositiveButton("编辑", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String words = edt_add_word.getText().toString();
                        String trans = edt_add_trans.getText().toString();
                        Word word1 = new Word(word.getId(),words, trans);
                        wordDB.getWordDao().update(word1);
                        wordList = wordDB.getWordDao().queryAll();
                        notifyItemChanged(position);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = dialog_build.create();
        dialog.show();
    }

    private View getCustomView() {
        return LayoutInflater.from(context).inflate(R.layout.item_dialog_add, null);
    }
}
