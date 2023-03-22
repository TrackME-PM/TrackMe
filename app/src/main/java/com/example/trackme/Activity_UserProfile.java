package com.example.trackme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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
                startActivity(new Intent(Activity_UserProfile.this, Report_Format.class));
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
        String username = (String) getIntent().getSerializableExtra("username");
        activityUserProfileBinding.tvUser.setText(username);
    }



//    public void openHomePage(View view){
//        Intent intent = new Intent(this, Activity_HomePage.class);
//        startActivity(intent);
//    }

}