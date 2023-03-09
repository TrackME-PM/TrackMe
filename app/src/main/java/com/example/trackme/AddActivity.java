package com.example.trackme;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.trackme.adapter.CardAdapter;
import com.example.trackme.databinding.ActivityAddBinding;
import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class AddActivity extends AppCompatActivity  {


    Button addBtn;
    ActivityAddBinding binding;
    LinearLayout expLayout, incLayout;
    RelativeLayout relativeLayout;

    TextView category;
    EditText amt, desc;
    String cardDesc, cardDate;
    Integer catId, cardAmt;
    Spinner spinner;


    RecyclerView recyclerView;

    CardAdapter cardAdapter;
    List<cards> cardsList;
    cards card;
    ArrayAdapter<CharSequence> adapterItems;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        category = findViewById(R.id.textViewCategory);
        relativeLayout =(RelativeLayout) findViewById(R.id.categoryLayout);

        incLayout = findViewById(R.id.isIncome);
        expLayout = findViewById(R.id.isExpense);
        incLayout.setBackgroundColor(android.R.color.transparent);

        binding.isExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                card.setExpId(1);
                incLayout.setBackgroundColor(android.R.color.transparent);
                expLayout.setBackgroundResource(R.drawable.rectangle_border);
                relativeLayout.setVisibility(1);
                category.setVisibility(1);
            }
        });

        binding.isIncome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                card.setExpId(0);
                expLayout.setBackgroundColor(android.R.color.transparent);
                incLayout.setBackgroundResource(R.drawable.rectangle_border);
                relativeLayout.setVisibility(view.GONE);
                category.setVisibility(view.GONE);


            }
        });

        spinner = findViewById(R.id.Category);
        adapterItems = ArrayAdapter.createFromResource(this, R.array.category_list, android.R.layout.simple_spinner_item);
        adapterItems.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterItems);


        desc =(EditText) findViewById(R.id.description);
        amt =(EditText)  findViewById(R.id.amount);

        cardAmt = Integer.getInteger(amt.getText().toString());
        cardDesc = desc.getText().toString();
//        cardDate =(String) curDate.getText();
//        cardsList = new ArrayList<>();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                String value = String.valueOf(adapterView.getItemIdAtPosition(pos));
                String item_position = String.valueOf(pos);

                int positonInt = Integer.valueOf(item_position);

                adapterItems.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                card = new cards(cardDesc, cardAmt, "09-03-2023", 1);
                cardsList.add(card);

                recyclerView = findViewById(R.id.cardView);
                cardAdapter = new CardAdapter(AddActivity.this, cardsList);
                recyclerView.setAdapter(cardAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(AddActivity.this));

                openTransactionPage(view);
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

//    private void passData(int position) {
//        cards card = new cards("Desc", 200, "02-03-2023", 1);
//        Intent i = new Intent(AddActivity.this, Activity_Transaction.class);
//        i.putExtra("cardDescription", cardsList.get(position).getDescription());
//        i.putExtra("cardAmount", cardsList.get(position).getAmount());
//        i.putExtra("cardDate", cardsList.get(position).getDate());
//        c.startActivity(i);
//    }


}
