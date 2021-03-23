package com.example.diaryapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;

import com.example.diaryapplication.DTO.DiaryDTO;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MySQLiteClass {
    SQLiteDatabase database;
    DatabaseHelper databaseHelper;

    public static String DB_NAME = "application.db";
    public static int DB_VERSION = 1;

    public MySQLiteClass(Context context){
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
    }

    public void insertDiary(DiaryDTO diary){
        String sql = "INSERT INTO diary(date, weather, content) " +
                "VALUES( '" +
                diary.getWeather() + "', '" +
                diary.getContent() + "', '" +
                diary.getDate() +
                "')";
        database.execSQL(sql);
    }
    public void deleteDiary(String date){
        String sql = "DELETE FROM diary " +
                "WHERE date = " + date;
        database.execSQL(sql);
    }
    public void updateDiary(DiaryDTO diary){
        String sql = "UPDATE diary SET " +
                "weather = '"+ diary.getWeather() + "', "+
                "content = '"+ diary.getContent() + "' "+
                "WHERE date = '" + diary.getDate() + "' ";
        database.execSQL(sql);
    }
    public DiaryDTO getDiaryById(int id){
        String sql = "SELECT * FROM diary " +
                "WHERE "+ id;
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToNext();
        int getId = Integer.parseInt(cursor.getString(0));
        String getWeather = cursor.getString(1);
        String getContent = cursor.getString(2);
        String getDate = cursor.getString(3);

        DiaryDTO diary = new DiaryDTO(getId, getWeather, getContent, getDate);

        return diary;
    }
    public DiaryDTO getDiaryByDate(int id){
        String sql = "SELECT * FROM diary " +
                "WHERE "+ id;
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToNext();
        int getId = Integer.parseInt(cursor.getString(0));
        String getWeather = cursor.getString(1);
        String getContent = cursor.getString(2);
        String getDate = cursor.getString(3);

        DiaryDTO diary = new DiaryDTO(getId, getWeather, getContent, getDate);

        return diary;
    }
    public ArrayList<DiaryDTO> getAllDiary(){
        ArrayList<DiaryDTO> diaryList = new ArrayList<>();
        String sql = "SELECT * FROM diary";
        Cursor cursor = database.rawQuery(sql, null);

        for(int i=0; i<cursor.getCount(); i++) {
            cursor.moveToNext();
            int getId = Integer.parseInt(cursor.getString(0));
            String getWeather = cursor.getString(1);
            String getContent = cursor.getString(2);
            String getDate = cursor.getString(3);
            DiaryDTO diary = new DiaryDTO(getId, getWeather, getContent, getDate);
            diaryList.add(diary);
        }

        return diaryList;
    }

    //databaseHelper Class
    public class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(@Nullable Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = "CREATE TABLE IF NOT EXISTS diary( " +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "weather TEXT, " +
                    "content TEXT, " +
                    "date TEXT" +
                    ")"; //추가 내용있으면 table drop하고 다시 만들기!
            db.execSQL(sql);
        }

        @Override
        public void onOpen(SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (newVersion > 1) {
                db.execSQL("DROP TABLE IF EXISTS emp");
            }
        }
    }
}
