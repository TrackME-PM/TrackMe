package com.example.trackme.data.model;

import java.util.List;

public class TransactionType {

    public int Id;
    public String Name;
//    public List<com.example.trackme.data.model.Transaction> Transaction;
    public TransactionType(int id, String name) {
        Id = id;
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }
}
