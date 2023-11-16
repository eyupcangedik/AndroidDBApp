package com.example.databaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public final static  String DATABASE_NAME = "USER.DB";
    public final static  String TABLE_NAME = "USERS";
    public final static  String COL_ID = "ID";
    public final static  String COL_USERNAME = "USERNAME";
    public final static  String COL_PASSWORD = "PASSWORD";
    public final static  String COL_EMAIL = "EMAIL";
    public final static  String COL_PHONE = "PHONE";
    SQLiteDatabase database;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY, USERNAME TEXT NOT NULL, PASSWORD TEXT NOT NULL, EMAIL TEXT NOT NULL, PHONE TEXT NOT NULL)";
        db.execSQL(CREATE_TABLE_QUERY);
        this.database = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String UPGRADE_QUERY = "DROP TABLE IF EXISTS".toString() + " " + TABLE_NAME;
        //String UPGRADE_QUERY = "DROP TABLE IF EXISTS" + TABLE_NAME;
        db.execSQL(UPGRADE_QUERY);
        this.onCreate(db);
    }

    public boolean insertUser(String email, String username, String password, String phone) {

        this.database = getWritableDatabase();

        String query = "select * from " + TABLE_NAME;
        Cursor cursor = database.rawQuery(query,null);
        int id = cursor.getCount();

        ContentValues value = new ContentValues();
        value.put(COL_ID, id+1);
        value.put(COL_EMAIL, email);
        value.put(COL_USERNAME, username);
        value.put(COL_PASSWORD, password);
        value.put(COL_PHONE, phone);

        Long result = database.insert(TABLE_NAME, null, value);

        database.close();

        if(result == -1){
            return false;
        }

        else{
            return true;
        }
    }

        public String findUser(String username) {
            this.database = getReadableDatabase();

            String query = "Select " + "password" + " from " + TABLE_NAME + " WHERE USERNAME='" + username+"'";
            Cursor cursor =  database.rawQuery(query,null);

            if(cursor.moveToFirst()){      // True ise kayıt vardır.
               database.close();
               return cursor.getString(0);
            }
            else{
                database.close();
                return "";
            }
        }

    public int resetDatabase(){
        this.database = getReadableDatabase();
        int result = database.delete(TABLE_NAME,null,null);

        return result;
    }
}