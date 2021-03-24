package com.example.diaryapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
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

    private static final int NUM_PAGES = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPagerView = findViewById(R.id.mainPagerView);
        pagerAdapter = new SlidePagerAdapter(this);
        mainPagerView.setAdapter(pagerAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
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

}