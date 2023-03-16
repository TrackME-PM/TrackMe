package com.example.trackme.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackme.R;

public class ReportHolder extends RecyclerView.ViewHolder{
    TextView trDate, trTitle, trCategory, trAmount, trDue;

    public ReportHolder(@NonNull View itemView) {
        super(itemView);
        trDate = itemView.findViewById(R.id.trDate);
        trTitle = itemView.findViewById(R.id.trTitle);
        trCategory = itemView.findViewById(R.id.trCategory);
        trAmount = itemView.findViewById(R.id.trAmount);
        trDue = itemView.findViewById(R.id.trDue);
    }
    public void setTitle(String title){
        trTitle.setText(title);
    }

    public void setAmount(int amount) {
        String num = Integer.toString(amount);
        trAmount.setText(num);
    }

    public void setTransactionDate(String date) {
        trDate.setText(date);
    }

    public void setDueDate(String date) {
        trDue.setText(date);
    }

    public void setCategory(int catId){
        switch (catId){
            case 1:
                trCategory.setText("Food & Beverages");
                break;
            case 2:
                trCategory.setText("Pantry");
                break;
            case 3:
                trCategory.setText("Stationary");
                break;
            case 4:
                trCategory.setText("Travel");
                break;
            case 5:
                trCategory.setText("Staff");
                break;
            default:
                trCategory.setText("Others");
                break;
        }
    }
}
