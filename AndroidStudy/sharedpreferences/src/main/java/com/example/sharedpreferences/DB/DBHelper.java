package com.example.sharedpreferences.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sharedpreferences.Entity.User;

public class DBHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "DB.db";
    private static String TABLE_NAME = "tb_user";
    private static DBHelper dbHelper;
    private static SQLiteDatabase read;
    private static SQLiteDatabase write;

    public DBHelper(Context context){
        super(context, DB_NAME, null, 1);
    }

    public static DBHelper getInstance(Context context){
        if(dbHelper == null){
            dbHelper = new DBHelper(context);
        }
        return dbHelper;
    }
    public SQLiteDatabase openReadLink(){
        if(read == null || !read.isOpen()){
            read = dbHelper.getReadableDatabase();
        }
        return read;
    }

    public SQLiteDatabase openWriteLink(){
        if(write == null || !write.isOpen()){
            write = dbHelper.getWritableDatabase();
        }
        return write;
    }

    public void closeLink(){
        if(read != null || read.isOpen()){
            read.close();
        }
        if(write != null || write.isOpen()){
            write.close();
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String table = "CREATE TABLE IF NOT EXISTS tb_user(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "name VARCHAR NOT NULL," +
                "age INT NOT NULL," +
                "height INT NOT NULL," +
                "weight FOLAT NOT NULL);";
        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 数据库版本发生变化时，应用程序安装启动会执行该方法
    }

    public long insert(User user){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", user.getName());
        contentValues.put("age", user.getAge());
        contentValues.put("weight", user.getWeight());
        contentValues.put("height", user.getHeight());
        return write.insert(TABLE_NAME, null, contentValues);
    }
}
