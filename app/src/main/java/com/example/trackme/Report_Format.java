package com.example.trackme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.trackme.adapter.CardAdapter;
import com.example.trackme.adapter.ReportAdapter;
import com.example.trackme.data.model.Transaction;
import com.example.trackme.databinding.ActivityReportFormatBinding;
//import com.github.barteksc.pdfviewer.PDFView;
import com.gkemon.XMLtoPDF.PdfGenerator;
import com.gkemon.XMLtoPDF.PdfGeneratorListener;
import com.gkemon.XMLtoPDF.model.FailureResponse;
import com.gkemon.XMLtoPDF.model.SuccessResponse;


import java.io.File;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Report_Format extends AppCompatActivity {

    TextView dueAmt, tvMonth;

    LinearLayout linear, layout;
    CardView downBtn;
    List<Report> reportList;
    double sum = 0, getAmt;

    Uri uri;

    private String desc, title, amt, date ,catId, expId;
    private String trTitle, trAmt, trDate ,trCat;
    List<Transaction> allTransactionList;
    RecyclerView recyclerView;
    String[] monthArr = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    String[] header = {"Tr Date", "Title", "Category", "Due Amount"};
    ReportAdapter reportAdapter;

//    PDFView pdfView;

    ImageView backBtn;

    private File filePath = null;
    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_format);


        dueAmt = findViewById(R.id.totalDue);
        tvMonth = findViewById(R.id.monthName);

        reportList = new ArrayList<>();

        ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();


        RetrofitClient.getRetrofitInstance().apiInterface.getTransactions().enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {

                if (mProgressDialog.isShowing()){
                    mProgressDialog.dismiss();
                }
                allTransactionList = response.body();

                String[] str = allTransactionList.get(1).getDate().split("-");
                int month = Integer.parseInt(str[1]);
                String getMonth = monthArr[month-1];
                tvMonth.setText(getMonth);

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


                    if(expId.equals("1")){
                        Log.e("report", "OnSuccess " + expId);
                        String[] dates = transaction.getDate().split("-");
                        LocalDate date_var = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            date_var = LocalDate.now();
                        }
                        String[] currDate = date_var.toString().split("-");
                        int day = Integer.parseInt((currDate[2]));
                        int mnth = Integer.parseInt((currDate[1]));
                        if(day<=7){
                            tvMonth.setText(monthArr[mnth-2]);
                            int pos = Integer.parseInt(dates[1]);
                            if((mnth-1) == pos) {
                                Report report = new Report(date, title, catId, amt);
                                reportList.add(report);
                                getAmt = Double.parseDouble(amt);
                                sum += getAmt;
                            }

                        }
                        else{
                            tvMonth.setText(monthArr[mnth-1]);
                            int pos = Integer.parseInt(dates[1]);
                            if(mnth == pos) {
                                Report report = new Report(date, title, catId, amt);
                                reportList.add(report);
                                getAmt = Double.parseDouble(amt);
                                sum += getAmt;
                            }


                        }




                        Log.e("amount", "OnSuccess " + date_var.toString());

                    }

                }


//                Toast.makeText(Report_Format.this,reportList.size(),Toast.LENGTH_SHORT).show();


                dueAmt.setText(Double.toString(sum));



                recyclerView = findViewById(R.id.dataRecycler);
                reportAdapter = new ReportAdapter(Report_Format.this, reportList);
                recyclerView.setAdapter(reportAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Report_Format.this));

            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {
                if (mProgressDialog.isShowing()){
                    mProgressDialog.dismiss();
                }
            }
        });


        final File[] file = new File[1];

        linear = findViewById(R.id.lineard);
        layout = findViewById(R.id.layoutPage);
//        pdfView = findViewById(R.id.pdfView);

        downBtn = findViewById(R.id.downloadBtn);
        downBtn.setVisibility(View.GONE);
//        downBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//
//
//                PdfGenerator.getBuilder()
//                        .setContext(Report_Format.this)
//                        .fromViewSource()
//                        .fromView(linear)
//                        /* "fromView()" takes array of view. You can also invoke "fromViewList()" method here
//                         * which takes list of view instead of array. */
//                        .setCustomPageSize(layout.getWidth(), layout.getHeight())
//                        .setFileName("Test-PDF")
//                        .setFolderName("TrackME")
//                        .openPDFafterGeneration(true)
//                        .build(new PdfGeneratorListener() {
//                            @Override
//                            public void onFailure(FailureResponse failureResponse) {
//                                super.onFailure(failureResponse);
//                            }
//
//                            @Override
//                            public void showLog(String log) {
//                                super.showLog(log);
//                            }
//
//                            @Override
//                            public void onSuccess(SuccessResponse response) {
//                                PdfDocument pdf = response.getPdfDocument();
//                                uri = Uri.parse(response.getPath());
//                                super.onSuccess(response);
//                                Intent intent = new Intent(Report_Format.this,Activity_UserProfile.class);
//                                startActivity(intent);
//                                finish();
//                            }
//
//                        });

//                        pdfView.fromUri(uri);






//
//
//            }
//
//
//        });

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Report_Format.this, Activity_UserProfile.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Report_Format.this, Activity_UserProfile.class);
        startActivity(intent);
        finish();
    }
}