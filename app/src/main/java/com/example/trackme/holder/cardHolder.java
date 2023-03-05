package com.example.trackme.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackme.R;

public class cardHolder extends RecyclerView.ViewHolder {

    TextView desc, amt, curDate;

    public cardHolder(@NonNull View itemView) {
        super(itemView);
        desc = itemView.findViewById(R.id.description);
        amt = itemView.findViewById(R.id.amount);
        curDate = itemView.findViewById(R.id.date);
    }
    public void setCardDescription(String description){
        desc.setText(description);
    }

//    public void setAmount(int amount) {
//        amt.setText(amount);
//    }
//
//    public void setDate(String date) {
//        curDate.setText(date);
//    }
}
