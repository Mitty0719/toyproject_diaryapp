package com.example.diaryapplication.Fragment;

import android.icu.util.DateInterval;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

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

        // Inflate the layout for this fragment
        return view;
    }
}