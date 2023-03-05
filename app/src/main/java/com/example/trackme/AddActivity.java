package com.example.trackme;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
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

    TextView desc, amt, curDate;
    String cardDesc, cardDate;
    Integer cardAmt;
    Spinner spinner;

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

        spinner = findViewById(R.id.Category);
        adapterItems = ArrayAdapter.createFromResource(this, R.array.category_list, android.R.layout.simple_spinner_item);
        adapterItems.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterItems);


        desc =(TextView) findViewById(R.id.description);
        amt =(TextView)  findViewById(R.id.amount);
        curDate =(TextView) findViewById(R.id.date);

//        cardAmt = Integer.valueOf(amt.getText().toString());
//        cardDesc = desc.getText().toString();
//        cardDate = curDate.getText().toString();
//        cardsList = new ArrayList<>();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                String value = String.valueOf(adapterView.getItemIdAtPosition(pos));
                adapterItems.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        addBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                card = new cards(cardDesc, cardAmt, cardDate, 1);
//                cardsList.add(card);
//                Intent intent = new Intent(AddActivity.this, Activity_Transaction.class);
//                intent.putExtra("TransactionList", (Serializable)cardsList);
//                startActivity(intent);
//                finish();
//            }
//        });

        addBtn =(Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this::openTransactionPage);



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


}
