package com.example.diaryapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.diaryapplication.CheckDiaryActivity;
import com.example.diaryapplication.MySharedPreference;
import com.example.diaryapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingmenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingmenuFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView loginBtn, joinBtn, logoutBtn, checkDiaryBtn, backupBtn, questionBtn, demandBtn;

    private MySharedPreference sharedPreference;

    public SettingmenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingmenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingmenuFragment newInstance(String param1, String param2) {
        SettingmenuFragment fragment = new SettingmenuFragment();
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
        View view = inflater.inflate(R.layout.fragment_settingmenu, container, false);

        sharedPreference = new MySharedPreference();

        loginBtn = view.findViewById(R.id.set_loginBtn);
        joinBtn = view.findViewById(R.id.set_joinBtn);
        logoutBtn = view.findViewById(R.id.set_logoutBtn);
        checkDiaryBtn = view.findViewById(R.id.set_checkDiaryBtn);
        backupBtn = view.findViewById(R.id.set_backupBtn);
        questionBtn = view.findViewById(R.id.set_questionBtn);
        demandBtn = view.findViewById(R.id.set_demandBtn);

        boolean isLogin = false;
        try{
            String loginData = sharedPreference.getPreference(getActivity().getApplicationContext(), "email");
            if(loginData != null){
                isLogin = true;
            }
        }catch(Exception e){
            isLogin = false;
        }

        if(isLogin){
            loginBtn.setVisibility(View.GONE);
            joinBtn.setVisibility(View.GONE);
            logoutBtn.setVisibility(View.VISIBLE);
        }else{
            loginBtn.setVisibility(View.VISIBLE);
            joinBtn.setVisibility(View.VISIBLE);
            logoutBtn.setVisibility(View.GONE);
        }

        logoutBtn.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.set_checkDiaryBtn:
                Intent intent = new Intent(getActivity().getApplicationContext(), CheckDiaryActivity.class);
                startActivity(intent);
                break;
            case R.id.set_logoutBtn:
                sharedPreference.removePreference(getActivity().getApplicationContext(), "email");
                getActivity().recreate();
                break;
        }
    }
}