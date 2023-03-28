package com.example.trackme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.trackme.adapter.ReportAdapter;
import com.example.trackme.data.model.Transaction;
//import com.github.barteksc.pdfviewer.PDFView;

//
//import org.apache.poi.ss.usermodel.BorderStyle;
//import org.apache.poi.ss.usermodel.FillPatternType;
//import org.apache.poi.ss.usermodel.HorizontalAlignment;
//import org.apache.poi.ss.usermodel.IndexedColors;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFCellStyle;
//import org.apache.poi.xssf.usermodel.XSSFFont;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;


//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Report_Format extends AppCompatActivity {

    EditText monthName, year;
    String i_month, i_year;

    private static final String PDF_API_FORMAT = "https://expensemanager20230325125916.azurewebsites.net/api/transactions/generatepdf?i_month=%s&i_year=%s";

    private static final int CUT_OFF_DAY = 7;



//    PDFView pdfView;

    ImageView backBtn;
    Button getReportBtn;
    private static final int STORAGE_PERMISSION_CODE = 101;

    private File filePath = null;
    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_format);

        monthName = findViewById(R.id.month);
        year = findViewById(R.id.year);

        getReportBtn = findViewById(R.id.getReportBtn);
        getReportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i_month = monthName.getText().toString();
                i_year = year.getText().toString();

                if(i_month.isEmpty()){
                    monthName.setError("Month is required!");
                }
                if(i_year.isEmpty()){
                    year.setError("Year is required");
                }



                if(!i_month.isEmpty() && !i_year.isEmpty()){
                    if(validateMonth(i_month) && validateYear(i_year)){
                        Log.e("url", i_month + " " + i_year);
                        String apiUrl = String.format(PDF_API_FORMAT, i_month, i_year);
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(apiUrl));
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(Report_Format.this, "Enter valid month and year!!! ", Toast.LENGTH_SHORT).show();
                    }

                }

//
            }
        });


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

    public boolean validateMonth(String month) {
        String[]monthName={"January","February","March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December","january","february","march", "april", "may", "june", "july",
                "august", "september", "october", "november",
                "december"};
        for(String s : monthName){
            if(Objects.equals(month, s)){
                return true;
            }
        }
        return false;
    }

    public boolean validateYear(String year) {
        int i_year = Integer.parseInt(year);
        Calendar c = Calendar.getInstance();
        int currYear = c.get(Calendar.YEAR);
        if(i_year <= currYear){
            return true;
        }
        return false;
    }

}