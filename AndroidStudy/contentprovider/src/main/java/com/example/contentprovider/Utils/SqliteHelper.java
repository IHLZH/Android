package com.example.contentprovider.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqliteHelper extends SQLiteOpenHelper {
    public SqliteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists tb_books(" +
                "id Integer primary key autoincrement, " +
                "name varchar(20) not null" +
                ");"
        );
        db.execSQL("create table if not exists tb_users(" +
                "id Integer primary key autoincrement, " +
                "name varchar(20) not null, " +
                "gender Integer not null" +
                ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
