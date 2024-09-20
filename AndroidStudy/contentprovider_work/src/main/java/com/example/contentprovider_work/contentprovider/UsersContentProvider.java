package com.example.contentprovider_work.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.example.contentprovider_work.utils.db.DBHelper;

public class UsersContentProvider extends ContentProvider {
    private static SQLiteDatabase userDB;
    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static String AUTHORITIES = "com.example.contentprovider_work.contentprovider.UsersContentProvider.lzh.provider";
    private static int USER_CODE = 0;
    public UsersContentProvider() {
        uriMatcher.addURI(AUTHORITIES, "tb_users", USER_CODE);
    }

    private String getTable(Uri uri) {
        String tableName = "";
        if(uriMatcher.match(uri) == USER_CODE)tableName = "tb_users";
        return tableName;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public boolean onCreate() {
        DBHelper dbHelper = DBHelper.getInstance(this.getContext());
        userDB = dbHelper.openWriteLink();
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String table = getTable(uri);
        if(table.isEmpty()){
            return null;
        }else {
            long insert = userDB.insert(table, null, values);
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
            int delete = userDB.delete(table, selection, selectionArgs);
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
            Cursor cursor = userDB.query(table, projection, selection, selectionArgs, null, null,sortOrder);
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
            int update = userDB.update(table, values, selection, selectionArgs);
            return update;
        }
    }
}