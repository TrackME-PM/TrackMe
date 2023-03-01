package com.example.trackme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // This is a test comment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openHomePage(View v) {
        Toast.makeText(this, "Logged in successfully !", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Activity_HomePage.class);
        startActivity(intent);

    }
}