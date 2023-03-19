package com.example.trackme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackme.R;
import com.example.trackme.cards;
import com.example.trackme.data.model.Transaction;
import com.example.trackme.holder.cardHolder;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<cardHolder>{

    public CardAdapter(Context context, List<cards> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }

    Context context;
    List<cards> transactionList;
    @NonNull
    @Override
    public cardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);

        return new cardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cardHolder holder, int position) {
        cards card = transactionList.get(position);
        holder.setCardDescription(card.getDescription());
        holder.setAmount(Double.valueOf(card.getAmount()));
        holder.setDate(card.getDate());
        holder.setTitle(card.getTitle());
        holder.setType(card.getExpId());
        holder.setCategory(card.getCatId());

    }



    @Override
    public int getItemCount() {
        return transactionList.size();
    }
}
