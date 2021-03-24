package com.example.diaryapplication.Fragment;

import android.icu.util.DateInterval;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.diaryapplication.DTO.DiaryDTO;
import com.example.diaryapplication.MainActivity;
import com.example.diaryapplication.MySQLiteClass;
import com.example.diaryapplication.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WriteboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WriteboardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView monthDay, weekDay;
    EditText diaryArea;
    Button saveBtn;
    MySQLiteClass sqliteDB;

    public WriteboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WriteboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WriteboardFragment newInstance(String param1, String param2) {
        WriteboardFragment fragment = new WriteboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_writeboard, container, false);
        monthDay = view.findViewById(R.id.write_monthDay);
        weekDay = view.findViewById(R.id.write_weekDay);
        diaryArea = view.findViewById(R.id.write_diaryArea);
        saveBtn = view.findViewById(R.id.write_saveBtn);
        sqliteDB = new MySQLiteClass(getActivity().getApplicationContext());

        //날짜 설정
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String currentDate = dateFormat.format(date);
        String[] dateArray = currentDate.split("/");
        String year = dateArray[0];
        String month = dateArray[1];
        String day = dateArray[2];

        String[] weekDayList = {"월", "화", "수", "목", "금", "토", "일"};
        String weekday = weekDayList[date.getDay()-1];

        monthDay.setText(month+" 월 "+day+" 일 ");
        weekDay.setText(weekday+"요일");


        //임시 버튼 클릭
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("임시 버튼", "클릭됨");
                String content = diaryArea.getText().toString();
                String weather = "맑음";
                String date = monthDay.getText().toString()+"-"+weekDay.getText().toString();
                date = date.replace(" ", "");
                DiaryDTO diary = new DiaryDTO(date, weather, content);
                sqliteDB.insertDiary(diary);
            }
        });



        // Inflate the layout for this fragment
        return view;
    }// onCreateView

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //나중에 다시켜기
        //DiarySaveThread thread = new DiarySaveThread();
        //thread.start();
    }

    public DiaryDTO getDiaryData(){
        String content = "";
        String weather = "";
        String date = "";

        content = diaryArea.getText().toString();
        weather = "맑음";
        date = monthDay.getText().toString()+"-"+weekDay.getText().toString();
        date = date.replace(" ", "");

        DiaryDTO diary = new DiaryDTO(weather, content, date);

        return diary;
    }


    //자동저장 Thread
    public class DiarySaveThread extends Thread{

        public DiarySaveThread(){}
        @Override
        public void run() {
            while(true){
                try {

                    String content = diaryArea.getText().toString();
                    String weather = "맑음";
                    String date = monthDay.getText().toString()+"-"+weekDay.getText().toString();
                    date = date.replace(" ", "");
                    DiaryDTO diary = new DiaryDTO(weather, content, date);
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