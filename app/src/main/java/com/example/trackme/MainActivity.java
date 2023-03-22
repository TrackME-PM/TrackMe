package com.example.trackme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trackme.data.model.User;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    // This is a test comment
    Button loginBtn;
    EditText username, password;
    String usrnm, psswrd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginBtn = findViewById(R.id.logInBtn);
        username = (EditText) findViewById(R.id.usernameTextEdit);
        password = (EditText) findViewById(R.id.passwordTextEdit);




        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usrnm = username.getText().toString();
                psswrd = password.getText().toString();



                loginBtnClicked(usrnm, psswrd);

            }
        });


    }


    private void loginBtnClicked(String username, String password){
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://expensemanager20230309225838.azurewebsites.net/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        ApiInterface retrofitApi = retrofit.create(ApiInterface.class);
        ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
        ApiInterface retrofitApi = RetrofitClient.getRetrofitInstance().apiInterface;
        User user = new User(username, password);
        Call<User> call = retrofitApi.getLoginData(user);

        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                  Log.e("login", String.valueOf(response.code()));
                  Log.e("login", usrnm);
                  Log.e("login", psswrd);

                if (mProgressDialog.isShowing()){
                    mProgressDialog.dismiss();
                }

                  if(response.code()==200){
                      Toast.makeText(MainActivity.this, "Logged in successfully !", Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent(MainActivity.this, Activity_HomePage.class);
                      startActivity(intent);
                      finish();
                  }
                  else{
                      Toast.makeText(MainActivity.this, "Invalid Credentials !", Toast.LENGTH_SHORT).show();
                  }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("login","failed");
            }
        });
    }


}