package com.example.trackme;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackme.adapter.CardAdapter;
import com.example.trackme.adapter.ViewPagerAdapter;
import com.example.trackme.data.model.Transaction;
import com.example.trackme.databinding.ActivityHomePageBinding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_HomePage extends AppCompatActivity {


    TextView userName, incAmt, expAmt;
    int exp = 0, inc = 0;
    ActivityHomePageBinding binding;

    private String desc, title, amt, date ,catId, expId;
    CardAdapter cardAdapter;

    List<Transaction> allTransactionList;
    List<cards> cardsList, tempList, tempList1;

    List<String> datestring;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        userName = findViewById(R.id.textViewProfile);
        userName.setText("Igmite Solutions");

        cardsList = new ArrayList<>();
        tempList= new ArrayList<>();
        tempList1= new ArrayList<>();


        incAmt = findViewById(R.id.incomeAmt);
        expAmt = findViewById(R.id.expenseAmt);

        RetrofitClient.getRetrofitInstance().apiInterface.getTransactions().enqueue(new Callback<List<Transaction>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<List<Transaction>> call, @NonNull Response<List<Transaction>> response) {
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
                    Log.e("tran","success"+ expId);
                    Log.e("tran","success"+ catId);


                    if(expId.equals("1")){
                        exp += Double.parseDouble(transaction.getAmount());
                    } else if (expId.equals("2")) {
                        inc += Double.parseDouble(transaction.getAmount());
                    }

                    cards card = new cards(title, desc, amt, date, catId, expId);
                    cardsList.add(card);
                }

                incAmt.setText(Double.toString(inc));
                expAmt.setText(Double.toString(exp));

                recyclerView = findViewById(R.id.itemsRecycler);
                cardAdapter = new CardAdapter(Activity_HomePage.this, cardsList);
                recyclerView.setAdapter(cardAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Activity_HomePage.this));
                // Toast.makeText(Activity_Transaction.this, Integer.toString(cardsList.size()), Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onFailure(@NonNull Call<List<Transaction>> call, Throwable t) {
                Log.e("api","onFailure: " + t.getLocalizedMessage());
            }
        });




//        int exp = 0, inc = 0;
//        for(cards card : cardsList) {
//            String date = card.getDate();
//            if (date == "11-03-2023") {
//                tempList.add(card);
//                int num = card.getAmount();
//                if(card.getExpId() == 1){
//                    exp += num;
//                }
//                if(card.getExpId() == 2){
//                    inc += num;
//                }
//            }
//        }
//        incAmt.setText(Integer.toString(inc));
//        expAmt.setText(Integer.toString(exp));

        recyclerView = findViewById(R.id.itemsRecycler);
        cardAdapter = new CardAdapter(Activity_HomePage.this, tempList);
        recyclerView.setAdapter(cardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Activity_HomePage.this));

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startActivity(new Intent(Activity_HomePage.this, AddActivity.class));
                }
                finish();
            }
        }) ;



        binding.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_HomePage.this, Activity_UserProfile.class));
                finish();
            }
        });

        binding.bottomNavView.setBackground(null);

        binding.bottomNavView.setOnItemSelectedListener(item->{
            int id = item.getItemId();
            switch(id) {
                case R.id.navigation_home:
                    break;

                case R.id.navigation_transaction:
                    Intent intent = new Intent(Activity_HomePage.this, Activity_Transaction.class);
//                    intent.putExtra(Activity_Transaction.Description, "null");
//                    intent.putExtra(Activity_Transaction.Amount, -1);
//                    intent.putExtra(Activity_Transaction.Expense, -1);
//                    intent.putExtra(Activity_Transaction.Category, -1);

                    startActivity(intent);
                    finish();
                    break;

                default:

            }
            return true;
        });

        binding.trendCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_HomePage.this, Activity_Report.class));
                finish();
            }
        });


    }



}

