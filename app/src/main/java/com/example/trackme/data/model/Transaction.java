package com.example.trackme.data.model;

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
    @SerializedName("transactionId")

    public String TransactionTypeId;
    @SerializedName("categoryId")
    public String CategoryId;

    public Transaction(int Id, String Name, String Description, String Amount, String Date, String TransactionTypeId, String CategoryId ){
        this.Id = Id;
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
}
