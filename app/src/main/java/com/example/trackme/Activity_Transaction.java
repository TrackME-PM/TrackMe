package com.example.trackme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.trackme.adapter.CardAdapter;
import com.example.trackme.databinding.ActivityTransactionBinding;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Activity_Transaction extends AppCompatActivity {

//    CardAdapter cardAdapter;
//    List<cards> cardsList;
//    RecyclerView recyclerView;
    ActivityTransactionBinding activityTransactionBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTransactionBinding = ActivityTransactionBinding.inflate(getLayoutInflater());
        setContentView(activityTransactionBinding.getRoot());

        activityTransactionBinding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_Transaction.this, Activity_HomePage.class));
            }
        });
//
//        cardsList = new ArrayList<>();
//        cards card1 = new cards("Tea",500,"23-02-2023", 1);
//        cards card2 = new cards("Pantry",800,"28-02-2023", 1);
//        cards card3 = new cards("Project",500,"01-03-2023", 0);
//        cards card4 = new cards("Staff",1000,"27-04-2023", 1);
//
//        cardsList.add(card1);
//        cardsList.add(card2);
//        cardsList.add(card3);
//        cardsList.add(card4);
//
//        recyclerView = findViewById(R.id.itemsRecycler);
//        cardAdapter = new CardAdapter(Activity_Transaction.this, cardsList);
//        recyclerView.setAdapter(cardAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Transaction.this));

    }
}