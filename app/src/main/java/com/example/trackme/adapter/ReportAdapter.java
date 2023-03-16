package com.example.trackme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackme.R;
import com.example.trackme.Report;
import com.example.trackme.holder.ReportHolder;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportHolder>{
    Context context;

    public ReportAdapter(Context context, List<Report> rowsList) {
        this.context = context;
        this.rowsList = rowsList;
    }

    List<Report> rowsList;
    @NonNull
    @Override
    public ReportHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_row_layout, parent, false);

        return new ReportHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportHolder holder, int position) {
        Report trData = rowsList.get(position);
        holder.setTitle(trData.getTrTitle());
        holder.setAmount(trData.getTrAmount());
        holder.setTransactionDate(trData.getTrDate());
        holder.setCategory(trData.getTrCategory());
    }

    @Override
    public int getItemCount() {
        return rowsList.size();
    }
}
