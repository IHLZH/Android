package com.example.contentprovider.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.contentprovider.Utils.SqliteHelper;

public class MyContentProvider extends ContentProvider {
    private SQLiteDatabase bookDB;
    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static String AUTHORITIES = "com.example.contentprovider.ContentProvider.provider";
    private static Integer BOOK_CODE = 0;
    public MyContentProvider() {
        matcher.addURI(AUTHORITIES, "tb_books", BOOK_CODE);
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    private String getTable(Uri uri) {
        String tableName = "";
        if(matcher.match(uri) == BOOK_CODE)tableName = "tb_books";
        return tableName;
    }

    @Override
    public boolean onCreate() {
        //获取Sqlite数据库
        SqliteHelper sqliteHelper = new SqliteHelper(
                getContext(),
                "bookDB.db",
                null,
                1
        );
        bookDB = sqliteHelper.getWritableDatabase();
        //插入数据
        ContentValues contentValuesBooks = new ContentValues();
        contentValuesBooks.put("name", "book1");
        bookDB.insert("tb_books", null, contentValuesBooks);
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String table = getTable(uri);
        if(table.isEmpty()){
            return null;
        }else {
            long insert = bookDB.insert(table, null, values);
            if(insert > 0)return uri;
            else return null;
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String table = getTable(uri);
        if(table.isEmpty()){
            return -1;
        }else {
            int delete = bookDB.delete(table, selection, selectionArgs);
            return delete;
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        String table = getTable(uri);
        if(table.isEmpty()){
            return null;
        }else {
            Cursor cursor = bookDB.query(table, projection, selection, selectionArgs, null, null,sortOrder);
            return cursor;
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        String table = getTable(uri);
        if(table.isEmpty()){
            return -1;
        }else {
            int update = bookDB.update(table, values, selection, selectionArgs);
            return update;
        }
    }
}