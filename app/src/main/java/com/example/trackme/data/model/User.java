package com.example.trackme.data.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    public User(String email, String password) {
        this.email = email;
        this.password = password;

    }




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
