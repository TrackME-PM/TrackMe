package com.example.trackme.data.model;

import java.util.List;

public class Category {



    public int Id;

    public String Name;

    public List<Transaction> getTransaction() {
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
