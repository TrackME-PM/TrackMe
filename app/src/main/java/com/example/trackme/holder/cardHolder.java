package com.example.trackme.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackme.R;

public class cardHolder extends RecyclerView.ViewHolder {

    TextView desc, amt, curDate;
    ImageView img, type;
    public cardHolder(@NonNull View itemView) {
        super(itemView);
        desc = itemView.findViewById(R.id.description);
        amt = itemView.findViewById(R.id.amount);
        curDate = itemView.findViewById(R.id.date);
        img = itemView.findViewById(R.id.categoryIcon);
        type = itemView.findViewById(R.id.typeArr);
    }
    public void setCardDescription(String description){
        desc.setText(description);
    }

    public void setAmount(Double amount) {
        String num = Double.toString(amount);
        amt.setText(num);
    }

    public void setDate(String date) {
        curDate.setText(date);
    }

    public void setCategory(int catId){
        switch (catId){
            case 1:
                img.setImageResource(R.drawable.food_icon);
                break;
            case 2:
                img.setImageResource(R.drawable.pantry_icon);
                break;
            case 3:
                img.setImageResource(R.drawable.stationary_icon);
                break;
            case 4:
                img.setImageResource(R.drawable.travel_icon);
                break;
            case 5:
                img.setImageResource(R.drawable.staff_icon);
                break;
            case 6:
                img.setImageResource(R.drawable.other_icon);
                break;
            default:
                img.setImageResource(R.drawable.income_icon);
                break;

        }
    }
    public void setType(int expId){
        if(expId == 2){
            type.setImageResource(R.drawable.arr_inc);
        }
        else{
            type.setImageResource(R.drawable.arr_exp);
        }
    }
}
