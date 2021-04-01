package com.example.diaryapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import android.os.Bundle;

import com.example.diaryapplication.DTO.DiaryDTO;
import com.example.diaryapplication.Fragment.CheckDiaryAdapter;

import java.util.ArrayList;

public class CheckDiaryActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    LayoutManager layoutManager;
    CheckDiaryAdapter diaryAdapter;
    MySQLiteClass sqliteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_diary);
        sqliteDB = new MySQLiteClass(this);

        recyclerview = findViewById(R.id.check_recyclerview);
        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        ArrayList<DiaryDTO> allDiary = sqliteDB.getAllDiary();
        diaryAdapter = new CheckDiaryAdapter(allDiary);

        recyclerview.setAdapter(diaryAdapter);
        recyclerview.setLayoutManager(layoutManager);

    }
}