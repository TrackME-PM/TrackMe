package com.example.trackme;

public class cards {

    private String description;

    private String title;
    private String amount;
    private String date;
    private String transactionType, categoryId;

    public cards(String title, String description, String amount, String date, String catId, String expId) {
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.transactionType = expId;
        this.categoryId = catId;
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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }


}
