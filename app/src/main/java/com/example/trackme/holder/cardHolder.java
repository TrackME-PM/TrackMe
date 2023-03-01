package com.example.trackme.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackme.R;

public class cardHolder extends RecyclerView.ViewHolder {

    TextView textView;

    public cardHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.description);
    }
    public void setCardDescription(String description){
        textView.setText(description);
    }
}
