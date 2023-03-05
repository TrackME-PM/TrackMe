package com.example.trackme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.trackme.databinding.ActivityUserProfileBinding;


public class Activity_UserProfile extends AppCompatActivity {

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
                startActivity(new Intent(Activity_UserProfile.this, Activity_Report.class));
                finish();
            }
        });

    }

//    public void openHomePage(View view){
//        Intent intent = new Intent(this, Activity_HomePage.class);
//        startActivity(intent);
//    }

}