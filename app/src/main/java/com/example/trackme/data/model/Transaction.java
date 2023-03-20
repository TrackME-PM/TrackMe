package com.example.trackme.data.model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class Transaction {


    public int Id;
    @SerializedName("name")
    public String Name;
    @SerializedName("description")
    public String Description;
    @SerializedName("amount")
    public String Amount;
    @SerializedName("date")

    public String Date;
    @SerializedName("transactionTypeId")

    public String TransactionTypeId;
    @SerializedName("categoryId")
    public String CategoryId;

    public Transaction(String Name, String Description, String Amount, String Date, String TransactionTypeId, String CategoryId ){
        this.Name = Name;
        this.Description = Description;
        this.Amount = Amount;
        this.Date = Date;
        this.TransactionTypeId = TransactionTypeId;
        this.CategoryId = CategoryId;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public String getAmount() {
        return Amount;
    }

    public String getDate() {
        return Date;
    }

    public String getTransactionTypeId() {
        return TransactionTypeId;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void show(){
        Log.e("Transaction","onSuccess: " + Name);
        Log.e("Transaction","onSuccess: " + Amount);
        Log.e("Transaction","onSuccess: " + Date);
        Log.e("Transaction","onSuccess: " + Description);
        Log.e("Transaction","onSuccess: " + CategoryId);
    }
}
