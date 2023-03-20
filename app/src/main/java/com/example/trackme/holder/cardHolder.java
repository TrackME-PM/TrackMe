package com.example.trackme.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackme.R;

import java.util.Objects;

public class cardHolder extends RecyclerView.ViewHolder {

    TextView trTitle, desc, amt, curDate;
    ImageView img, type;
    boolean visible = false;
    public cardHolder(@NonNull View itemView) {
        super(itemView);
        trTitle = itemView.findViewById(R.id.title) ;
        desc = itemView.findViewById(R.id.description);
        amt = itemView.findViewById(R.id.amount);
        curDate = itemView.findViewById(R.id.date);
        img = itemView.findViewById(R.id.categoryIcon);
        type = itemView.findViewById(R.id.typeArr);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!visible) {
                    desc.setVisibility(View.VISIBLE);
                    visible = true;
                }else{
                    desc.setVisibility(View.GONE);
                    visible = false;
                }

            }
        });

    }


    public void setTitle(String title) {
        trTitle.setText(title);
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

    public void setCategory(String catId){

        switch (catId){
            case "1":
                img.setImageResource(R.drawable.food_icon);
                break;
            case "2":
                img.setImageResource(R.drawable.pantry_icon);
                break;
            case "3":
                img.setImageResource(R.drawable.stationary_icon);
                break;
            case "4":
                img.setImageResource(R.drawable.travel_icon);
                break;
            case "5":
                img.setImageResource(R.drawable.staff_icon);
                break;
            case "6":
                img.setImageResource(R.drawable.other_icon);
                break;
            default:
                img.setImageResource(R.drawable.income_icon);
                break;

        }
    }
    public void setType(String  expId){
        if(Objects.equals(expId, "2")){
            type.setImageResource(R.drawable.arr_inc);
        }
        else{
            type.setImageResource(R.drawable.arr_exp);
        }
    }
}
