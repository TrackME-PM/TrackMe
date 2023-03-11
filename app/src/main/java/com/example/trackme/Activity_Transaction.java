package com.example.trackme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.trackme.adapter.CardAdapter;
import com.example.trackme.databinding.ActivityTransactionBinding;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Activity_Transaction extends AppCompatActivity {

    TextView catName;

    public static String Description = "CardDescription";
    public static String Amount = "CardAmount";

    TextView cardDesc, cardAmt, cardDate;
    private String desc;
    private int amt;

    cards card;
    ListView listView;
    CardView catAll, catFood, catTravel,catStat, catStaff, catOther;
    CardAdapter cardAdapter;
    List<cards> cardsList, tempList;
    RecyclerView recyclerView;

    ArrayAdapter<CharSequence> adapterItems;
    ActivityTransactionBinding activityTransactionBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tempList = new ArrayList<>();
        super.onCreate(savedInstanceState);
        activityTransactionBinding = ActivityTransactionBinding.inflate(getLayoutInflater());
        setContentView(activityTransactionBinding.getRoot());


        activityTransactionBinding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_Transaction.this, Activity_HomePage.class));
                finish();
            }
        });

        catAll = findViewById(R.id.catAll);
        catStat = findViewById(R.id.catStat);
        catTravel = findViewById(R.id.catTravel);
        catStaff = findViewById(R.id.catStaff);
        catFood = findViewById(R.id.catFood);
        catOther = findViewById(R.id.catOther);

        activityTransactionBinding.catFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                catFood.setBackgroundResource(R.drawable.category_card_border);
                tempList.clear();
                for(cards card: cardsList){
                    if(card.getCatId() == 1){
                        tempList.add(card);
                    }
                }
                recyclerView = findViewById(R.id.itemsRecycler);
                cardAdapter = new CardAdapter(Activity_Transaction.this, tempList);
                recyclerView.setAdapter(cardAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Transaction.this));
            }
        });
        activityTransactionBinding.catTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                catTravel.setBackgroundResource(R.drawable.category_card_border);
                tempList.clear();
                for(cards card: cardsList){
                    if(card.getCatId() == 2){
                        tempList.add(card);
                    }
                }
                recyclerView = findViewById(R.id.itemsRecycler);
                cardAdapter = new CardAdapter(Activity_Transaction.this, tempList);
                recyclerView.setAdapter(cardAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Transaction.this));
            }
        });

        activityTransactionBinding.catStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                catStat.setBackgroundResource(R.drawable.category_card_border);
                tempList.clear();
                for(cards card: cardsList){
                    if(card.getCatId() == 3){
                        tempList.add(card);
                    }
                }
                recyclerView = findViewById(R.id.itemsRecycler);
                cardAdapter = new CardAdapter(Activity_Transaction.this, tempList);
                recyclerView.setAdapter(cardAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Transaction.this));
            }
        });

        activityTransactionBinding.catStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                catStaff.setBackgroundResource(R.drawable.category_card_border);
                tempList.clear();
                for(cards card: cardsList){
                    if(card.getCatId() == 4){
                        tempList.add(card);
                    }
                }
                recyclerView = findViewById(R.id.itemsRecycler);
                cardAdapter = new CardAdapter(Activity_Transaction.this, tempList);
                recyclerView.setAdapter(cardAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Transaction.this));
            }
        });

        activityTransactionBinding.catOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                catOther.setBackgroundResource(R.drawable.category_card_border);
                tempList.clear();
                for(cards card: cardsList){
                    if(card.getCatId() == 5){
                        tempList.add(card);
                    }
                }
                recyclerView = findViewById(R.id.itemsRecycler);
                cardAdapter = new CardAdapter(Activity_Transaction.this, tempList);
                recyclerView.setAdapter(cardAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Transaction.this));
            }
        });

        activityTransactionBinding.catAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                catAll.setBackgroundResource(R.drawable.category_card_border);

                recyclerView = findViewById(R.id.itemsRecycler);
                cardAdapter = new CardAdapter(Activity_Transaction.this, cardsList);
                recyclerView.setAdapter(cardAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Transaction.this));
            }
        });




//        cardDesc = findViewById(R.id.description);
//        cardAmt = findViewById(R.id.amount);
//
//        Intent i = getIntent();
//        desc = i.getStringExtra(Description);
//        amt = i.getIntExtra(Amount, 0);
//
//
//        cardDesc.setText(desc);
//        cardAmt.setText(amt);
//        cardDate.setText("10-03-2023");

        cardsList = new ArrayList<>();
        cards card1 = new cards("Tea",500,"23-02-2023", 1,1);
        cards card2 = new cards("Pantry",800,"28-02-2023", 1,1);
        cards card3 = new cards("Project",500,"01-03-2023", 0);
        cards card4 = new cards("Staff",1000,"27-04-2023", 4, 1);
        cards card5 = new cards("Tea",1000,"27-04-2023", 1,1);
        cards card6 = new cards("Pantry",1000,"27-04-2023", 1,1);
        cards card7 = new cards("Project",1000,"27-04-2023", 0);
        cards card8 = new cards("Stationary",1000,"27-04-2023",3, 1);
        cards card9 = new cards("Travel",1000,"27-04-2023",2, 1);

        cardsList.add(card1);
        cardsList.add(card2);
        cardsList.add(card3);
        cardsList.add(card4);
        cardsList.add(card5);
        cardsList.add(card6);
        cardsList.add(card7);
        cardsList.add(card8);
        cardsList.add(card9);


//       for(cards card : cardsList) {
//            ImageView img;
//            img = findViewById(R.id.categoryIcon);
//            int catId = card.getCatId();
//            if(catId == 1){
//                img.setImageResource(R.drawable.food_icon);
//            }
//            else if(catId == 2){
//                img.setImageResource(R.drawable.travel_icon);
//            }
//            else if(catId == 3){
//                img.setImageResource(R.drawable.stationary_icon);
//            }
//            else if(catId == 4){
//                img.setImageResource(R.drawable.staff_icon);
//            }
//            else if(catId == 5){
//                img.setImageResource(R.drawable.other_icon);
//            }
//        }
//        Intent intent = getIntent();
//        if(intent != null)
//            cardsList = (List<cards>) intent.getSerializableExtra("TransactionList");


        recyclerView = findViewById(R.id.itemsRecycler);
        cardAdapter = new CardAdapter(Activity_Transaction.this, cardsList);
        recyclerView.setAdapter(cardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Transaction.this));

    }
}