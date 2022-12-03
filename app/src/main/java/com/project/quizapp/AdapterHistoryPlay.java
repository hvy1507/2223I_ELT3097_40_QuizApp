package com.project.quizapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterHistoryPlay extends RecyclerView.Adapter<AdapterHistoryPlay.ViewHolder> {
    Context context;
    List<Result> resultList;


    public AdapterHistoryPlay(Context context, List<Result> list) {
        this.context = context;
        this.resultList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.items_history_play, parent, false);
        return new ViewHolder(view);
    }

    // Gán data
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result result = resultList.get(position);
        holder.txtDate.setText(result.getDate());
        holder.txtLevel.setText(result.getLevel());
        holder.txtMon.setText(result.getMonHoc());
        holder.txtResult.setText(result.getResult());
    }


    @Override
    public int getItemCount() {
        return resultList.size();
    }

    // Ánh xạ
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtResult, txtMon, txtLevel, txtDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtResult = itemView.findViewById(R.id.txtResult);
            txtMon = itemView.findViewById(R.id.txtMonHoc);
            txtLevel = itemView.findViewById(R.id.txtLevel);
            txtDate = itemView.findViewById(R.id.txtDate);
        }
    }
}
