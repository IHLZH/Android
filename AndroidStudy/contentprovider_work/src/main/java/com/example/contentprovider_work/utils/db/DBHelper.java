package com.example.contentprovider_work.utils.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.contentprovider_work.entity.User;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper dbHelper;
    private static SQLiteDatabase writeUsersDB;
    private static SQLiteDatabase readUsersDB;
    private static final String TABLE_NAME = "tb_users";
    private static int version = 1;

    public DBHelper(Context context){
        super(context, TABLE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table = "CREATE TABLE IF NOT EXISTS tb_users (" +
                "id INT PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "name VARCHAR(20) NOT NULL," +
                "phone VARCHAR(11) NOT NULL," +
                "address VARCHAR(255) NOT NULL );";
        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static DBHelper getInstance(Context context){
        if(dbHelper == null){
            dbHelper = new DBHelper(context);
        }
        return dbHelper;
    }

    public SQLiteDatabase openWriteLink(){
        if(writeUsersDB == null){
            writeUsersDB = dbHelper.getWritableDatabase();
        }
        return writeUsersDB;
    }

    public SQLiteDatabase openReadLink(){
        if(readUsersDB == null){
            readUsersDB = dbHelper.getReadableDatabase();
        }
        return readUsersDB;
    }

    public void closeLink(){
        if(writeUsersDB != null || writeUsersDB.isOpen()){
            writeUsersDB.close();
        }
        if(readUsersDB != null || readUsersDB.isOpen()){
            readUsersDB.close();
        }
    }

    public long insert(User user){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", user.getName());
        contentValues.put("phone", user.getPhone());
        contentValues.put("address", user.getAddress());
        return writeUsersDB.insert(TABLE_NAME, null, contentValues);
    }

    public int delete(User user){
        int id = user.getId();
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(id)};
        return writeUsersDB.delete(TABLE_NAME, whereClause, whereArgs);
    }

    public int update(User user){
        int id = user.getId();
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(id)};
        String name = user.getName();
        String phone = user.getPhone();
        String address = user.getAddress();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", user.getName());
        contentValues.put("phone", user.getPhone());
        contentValues.put("address", user.getAddress());
        return writeUsersDB.update(TABLE_NAME, contentValues, whereClause, whereArgs);
    }

    public List<User> selectAll(){
        Cursor cursor = writeUsersDB.query(TABLE_NAME, null, null, null, null, null, null);
        List<User> userList = new ArrayList<>();
        while(cursor.moveToNext()){
            User user = new User();
            int idIndex = cursor.getColumnIndex("id");
            int nameIndex = cursor.getColumnIndex("name");
            int phoneIndex = cursor.getColumnIndex("phone");
            int addressIndex = cursor.getColumnIndex("address");
            int id = cursor.getInt(idIndex);
            String name = cursor.getString(nameIndex);
            String phone = cursor.getString(phoneIndex);
            String address = cursor.getString(addressIndex);
            user.setId(id);
            user.setName(name);
            user.setPhone(phone);
            user.setAddress(address);
            userList.add(user);
        }
        return userList;
    }
}
