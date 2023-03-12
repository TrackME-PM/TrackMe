package com.example.trackme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.trackme.adapter.ReportAdapter;
import com.example.trackme.databinding.ActivityReportFormatBinding;

import java.util.ArrayList;
import java.util.List;

public class Report_Format extends AppCompatActivity {

    TextView trDate, trTitle, trCategory, trAmount, trDue;

    List<Report> reportList;
    RecyclerView recyclerView;
    ReportAdapter reportAdapter;
    private ActivityReportFormatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityReportFormatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        reportList = new ArrayList<>();
        Report data1 = new Report("23-02-2023","Tea", 1, 500,"07-03-2023");
        Report data2 = new Report("28-02-2023","Pantry", 2, 800,"07-03-2023");
        Report data3 = new Report("27-02-2023","Staff", 5, 1000,"07-03-2023");
        Report data4 = new Report("12-02-2023","Stationary", 3, 200,"07-03-2023");
        Report data5 = new Report("17-02-2023","Travel", 4, 450,"07-03-2023");
        Report data6 = new Report("20-02-2023","Other", 5, 700,"07-03-2023");
        Report data7 = new Report("05-02-2023","Staff", 5, 950,"07-03-2023");
        Report data8 = new Report("20-02-2023","Pantry", 1, 1100,"07-03-2023");

        reportList.add(data1);
        reportList.add(data2);
        reportList.add(data3);
        reportList.add(data4);
        reportList.add(data5);
        reportList.add(data6);
        reportList.add(data7);
        reportList.add(data8);


        recyclerView = findViewById(R.id.itemsRecycler);
        reportAdapter = new ReportAdapter(this, reportList);
        recyclerView.setAdapter(reportAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
}