package com.example.wordbook.utils.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.wordbook.dao.WordDao;
import com.example.wordbook.entity.Word;

@Database(entities = {Word.class}, version = 1)
public abstract class WordDatabase extends RoomDatabase {
    private static String DB_NAME = "wordDB2.db";

    private static volatile WordDatabase wordDatabase;

    public abstract WordDao getWordDao();

    public static WordDatabase getInstance(Context context){
        if(wordDatabase == null){
            synchronized (WordDatabase.class){
                if(wordDatabase == null){
                    wordDatabase = Room.databaseBuilder(context.getApplicationContext(), WordDatabase.class, DB_NAME)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return wordDatabase;
    }
}
