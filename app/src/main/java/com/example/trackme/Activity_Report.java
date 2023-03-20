package com.example.trackme;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.trackme.data.model.Transaction;
import com.example.trackme.databinding.ActivityReportBinding;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Report extends AppCompatActivity {

    // Create the object of TextView and PieChart class
    TextView tvFood, tvTravel, tvStat, tvOther, tvStaff, tvPantry;
    PieChart pieChart;

    int food = 0, travel = 0, stat = 0, other = 0, pantry = 0, staff = 0;
    String catId;

    List<Transaction> allTransactionList;
    ActivityReportBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Link those objects with their respective
// id's that we have given in .XML file
        tvFood = findViewById(R.id.tvFood);
        tvTravel = findViewById(R.id.tvTravel);
        tvStat = findViewById(R.id.tvStat);
        tvOther = findViewById(R.id.tvOther);
        tvStaff = findViewById(R.id.tvStaff);
        tvPantry = findViewById(R.id.tvPantry);
        pieChart = findViewById(R.id.piechart);


        setData();
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_Report.this, Activity_HomePage.class));
                finish();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void setData(){
        // Set the percentage of language used
        RetrofitClient.getRetrofitInstance().apiInterface.getTransactions().enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                allTransactionList = response.body();

                for(Transaction transaction : allTransactionList) {
                    catId = transaction.getCategoryId();
                    if(catId.equals("1")){
                        food += (int) Math.round(Double.parseDouble(transaction.getAmount()));
                    }
                    if(catId.equals("2")){
                        pantry += (int) Math.round(Double.parseDouble(transaction.getAmount()));
                    }
                    if(catId.equals("3")){
                        stat += (int) Math.round(Double.parseDouble(transaction.getAmount()));
                    }
                    if(catId.equals("4")){
                        travel += (int) Math.round(Double.parseDouble(transaction.getAmount()));
                    }
                    if(catId.equals("5")){
                        staff += (int) Math.round(Double.parseDouble(transaction.getAmount()));
                    }
                    if(catId.equals("6")){
                        other += (int) Math.round(Double.parseDouble(transaction.getAmount()));
                    }
                }
                Log.e("chart", "OnSuccess : " + Integer.toString(food));
                Log.e("chart", "OnSuccess : " +Integer.toString(pantry));
                Log.e("chart", "OnSuccess : " +Integer.toString(stat));
                Log.e("chart", "OnSuccess : " +Integer.toString(travel));
                Log.e("chart", "OnSuccess : " +Integer.toString(staff));


                tvFood.setText(Integer.toString(food));
                tvTravel.setText(Integer.toString(travel));
                tvStat.setText(Integer.toString(stat));
                tvOther.setText(Integer.toString(other));
                tvPantry.setText(Integer.toString(pantry));
                tvStaff.setText(Integer.toString(staff));

                // Set the data and color to the pie chart
                pieChart.addPieSlice(
                        new PieModel(
                                "Food",
                                Integer.parseInt(tvFood.getText().toString()),
                                Color.parseColor("#FFA726")));
                pieChart.addPieSlice(
                        new PieModel(
                                "Pantry",
                                Integer.parseInt(tvPantry.getText().toString()),
                                Color.parseColor("#D45089")));
                pieChart.addPieSlice(
                        new PieModel(
                                "Stationary",
                                Integer.parseInt(tvStat.getText().toString()),
                                Color.parseColor("#50D4A4")));

                pieChart.addPieSlice(
                        new PieModel(
                                "Travel",
                                Integer.parseInt(tvTravel.getText().toString()),
                                Color.parseColor("#66BB6A")));

                pieChart.addPieSlice(
                        new PieModel(
                                "Staff",
                                Integer.parseInt(tvStaff.getText().toString()),
                                Color.parseColor("#EF5350")));

                pieChart.addPieSlice(
                        new PieModel(
                                "Others",
                                Integer.parseInt(tvOther.getText().toString()),
                                Color.parseColor("#29B6F6")));

                // To animate the pie chart
                pieChart.startAnimation();


            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {

            }
        });

//        Log.e("chart", "OnSuccess : " + Integer.toString(food));
//        Log.e("chart", "OnSuccess : " +Integer.toString(pantry));
//        Log.e("chart", "OnSuccess : " +Integer.toString(stat));
//        Log.e("chart", "OnSuccess : " +Integer.toString(travel));
//        Log.e("chart", "OnSuccess : " +Integer.toString(staff));




    }
}