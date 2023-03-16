package com.example.trackme.data.model;

import java.time.LocalDateTime;

public class Transaction {


    public int Id;
    public String Name;
    public String Description;
    public double Amount;

    public LocalDateTime Date;

    public int TransactionTypeId;
    public int CategoryId;

    public Transaction(int Id, String Name, String Description, double Amount, LocalDateTime Date, int TransactionTypeId, int CategoryId ){
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

    public double getAmount() {
        return Amount;
    }

    public LocalDateTime getDate() {
        return Date;
    }

    public int getTransactionTypeId() {
        return TransactionTypeId;
    }

    public int getCategoryId() {
        return CategoryId;
    }
}
