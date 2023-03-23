package com.example.trackme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Transaction extends AppCompatActivity {

    TextView catName;

    TextView cardDesc, cardAmt, cardDate;
    private String desc, title, amt, date, catId, expId;

    RelativeLayout catAll, catFood, catTravel, catStat, catStaff, catOther, catInc, catPantry;

    CardAdapter cardAdapter;
    List<Transaction> allTransactionList, transactionList;
    Category category;
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

        ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        RetrofitClient.getRetrofitInstance().apiInterface.getTransactions().enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(@NonNull Call<List<Transaction>> call, @NonNull Response<List<Transaction>> response) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
                allTransactionList = response.body();



                for (Transaction transaction : allTransactionList) {
                    title = transaction.getName();
                    desc = transaction.getDescription();
                    amt = transaction.getAmount();
                    date = transaction.getDate();

                    catId = transaction.getCategoryId();
                    expId = transaction.getTransactionTypeId();

                    Log.e("tran", "success" + expId);
                    Log.e("tran", "success" + catId);

                    cards card = new cards(title, desc, amt, date, catId, expId);
                    cardsList.add(card);
                }
                Collections.reverse(cardsList);
                recyclerView = findViewById(R.id.itemsRecycler);
                cardAdapter = new CardAdapter(Activity_Transaction.this, cardsList);
                recyclerView.setAdapter(cardAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Transaction.this));
                // Toast.makeText(Activity_Transaction.this, Integer.toString(cardsList.size()), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(@NonNull Call<List<Transaction>> call, Throwable t) {
                Log.e("api", "onFailure: " + t.getLocalizedMessage());
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
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


        catAll = findViewById(R.id.catAll);
        catStat = findViewById(R.id.catStat);
        catTravel = findViewById(R.id.catTravel);
        catStaff = findViewById(R.id.catStaff);
        catFood = findViewById(R.id.catFood);
        catOther = findViewById(R.id.catOther);
        catInc = findViewById(R.id.catIncome);
        catPantry = findViewById(R.id.catPantry);

        activityTransactionBinding.catFood.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                changeColor(2);
                tempList.clear();

                RetrofitClient.getRetrofitInstance().apiInterface.getTransactions().enqueue(new Callback<List<Transaction>>() {
                    @Override
                    public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                        if (mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                        transactionList = response.body();

                        for (Transaction transaction : transactionList) {
                            title = transaction.getName();
                            desc = transaction.getDescription();
                            amt = transaction.getAmount();
                            date = transaction.getDate();
                            date = date.substring(0, 10);
                            catId = transaction.getCategoryId();
                            expId = transaction.getTransactionTypeId();

                            if (catId.equals("1")) {
                                cards card = new cards(title, desc, amt, date, catId, expId);
                                tempList.add(card);
                            }

                        }
                        recyclerView = findViewById(R.id.itemsRecycler);
                        cardAdapter = new CardAdapter(Activity_Transaction.this, tempList);
                        recyclerView.setAdapter(cardAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Transaction.this));
                    }

                    @Override
                    public void onFailure(Call<List<Transaction>> call, Throwable t) {
                        if (mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                    }
                });

            }
        });
        activityTransactionBinding.catTravel.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                changeColor(4);
                tempList.clear();
                RetrofitClient.getRetrofitInstance().apiInterface.getTransactions().enqueue(new Callback<List<Transaction>>() {
                    @Override
                    public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                        if (mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                        transactionList = response.body();

                        for (Transaction transaction : transactionList) {
                            title = transaction.getName();
                            desc = transaction.getDescription();
                            amt = transaction.getAmount();
                            date = transaction.getDate();
                            date = date.substring(0, 10);
                            catId = transaction.getCategoryId();
                            expId = transaction.getTransactionTypeId();

                            if (catId.equals("4")) {
                                cards card = new cards(title, desc, amt, date, catId, expId);
                                tempList.add(card);
                            }

                        }
                        recyclerView = findViewById(R.id.itemsRecycler);
                        cardAdapter = new CardAdapter(Activity_Transaction.this, tempList);
                        recyclerView.setAdapter(cardAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Transaction.this));
                    }

                    @Override
                    public void onFailure(Call<List<Transaction>> call, Throwable t) {
                        if (mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                    }
                });

            }
        });

        activityTransactionBinding.catStat.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                changeColor(5);
                tempList.clear();
                RetrofitClient.getRetrofitInstance().apiInterface.getTransactions().enqueue(new Callback<List<Transaction>>() {
                    @Override
                    public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {

                        if (mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                        transactionList = response.body();

                        for (Transaction transaction : transactionList) {
                            title = transaction.getName();
                            desc = transaction.getDescription();
                            amt = transaction.getAmount();
                            date = transaction.getDate();
                            date = date.substring(0, 10);
                            catId = transaction.getCategoryId();
                            expId = transaction.getTransactionTypeId();

                            if (catId.equals("3")) {
                                cards card = new cards(title, desc, amt, date, catId, expId);
                                tempList.add(card);
                            }

                        }
                        recyclerView = findViewById(R.id.itemsRecycler);
                        cardAdapter = new CardAdapter(Activity_Transaction.this, tempList);
                        recyclerView.setAdapter(cardAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Transaction.this));
                    }

                    @Override
                    public void onFailure(Call<List<Transaction>> call, Throwable t) {
                        if (mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                    }
                });

            }
        });

        activityTransactionBinding.catStaff.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                changeColor(6);
                tempList.clear();
                RetrofitClient.getRetrofitInstance().apiInterface.getTransactions().enqueue(new Callback<List<Transaction>>() {
                    @Override
                    public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                        if (mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                        transactionList = response.body();

                        for (Transaction transaction : transactionList) {
                            title = transaction.getName();
                            desc = transaction.getDescription();
                            amt = transaction.getAmount();
                            date = transaction.getDate();
                            date = date.substring(0, 10);
                            catId = transaction.getCategoryId();
                            expId = transaction.getTransactionTypeId();

                            if (catId.equals("5")) {
                                cards card = new cards(title, desc, amt, date, catId, expId);
                                tempList.add(card);
                            }

                        }
                        recyclerView = findViewById(R.id.itemsRecycler);
                        cardAdapter = new CardAdapter(Activity_Transaction.this, tempList);
                        recyclerView.setAdapter(cardAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Transaction.this));
                    }

                    @Override
                    public void onFailure(Call<List<Transaction>> call, Throwable t) {
                        if (mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                    }
                });

            }
        });

        activityTransactionBinding.catOther.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                changeColor(7);
                tempList.clear();
                RetrofitClient.getRetrofitInstance().apiInterface.getTransactions().enqueue(new Callback<List<Transaction>>() {
                    @Override
                    public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                        if (mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                        transactionList = response.body();

                        for (Transaction transaction : transactionList) {
                            title = transaction.getName();
                            desc = transaction.getDescription();
                            amt = transaction.getAmount();
                            date = transaction.getDate();
                            date = date.substring(0, 10);
                            catId = transaction.getCategoryId();
                            expId = transaction.getTransactionTypeId();

                            if (catId.equals("6")) {
                                cards card = new cards(title, desc, amt, date, catId, expId);
                                tempList.add(card);
                            }

                        }
                        recyclerView = findViewById(R.id.itemsRecycler);
                        cardAdapter = new CardAdapter(Activity_Transaction.this, tempList);
                        recyclerView.setAdapter(cardAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Transaction.this));
                    }

                    @Override
                    public void onFailure(Call<List<Transaction>> call, Throwable t) {
                        if (mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                    }
                });

            }
        });
//
        activityTransactionBinding.catAll.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                changeColor(1);

                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }

                recyclerView = findViewById(R.id.itemsRecycler);
                cardAdapter = new CardAdapter(Activity_Transaction.this, cardsList);
                recyclerView.setAdapter(cardAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Transaction.this));

//                RetrofitClient.getRetrofitInstance().apiInterface.getTransactions().enqueue(new Callback<List<Transaction>>() {
//                    @Override
//                    public void onResponse(@NonNull Call<List<Transaction>> call, @NonNull Response<List<Transaction>> response) {
//                        allTransactionList = response.body();
////               cardAdapter = new CardAdapter(Activity_Transaction.this, allTransactionList);
////               recyclerView.setAdapter(cardAdapter);
//
//
//                        for (Transaction transaction : allTransactionList) {
//                            title = transaction.getName();
//                            desc = transaction.getDescription();
//                            amt = transaction.getAmount();
//                            date = transaction.getDate();
//                            date = date.substring(0, 10);
//                            catId = transaction.getCategoryId();
//                            expId = transaction.getTransactionTypeId();
//
//                            cards card = new cards(title, desc, amt, date, catId, expId);
//                            cardsList.add(card);
//                        }
//
//                        // Toast.makeText(Activity_Transaction.this, Integer.toString(cardsList.size()), Toast.LENGTH_SHORT).show();
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<Transaction>> call, Throwable t) {
//
//                    }
//                });

            }

        });

        activityTransactionBinding.catPantry.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                changeColor(3);
                tempList.clear();
                RetrofitClient.getRetrofitInstance().apiInterface.getTransactions().enqueue(new Callback<List<Transaction>>() {
                    @Override
                    public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                        if (mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                        transactionList = response.body();

                        for (Transaction transaction : transactionList) {
                            title = transaction.getName();
                            desc = transaction.getDescription();
                            amt = transaction.getAmount();
                            date = transaction.getDate();
                            date = date.substring(0, 10);
                            catId = transaction.getCategoryId();
                            expId = transaction.getTransactionTypeId();

                            if (catId.equals("2")) {
                                cards card = new cards(title, desc, amt, date, catId, expId);
                                tempList.add(card);
                            }

                        }
                        recyclerView = findViewById(R.id.itemsRecycler);
                        cardAdapter = new CardAdapter(Activity_Transaction.this, tempList);
                        recyclerView.setAdapter(cardAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Transaction.this));
                    }

                    @Override
                    public void onFailure(Call<List<Transaction>> call, Throwable t) {
                        if (mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                    }
                });

            }
        });


        activityTransactionBinding.catIncome.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                changeColor(8);
                tempList.clear();

                RetrofitClient.getRetrofitInstance().apiInterface.getTransactions().enqueue(new Callback<List<Transaction>>() {
                    @Override
                    public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                        if (mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                        transactionList = response.body();

                        for (Transaction transaction : transactionList) {
                            title = transaction.getName();
                            desc = transaction.getDescription();
                            amt = transaction.getAmount();
                            date = transaction.getDate();
                            date = date.substring(0, 10);
                            catId = transaction.getCategoryId();
                            expId = transaction.getTransactionTypeId();

                            if (catId.equals("7")) {
                                cards card = new cards(title, desc, amt, date, catId, expId);
                                tempList.add(card);
                            }

                        }
                        recyclerView = findViewById(R.id.itemsRecycler);
                        cardAdapter = new CardAdapter(Activity_Transaction.this, tempList);
                        recyclerView.setAdapter(cardAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Transaction.this));
                    }

                    @Override
                    public void onFailure(Call<List<Transaction>> call, Throwable t) {
                        if (mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                    }
                });

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


    @SuppressLint("ResourceAsColor")
    public void changeColor(int id) {
        catAll.setBackgroundResource(R.color.light_grey);
        catFood.setBackgroundResource(R.color.light_grey);
        catPantry.setBackgroundResource(R.color.light_grey);
        catStat.setBackgroundResource(R.color.light_grey);
        catStaff.setBackgroundResource(R.color.light_grey);
        catOther.setBackgroundResource(R.color.light_grey);
        catTravel.setBackgroundResource(R.color.light_grey);
        catInc.setBackgroundResource(R.color.light_grey);
        if (id == 1) {
            catAll.setBackgroundResource(R.color.teal_200);

        } else if (id == 2) {
            catFood.setBackgroundResource(R.color.teal_200);
        } else if (id == 3) {
            catPantry.setBackgroundResource(R.color.teal_200);
        } else if (id == 4) {
            catTravel.setBackgroundResource(R.color.teal_200);
        } else if (id == 5) {
            catStat.setBackgroundResource(R.color.teal_200);
        } else if (id == 6) {
            catStaff.setBackgroundResource(R.color.teal_200);
        } else if (id == 7) {
            catOther.setBackgroundResource(R.color.teal_200);
        } else if (id == 8) {
            catInc.setBackgroundResource(R.color.teal_200);
        }

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Activity_Transaction.this, Activity_HomePage.class);

        startActivity(intent);
        finish();
    }
}