package com.example.trackme.data.model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.time.LocalDateTime;
import java.util.List;

public class Transaction {


    public int Id;
    @SerializedName("name")
    public String Name;
    @SerializedName("description")
    public String Description;
    @SerializedName("amount")
    public Double Amount;
    @SerializedName("date")

    public String Date;
    @SerializedName("transactionTypeId")

    public int TransactionTypeId;
    @SerializedName("categoryId")
    public int CategoryId;

    @SerializedName("title")
    public String title;

    public String getTitle() {
        return title;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setTransactionTypeId(int transactionTypeId) {
        TransactionTypeId = transactionTypeId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public Transaction(String Name, String Description, Double Amount, String Date, int TransactionTypeId, int CategoryId ){
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
        return Double.toString(Amount);
    }

    public String getDate() {
        return Date.toString();
    }

    public String getTransactionTypeId() {
        return Integer.toString(TransactionTypeId);
    }

    public String getCategoryId() {
        return Integer.toString(CategoryId);
    }

    public void show(){
        Log.e("Transaction","onSuccess: " + Name);
        Log.e("Transaction","onSuccess: " + Amount);
        Log.e("Transaction","onSuccess: " + Date);
        Log.e("Transaction","onSuccess: " + Description);
        Log.e("Transaction","onSuccess: " + CategoryId);
    }
}
