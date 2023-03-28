package com.example.trackme;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackme.adapter.CardAdapter;
import com.example.trackme.data.model.Transaction;
import com.example.trackme.databinding.ActivityAddBinding;
import com.example.trackme.holder.cardHolder;


import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.time.LocalDate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@RequiresApi(api = Build.VERSION_CODES.O)
public class AddActivity extends AppCompatActivity  {


    Button addBtn;
    ActivityAddBinding binding;
    LinearLayout expenseLayout, incomeLayout;
    RelativeLayout relativeLayout;



    TextView category;
    EditText amtEditText, descEditText, titleEditText;




    LocalDate date = LocalDate.now();




// System.out.println(date1);

    Integer categoryId = 6, transactionType = 1;
    Spinner spinner;


    RecyclerView recyclerView;

    CardAdapter cardAdapter;
    cardHolder holder;



    ArrayAdapter<CharSequence> adapterItems;

    public AddActivity() throws ParseException {
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        titleEditText = (EditText) findViewById(R.id.title);
        descEditText = (EditText) findViewById(R.id.description);
        amtEditText = (EditText) findViewById(R.id.amount);



        // card = new cards();
        category = findViewById(R.id.textViewCategory);
        relativeLayout =(RelativeLayout) findViewById(R.id.categoryLayout);

        incomeLayout = findViewById(R.id.isIncome);
        expenseLayout = findViewById(R.id.isExpense);
        incomeLayout.setBackgroundColor(android.R.color.transparent);

        binding.isExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transactionType = 1;
                incomeLayout.setBackgroundColor(android.R.color.transparent);
                expenseLayout.setBackgroundResource(R.drawable.rectangle_border);
                relativeLayout.setVisibility(1);
                category.setVisibility(1);
            }
        });

        binding.isIncome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                categoryId = 7;
                transactionType = 2;
                expenseLayout.setBackgroundColor(android.R.color.transparent);
                incomeLayout.setBackgroundResource(R.drawable.rectangle_border);
                relativeLayout.setVisibility(view.GONE);
                category.setVisibility(view.GONE);


            }
        });

        spinner = findViewById(R.id.Category);
        adapterItems = ArrayAdapter.createFromResource(this, R.array.category_list, android.R.layout.simple_spinner_item);
        adapterItems.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterItems);




//        cardAmt = Integer.valueOf(amt.getText().toString());
//        cardDesc = desc.getText().toString();
//        cardDate =(String) curDate.getText();
//        cardsList = new ArrayList<>();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                String value = String.valueOf(adapterView.getItemIdAtPosition(pos));
                String item_position = String.valueOf(pos);

                int positonInt = Integer.parseInt(item_position);
                if(positonInt == 0) {
                    categoryId = 1;
                }
                else if (positonInt == 1) {
                    categoryId = 2;
                }
                else if (positonInt == 2) {
                    categoryId = 3;
                }
                else if (positonInt == 3) {
                    categoryId = 4;
                }
                else if (positonInt == 4) {
                    categoryId = 5;
                }
                else if (positonInt == 5) {
                    categoryId = 6;
                }

                adapterItems.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount, desc, title;

                title = titleEditText.getText().toString();
                if (StringUtils.isBlank(title)) {
                    titleEditText.setError( "Title is required!" );
                }

                amount = amtEditText.getText().toString();
                if(StringUtils.isBlank(amount)){
                    amtEditText.setError("Amount is required");
                }

                desc = descEditText.getText().toString();
                if(StringUtils.isBlank(desc)){
                    desc = StringUtils.EMPTY;
                }


//                Toast.makeText(AddActivity.this,getTitle,Toast.LENGTH_SHORT).show();
                Log.e("Add","Success"+ amount);
                if(!title.isEmpty()
                        && !amount.isEmpty()) {

                    ApiInterface retrofitApi = RetrofitClient.getRetrofitInstance().apiInterface;
                    Transaction transaction = new Transaction(title, desc,Double.parseDouble(amount),date.toString(), transactionType, categoryId);
                    Call<Transaction> call = retrofitApi.addTransaction(transaction);
                    call.enqueue(new Callback<Transaction>() {
                        @Override
                        public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                            Log.e("Add","Success");


                        }

                        @Override
                        public void onFailure(Call<Transaction> call, Throwable t) {
                            Log.e("Add","Failure");
                        }
                    });
                    startActivity(new Intent(AddActivity.this, Activity_HomePage.class));
                    finish();

                }

            }
        });


//        addBtn =(Button) findViewById(R.id.addBtn);
//        addBtn.setOnClickListener(this::openTransactionPage);


        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddActivity.this, Activity_HomePage.class));
                finish();
            }
        });


    }

    public void openTransactionPage(View view) {
        Toast.makeText(this, "Transaction Added !", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Activity_Transaction.class);
        startActivity(intent);
    }

    @Override
        public void onBackPressed(){
        startActivity(new Intent(AddActivity.this, Activity_HomePage.class));
        finish();
    }


}