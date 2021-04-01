package com.example.diaryapplication.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diaryapplication.DTO.DiaryDTO;
import com.example.diaryapplication.R;

import java.util.ArrayList;

public class CheckDiaryAdapter extends RecyclerView.Adapter<CheckDiaryAdapter.ViewHolder> {

    private ArrayList<DiaryDTO> localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView diaryContent, diaryDate, diaryWeather;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            diaryContent = itemView.findViewById(R.id.diaryContent);
            diaryDate = itemView.findViewById(R.id.diaryDate);
            diaryWeather = itemView.findViewById(R.id.diaryWeather);
        }

        public void setItem(DiaryDTO diary){
            diaryContent.setText(diary.getContent());
            diaryDate.setText(diary.getDate());
            diaryDate.setText(diary.getWeather());
        }

    }

    public CheckDiaryAdapter(ArrayList<DiaryDTO> dataSet){
        localDataSet = dataSet;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.diary_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DiaryDTO diary = localDataSet.get(position);
        holder.setItem(diary);
    }


    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
