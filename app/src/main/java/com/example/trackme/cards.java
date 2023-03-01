package com.example.trackme;

public class cards {

    private String description;
    private int amount;
    private String date;
    private int expId;

    public cards(String description, int amount, String date, int expId) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.expId = expId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getExpId() {
        return expId;
    }

    public void setExpId(int expId) {
        this.expId = expId;
    }



}
