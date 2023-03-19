package com.example.trackme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;



import com.example.trackme.adapter.CardAdapter;
import com.example.trackme.data.model.Category;
import com.example.trackme.data.model.Transaction;
import com.example.trackme.databinding.ActivityTransactionBinding;
import com.example.trackme.holder.cardHolder;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Transaction extends AppCompatActivity {

    TextView catName;

    public static String Description = "CardDescription";
    public static String Amount = "CardAmount";
    public static String Category = "CardCategory";
    public static String Expense = "CardExpense";

    TextView cardDesc, cardAmt, cardDate;
    private String desc, title, amt, date ,catId, expId;
    Integer category, exp;
    CardView catAll, catFood, catTravel,catStat, catStaff, catOther;
    CardAdapter cardAdapter;
    List<Transaction> allTransactionList, transactionList;
    List<cards> cardsList, tempList;
    cardHolder holder;
    RecyclerView recyclerView;

    ArrayAdapter<CharSequence> adapterItems;
    ActivityTransactionBinding activityTransactionBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTransactionBinding = ActivityTransactionBinding.inflate(getLayoutInflater());
        setContentView(activityTransactionBinding.getRoot());

        cardsList = new ArrayList<>();
        tempList = new ArrayList<>();
       RetrofitClient.getRetrofitInstance().apiInterface.getTransactions().enqueue(new Callback<List<Transaction>>() {
           @Override
           public void onResponse(@NonNull Call<List<com.example.trackme.data.model.Transaction>> call, @NonNull Response<List<Transaction>> response) {
               allTransactionList = response.body();
//               cardAdapter = new CardAdapter(Activity_Transaction.this, allTransactionList);
//               recyclerView.setAdapter(cardAdapter);


               for(Transaction transaction : allTransactionList){
                   title = transaction.getName();
                   desc = transaction.getDescription();
                   amt = transaction.getAmount();
                   date = transaction.getDate();
                   date = date.substring(0, 10);
                   catId = transaction.getCategoryId();
                   expId = transaction.getTransactionTypeId();
                   Log.e("api","onSuccess: " + title);
                   Log.e("api","onSuccess: " + amt);
                   Log.e("api","onSuccess: " + date);
                   Log.e("api","onSuccess: " + desc);
                   Log.e("api","onSuccess: " + catId);

                   cards card = new cards(title, desc, amt, date, catId, expId);
                   cardsList.add(card);
               }
               recyclerView = findViewById(R.id.itemsRecycler);
               cardAdapter = new CardAdapter(Activity_Transaction.this, cardsList);
               recyclerView.setAdapter(cardAdapter);
               recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Transaction.this));
              // Toast.makeText(Activity_Transaction.this, Integer.toString(cardsList.size()), Toast.LENGTH_SHORT).show();


           }

           @Override
           public void onFailure(@NonNull Call<List<Transaction>> call, Throwable t) {
                Log.e("api","onFailure: " + t.getLocalizedMessage());
           }
       });


        activityTransactionBinding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Transaction.this, Activity_HomePage.class);

                startActivity(intent);
                finish();
            }
        });




//        catAll = findViewById(R.id.catAll);
//        catStat = findViewById(R.id.catStat);
//        catTravel = findViewById(R.id.catTravel);
//        catStaff = findViewById(R.id.catStaff);
//        catFood = findViewById(R.id.catFood);
//        catOther = findViewById(R.id.catOther);
//
//        activityTransactionBinding.catFood.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                catFood.setBackgroundResource(R.drawable.category_card_border);
//                RetrofitClient.getRetrofitInstance().apiInterface.getTransactionsByCategory(1).enqueue(new Callback<com.example.trackme.data.model.Category>() {
//                    @Override
//                    public void onResponse(Call<com.example.trackme.data.model.Category> call, Response<com.example.trackme.data.model.Category> response) {
//                        Log.e("category", "response" + response.body().getTransaction());
//                    }
//
//                    @Override
//                    public void onFailure(Call<com.example.trackme.data.model.Category> call, Throwable t) {
//
//                    }
//                });
//
//            }
//        });
//        activityTransactionBinding.catTravel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                catTravel.setBackgroundResource(R.drawable.category_card_border);
//                tempList.clear();
//                for(Transaction transaction: allTransactionList){
//                    if(Integer.parseInt(transaction.getCategoryId()) == 4){
//                        title = transaction.getName();
//                        desc = transaction.getDescription();
//                        amt = transaction.getAmount();
//                        date = transaction.getDate();
//                        date = date.substring(0, 10);
//                        catId = transaction.getCategoryId();
//                        expId = transaction.getTransactionTypeId();
//
//                        cards card = new cards(title, desc, amt, date, catId, expId);
//                        tempList.add(card);
//                    }
//                }
//                recyclerView = findViewById(R.id.itemsRecycler);
//                cardAdapter = new CardAdapter(Activity_Transaction.this, tempList);
//                recyclerView.setAdapter(cardAdapter);
//                recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Transaction.this));
//            }
//        });
//
//        activityTransactionBinding.catStat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                catStat.setBackgroundResource(R.drawable.category_card_border);
//                tempList.clear();
//                for(Transaction transaction: allTransactionList){
//                    if(Integer.parseInt(transaction.getCategoryId()) == 3){
//                        title = transaction.getName();
//                        desc = transaction.getDescription();
//                        amt = transaction.getAmount();
//                        date = transaction.getDate();
//                        date = date.substring(0, 10);
//                        catId = transaction.getCategoryId();
//                        expId = transaction.getTransactionTypeId();
//
//                        cards card = new cards(title, desc, amt, date, catId, expId);
//                        tempList.add(card);
//                    }
//                }
//                recyclerView = findViewById(R.id.itemsRecycler);
//                cardAdapter = new CardAdapter(Activity_Transaction.this, tempList);
//                recyclerView.setAdapter(cardAdapter);
//                recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Transaction.this));
//            }
//        });
//
//        activityTransactionBinding.catStaff.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                catStaff.setBackgroundResource(R.drawable.category_card_border);
//                tempList.clear();
//                for(Transaction transaction: allTransactionList){
//                    if(Integer.parseInt(transaction.getCategoryId()) == 5){
//                        title = transaction.getName();
//                        desc = transaction.getDescription();
//                        amt = transaction.getAmount();
//                        date = transaction.getDate();
//                        date = date.substring(0, 10);
//                        catId = transaction.getCategoryId();
//                        expId = transaction.getTransactionTypeId();
//
//                        cards card = new cards(title, desc, amt, date, catId, expId);
//                        tempList.add(card);
//                    }
//                }
//                recyclerView = findViewById(R.id.itemsRecycler);
//                cardAdapter = new CardAdapter(Activity_Transaction.this, tempList);
//                recyclerView.setAdapter(cardAdapter);
//                recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Transaction.this));
//            }
//        });
//
//        activityTransactionBinding.catOther.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                catOther.setBackgroundResource(R.drawable.category_card_border);
//                tempList.clear();
//                for(Transaction transaction: allTransactionList){
//                    if(Integer.parseInt(transaction.getCategoryId()) == 6){
//                        title = transaction.getName();
//                        desc = transaction.getDescription();
//                        amt = transaction.getAmount();
//                        date = transaction.getDate();
//                        date = date.substring(0, 10);
//                        catId = transaction.getCategoryId();
//                        expId = transaction.getTransactionTypeId();
//
//                        cards card = new cards(title, desc, amt, date, catId, expId);
//                        tempList.add(card);
//                    }
//                }
//                recyclerView = findViewById(R.id.itemsRecycler);
//                cardAdapter = new CardAdapter(Activity_Transaction.this, tempList);
//                recyclerView.setAdapter(cardAdapter);
//                recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Transaction.this));
//            }
//        });
//
//        activityTransactionBinding.catAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                catAll.setBackgroundResource(R.drawable.category_card_border);
//
//                recyclerView = findViewById(R.id.itemsRecycler);
//                cardAdapter = new CardAdapter(Activity_Transaction.this, cardsList);
//                recyclerView.setAdapter(cardAdapter);
//                recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Transaction.this));
//            }
//        });
//
//        activityTransactionBinding.catPantry.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                catAll.setBackgroundResource(R.drawable.category_card_border);
//                tempList.clear();
//                for(Transaction transaction: allTransactionList){
//                    if(Integer.parseInt(transaction.getCategoryId()) == 2){
//                        title = transaction.getName();
//                        desc = transaction.getDescription();
//                        amt = transaction.getAmount();
//                        date = transaction.getDate();
//                        date = date.substring(0, 10);
//                        catId = transaction.getCategoryId();
//                        expId = transaction.getTransactionTypeId();
//
//                        cards card = new cards(title, desc, amt, date, catId, expId);
//                        tempList.add(card);
//                    }
//                }
//                recyclerView = findViewById(R.id.itemsRecycler);
//                cardAdapter = new CardAdapter(Activity_Transaction.this, tempList);
//                recyclerView.setAdapter(cardAdapter);
//                recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Transaction.this));
//            }
//        });

//



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

//        transactionList = new ArrayList<>();
//        Transaction card1 = new Transaction("Tea",500,"23-02-2023", 1,1);
//        Transaction card2 = new Transaction("Pantry",800,"28-02-2023", 2,1);
//        Transaction card3 = new Transaction("Project",500,"01-03-2023", 2);
//        Transaction card4 = new Transaction("Staff",1000,"27-04-2023", 5, 1);
//        Transaction card5 = new Transaction("Tea",1000,"27-04-2023", 1,1);
//        Transaction card6 = new Transaction("Pantry",1000,"27-04-2023", 2,1);
//        Transaction card7 = new Transaction("Project",1000,"27-04-2023", 2);
//        Transaction card8 = new Transaction("Stationary",1000,"27-04-2023",3, 1);
//        Transaction card9 = new Transaction("Travel",1000,"27-04-2023",4, 1);
//
//
//        transactionList.add(card1);
//        transactionList.add(card2);
//        transactionList.add(card3);
//        transactionList.add(card4);
//        transactionList.add(card5);
//        transactionList.add(card6);
//        transactionList.add(card7);
//        transactionList.add(card8);
//        transactionList.add(card9);


//           for(Transaction card : cardsList) {
//                ImageView img;
//                img = findViewById(R.id.categoryIcon);
//                int catId = card.getCategoryId();
//                if(catId == 1){
//                    img.setImageResource(R.drawable.food_icon);
//                }
//                else if(catId == 2){
//                    img.setImageResource(R.drawable.travel_icon);
//                }
//                else if(catId == 3){
//                    img.setImageResource(R.drawable.stationary_icon);
//                }
//                else if(catId == 4){
//                    img.setImageResource(R.drawable.staff_icon);
//                }
//                else if(catId == 5){
//                    img.setImageResource(R.drawable.other_icon);
//                }
//            }
//        Intent intent = getIntent();
//        if(intent != null)
//            cardsList = (List<cards>) intent.getSerializableExtra("TransactionList");

//        Intent intent = getIntent();
//
//        int expId = intent.getIntExtra(Expense, 0);
//        Toast.makeText(Activity_Transaction.this, "Transaction " + expId, Toast.LENGTH_SHORT).show();
//
//        String cardDesc = intent.getStringExtra(Description);
//        int cardAmt = intent.getIntExtra(Amount, 0);
//        int catId = intent.getIntExtra(Category, 0);
//        Toast.makeText(Activity_Transaction.this, "Transaction " + cardDesc, Toast.LENGTH_SHORT).show();
//        Toast.makeText(Activity_Transaction.this, "Transaction " + cardAmt, Toast.LENGTH_SHORT).show();
//        Toast.makeText(Activity_Transaction.this, "Transaction " + catId, Toast.LENGTH_SHORT).show();
//        if(cardDesc != "null" && cardAmt != -1 && catId != -1 && expId != -1) {
//
//            Transaction card = new Transaction(cardDesc, cardAmt, "11-03-2023",catId, expId);
//            transactionList.add(card);
//
//        }
//        Collections.reverse(transactionList);


//        recyclerView.smoothScrollToPosition(allTransactionList.size()-1);



    }
}