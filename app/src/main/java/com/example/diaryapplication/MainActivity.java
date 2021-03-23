package com.example.diaryapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.diaryapplication.DTO.DiaryDTO;
import com.example.diaryapplication.Fragment.SettingmenuFragment;
import com.example.diaryapplication.Fragment.WriteboardFragment;

public class MainActivity extends FragmentActivity {
    private ViewPager2 mainPagerView;
    private SlidePagerAdapter pagerAdapter;
    MySQLiteClass sqliteDB;

    private static final int NUM_PAGES = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPagerView = findViewById(R.id.mainPagerView);
        pagerAdapter = new SlidePagerAdapter(this);
        mainPagerView.setAdapter(pagerAdapter);
        sqliteDB = new MySQLiteClass(this);
        
        DiarySaveThread thread = new DiarySaveThread(); //Thread가 Fragment 생성되기 전에 실행되 nullReferenceExceptino을 보냄
        thread.start();
    }

    @Override
    public void onBackPressed() {
        if(mainPagerView.getCurrentItem() == 0){
            super.onBackPressed();
        }else{
            mainPagerView.setCurrentItem(mainPagerView.getCurrentItem() - 1);
        }
    }

    private class SlidePagerAdapter extends FragmentStateAdapter {

        public SlidePagerAdapter(FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Fragment fragment;
            switch(position){
                case 0:
                    fragment = new WriteboardFragment();
                    break;
                default:
                    fragment = new SettingmenuFragment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }

    //자동저장 Thread
    public class DiarySaveThread extends Thread{

        public DiarySaveThread(){}
        @Override
        public void run() {
            while(true){
                try {
                    WriteboardFragment fragment = new WriteboardFragment();
                    DiaryDTO diary = fragment.getDiaryData();

//                    String content = diaryArea.getText().toString();
//                    String weather = "맑음";
//                    String date = monthDay.getText().toString()+"-"+weekDay.getText().toString();
//                    date = date.replace(" ", "");
//                    DiaryDTO diary = new DiaryDTO(weather, content, date);
                    sqliteDB.updateDiary(diary);
                    sleep(3000);
                    Log.i("Thread", "호출");
                    Log.i("month", diary.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    } //DiarySaveThread
}