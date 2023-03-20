package com.example.trackme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.trackme.adapter.CardAdapter;
import com.example.trackme.adapter.ReportAdapter;
import com.example.trackme.data.model.Transaction;
import com.example.trackme.databinding.ActivityReportFormatBinding;
import com.gkemon.XMLtoPDF.PdfGenerator;
import com.gkemon.XMLtoPDF.PdfGeneratorListener;
import com.gkemon.XMLtoPDF.model.FailureResponse;
import com.gkemon.XMLtoPDF.model.SuccessResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Report_Format extends AppCompatActivity {

    TextView dueAmt;

    LinearLayout linear;
    CardView downBtn;
    List<Report> reportList;

    private String desc, title, amt, date ,catId, expId;
    List<Transaction> allTransactionList;
    RecyclerView recyclerView;
    ReportAdapter reportAdapter;
    private ActivityReportFormatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityReportFormatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        RetrofitClient.getRetrofitInstance().apiInterface.getTransactions().enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                allTransactionList = response.body();

                for (Transaction transaction: allTransactionList) {
                    title = transaction.getName();
                    desc = transaction.getDescription();
                    amt = transaction.getAmount();
                    date = transaction.getDate();
                    date = date.substring(0, 10);
                    catId = transaction.getCategoryId();
                    expId = (transaction.getTransactionTypeId());

                    Log.e("report", "OnSuccess " + title);
                    Log.e("report", "OnSuccess " + desc);
                    Log.e("report", "OnSuccess " + amt);
                    Log.e("report", "OnSuccess " + date);
                    Log.e("report", "OnSuccess " + catId);
                    Log.e("report", "OnSuccess " + expId);


//                    if(expId.equals("1")){
//                        Log.e("report", "OnSuccess " + expId);
//
//                    }
//                    Report report = new Report("date", "title", "1", "20");
//                    reportList.add(report);



                }
                Toast.makeText(Report_Format.this,reportList.size(),Toast.LENGTH_SHORT).show();
                recyclerView = findViewById(R.id.dataRecycler);
                reportAdapter = new ReportAdapter(Report_Format.this, reportList);
                recyclerView.setAdapter(reportAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Report_Format.this));
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {

            }
        });

//        dueAmt = findViewById(R.id.totalDue);
//        int sum = 0;
//        for(Report data: reportList){
//            int amt = Integer.parseInt(data.getTrAmount());
//            sum += amt;
//        }
//        dueAmt.setText(Integer.toString(sum));


        linear = findViewById(R.id.lineard);

        downBtn = findViewById(R.id.downloadBtn);
        downBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PdfGenerator.getBuilder()
                        .setContext(Report_Format.this)
                        .fromViewSource()
                        .fromView(linear)
                        /* "fromView()" takes array of view. You can also invoke "fromViewList()" method here
                         * which takes list of view instead of array. */
                        .setCustomPageSize(linear.getWidth(), linear.getHeight())
                        .setFileName("Test-PDF")
                        .setFolderName("TrackME")
                        .openPDFafterGeneration(true)
                        .build(new PdfGeneratorListener() {
                            @Override
                            public void onFailure(FailureResponse failureResponse) {
                                super.onFailure(failureResponse);
                            }

                            @Override
                            public void showLog(String log) {
                                super.showLog(log);
                            }

                            @Override
                            public void onSuccess(SuccessResponse response) {
                                super.onSuccess(response);
                            }

                        });
            }
        });





    }
}