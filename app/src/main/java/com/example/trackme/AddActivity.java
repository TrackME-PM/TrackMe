package com.example.trackme;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trackme.databinding.ActivityAddBinding;


public class AddActivity extends AppCompatActivity  {

    Button btn;
    ActivityAddBinding binding;

    Spinner spinner;

    ArrayAdapter<String> adapterItems;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        spinner = findViewById(R.id.Category);
        adapterItems = new ArrayAdapter<>(this, R.layout.dropdown_item, R.array.category_list);
        spinner.setAdapter(adapterItems);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                adapterItems.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn =(Button) findViewById(R.id.addBtn);
        btn.setOnClickListener(this::openTransactionPage);



        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddActivity.this, Activity_HomePage.class));
            }
        });


    }

    public void openTransactionPage(View view) {
        Toast.makeText(this, "Transaction Added !", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Activity_Transaction.class);
        startActivity(intent);
    }


}
