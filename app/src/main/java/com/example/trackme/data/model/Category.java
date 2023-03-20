package com.example.trackme.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {



    public int Id;

    @SerializedName("name")
    public String Name;

    public List<Transaction> getTransactionCat() {
        return Transaction;
    }


    public List<Transaction> Transaction;

    public Category(int Id, String Name){
        this.Id = Id;
        this.Name = Name;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }


}
