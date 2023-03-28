package com.example.trackme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.DatePicker;
import android.widget.NumberPicker;

import com.example.trackme.data.model.GeneratePdf;
import com.example.trackme.databinding.ActivityUserProfileBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Activity_UserProfile extends AppCompatActivity {

    private static final String PDF_API_FORMAT = "https://expensemanager20230325125916.azurewebsites.net/api/transactions/generatepdf?i_month=%s&i_year=%d";
    //
    private static final int CUT_OFF_DAY = 7;
    ActivityUserProfileBinding activityUserProfileBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUserProfileBinding = ActivityUserProfileBinding.inflate(getLayoutInflater());
        setContentView(activityUserProfileBinding.getRoot());

        activityUserProfileBinding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_UserProfile.this, Activity_HomePage.class));
                finish();
            }
        });

        activityUserProfileBinding.signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_UserProfile.this, MainActivity.class));
                finish();
            }
        });

        activityUserProfileBinding.reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Calendar c = Calendar.getInstance();
                int currYear = c.get(Calendar.YEAR);
                int currMonth = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);


                String[]monthName={"January","February","March", "April", "May", "June", "July",
                        "August", "September", "October", "November",
                        "December"};

                int monthIndex = c.get(Calendar.MONTH);
                int year=c.get(Calendar.YEAR);
                int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
                if(dayOfMonth <= CUT_OFF_DAY){
                    monthIndex = (monthIndex + 11) % 12;
                }


                String month=monthName[monthIndex];
                Log.e("url", month + " " + year);
                String apiUrl = String.format(PDF_API_FORMAT, month, year);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(apiUrl));
                startActivity(intent);

            }
        });


        activityUserProfileBinding.generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_UserProfile.this, Report_Format.class);
                startActivity(intent);
                finish();
            }
        });
        activityUserProfileBinding.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://igmite.in/index.php"));
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(Activity_UserProfile.this, Activity_HomePage.class));
        finish();

    }



//    public void openHomePage(View view){
//        Intent intent = new Intent(this, Activity_HomePage.class);
//        startActivity(intent);
//    }

    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            // todo change the file location/name according to your needs
            File file = new File(Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "FilePdf");
            if(!file.exists()){
                file.mkdirs();
            }

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(file);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d("File Download: " , fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }




}

