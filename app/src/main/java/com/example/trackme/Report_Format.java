package com.example.trackme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfDocument;
import android.media.tv.TvInputService;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.trackme.adapter.CardAdapter;
import com.example.trackme.adapter.ReportAdapter;
import com.example.trackme.data.model.Transaction;
import com.example.trackme.databinding.ActivityReportFormatBinding;
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
import java.security.Policy;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Properties;


//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;

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
    private static final int STORAGE_PERMISSION_CODE = 101;

    private File filePath = null;
    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_format);
        //checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);


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

        downBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                //createXlsx(reportList);
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

//    private void createXlsx(List<Report> reportList) {
//        try {
//            String strDate = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss", Locale.getDefault()).format(new Date());
//            File root = new File(Environment
//                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "FileExcel");
//            if (!root.exists())
//                root.mkdirs();
//
//            File path = new File(root, "/" + strDate + ".xlsx");
//
//            XSSFWorkbook workbook = new XSSFWorkbook();
//            FileOutputStream outputStream = new FileOutputStream(path);
//
//            XSSFCellStyle headerStyle = workbook.createCellStyle();
//            headerStyle.setAlignment(HorizontalAlignment.CENTER);
//            headerStyle.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
//            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//            headerStyle.setBorderTop(BorderStyle.MEDIUM);
//            headerStyle.setBorderBottom(BorderStyle.MEDIUM);
//            headerStyle.setBorderRight(BorderStyle.MEDIUM);
//            headerStyle.setBorderLeft(BorderStyle.MEDIUM);
//
//            XSSFFont font = workbook.createFont();
//            font.setFontHeightInPoints((short) 12);
//            font.setColor(IndexedColors.WHITE.getIndex());
//            font.setBold(true);
//            headerStyle.setFont(font);
//
//            XSSFSheet sheet = workbook.createSheet("TrackME");
//            XSSFRow row = sheet.createRow(0);
//
//            XSSFCell cell = row.createCell(0);
//            cell.setCellValue("Date");
//            cell.setCellStyle(headerStyle);
//
//            cell = row.createCell(1);
//            cell.setCellValue("Title");
//            cell.setCellStyle(headerStyle);
//
//            cell = row.createCell(2);
//            cell.setCellValue("Category");
//            cell.setCellStyle(headerStyle);
//
//            cell = row.createCell(3);
//            cell.setCellValue("Due Amount");
//            cell.setCellStyle(headerStyle);
//
//            String cat[] = {"Food And Beverages","Pantry","Stationary","Travel","Staff","Others","Income"};
//
//            for (int i = 0; i < reportList.size(); i++) {
//                row = sheet.createRow(i + 1);
//
//                cell = row.createCell(0);
//                cell.setCellValue(reportList.get(i).getTrDate());
//                sheet.setColumnWidth(0, (reportList.get(i).getTrDate().length() + 10) * 256);
//
//                cell = row.createCell(1);
//                cell.setCellValue(reportList.get(i).getTrTitle());
//                sheet.setColumnWidth(1, (reportList.get(i).getTrTitle().length()+15) * 256);
//
//                cell = row.createCell(2);
//                String category = cat[Integer.parseInt(reportList.get(i).getTrCategory())-1];
//                cell.setCellValue(category);
//                sheet.setColumnWidth(2, (reportList.get(i).getTrCategory().length()+30) * 256);
//
//                cell = row.createCell(3);
//                cell.setCellValue(reportList.get(i).getTrAmount());
//                sheet.setColumnWidth(3, (reportList.get(i).getTrAmount().length()+10) * 256);
//
//
//
//
//            }
//
//            workbook.write(outputStream);
//            outputStream.close();
//
//            Intent intentShareFile = new Intent(Intent.ACTION_SEND);
//            intentShareFile.putExtra(Intent.EXTRA_SUBJECT, "My Subject");
//            intentShareFile.putExtra(Intent.EXTRA_TEXT, "Extra text");
//            intentShareFile.setType("application/vnd.ms-excel");
//            intentShareFile.putExtra(Intent.EXTRA_STREAM, Uri.parse(path.getAbsolutePath()));
//            startActivity(Intent.createChooser(intentShareFile, "Share File"));
//
//
//
//
//            final String username = "prathamesh_girase@moderncoe.edu.in";
//            final String password = "McoeCode3250";
//            String message_to_send = "mpkulkarni117@gmail.com";
//
//            Properties props = new Properties();
//            props.put("mail.smtp.auth","true");
//            props.put("mail.smtp.starttls.enable","true");
//            props.put("mail.smtp.host","smtp.gmail.com");
//            props.put("mail.smtp.port","587");
//
//            Session session = Session.getInstance(props,
//                    new javax.mail.Authenticator(){
//                        @Override
//                        protected PasswordAuthentication getPasswordAuthentication(){
//                            return new PasswordAuthentication(username, password);
//                        }
//                    }
//                    );
//
//            try{
//                MimeMessage message = new MimeMessage(session);
//                message.setFrom(new InternetAddress());
//                message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(username));
//                message.setSubject("Sending Email Without Opening Gmail");
//                message.setText(message_to_send);
//                Transport.send(message);
//                Toast.makeText(getApplicationContext(),"email send succesfully", Toast.LENGTH_SHORT).show();
//            }catch (MessagingException e){
//                    throw new RuntimeException(e);
//            }
//
//
//
//            Toast.makeText(Report_Format.this, "Data successfully exported!", Toast.LENGTH_SHORT).show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//
//
//
//    public void checkPermission(String permission, int requestCode) {
//        if (ContextCompat.checkSelfPermission(Report_Format.this, permission) == PackageManager.PERMISSION_DENIED) {
//            ActivityCompat.requestPermissions(Report_Format.this, new String[]{permission}, requestCode);
//        } else {
//            Toast.makeText(Report_Format.this, "Permission already granted", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        if (requestCode == STORAGE_PERMISSION_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(Report_Format.this, "Storage Permission Granted", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(Report_Format.this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Report_Format.this, Activity_UserProfile.class);
        startActivity(intent);
        finish();
    }
}