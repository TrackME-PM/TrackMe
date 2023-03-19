package com.example.trackme;

public class cards {

    private String description;

    private String title;
    private String amount;
    private String date;
    private String expId, catId;

    public cards(String title, String description, String amount, String date, String catId, String expId) {
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.expId = expId;
        this.catId = catId;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String  amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExpId() {
        return expId;
    }

    public void setExpId(String expId) {
        this.expId = expId;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getTitle() {
        return title;
    }


}
