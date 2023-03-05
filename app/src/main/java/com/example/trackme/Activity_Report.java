package com.example.trackme;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class Activity_Report extends AppCompatActivity {

    // Create the object of TextView and PieChart class
    TextView tvFood, tvTravel, tvStat, tvOther;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        // Link those objects with their respective
// id's that we have given in .XML file
        tvFood = findViewById(R.id.tvFood);
        tvTravel = findViewById(R.id.tvTravel);
        tvStat = findViewById(R.id.tvStat);
        tvOther = findViewById(R.id.tvOther);
        pieChart = findViewById(R.id.piechart);


        setData();
    }

    @SuppressLint("SetTextI18n")
    private void setData(){
        // Set the percentage of language used
        tvFood.setText(Integer.toString(40));
        tvTravel.setText(Integer.toString(30));
        tvStat.setText(Integer.toString(5));
        tvOther.setText(Integer.toString(25));

        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "Food",
                        Integer.parseInt(tvFood.getText().toString()),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Travel",
                        Integer.parseInt(tvTravel.getText().toString()),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Stationary",
                        Integer.parseInt(tvStat.getText().toString()),
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "Others",
                        Integer.parseInt(tvOther.getText().toString()),
                        Color.parseColor("#29B6F6")));

        // To animate the pie chart
        pieChart.startAnimation();

    }
}