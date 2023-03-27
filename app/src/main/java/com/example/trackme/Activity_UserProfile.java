package com.example.trackme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.trackme.databinding.ActivityUserProfileBinding;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;


public class Activity_UserProfile extends AppCompatActivity {

    private static final String PDF_API_FORMAT = "https://expensemanager20230325125916.azurewebsites.net/api/transactions/generatepdf?i_month=%s&i_year=%d";

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
                String apiUrl = String.format(PDF_API_FORMAT, month, year);
                Intent intent = new Intent();
                intent.setData(Uri.parse(apiUrl));
                startActivity(intent);
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

}