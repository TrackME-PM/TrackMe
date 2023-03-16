

package com.example.trackme;

public class Report {
    private String trDate;
    private String trTitle;
    private int trCategory;

    public Report(String trDate, String trTitle, int trCategory, int trAmount, String trDue) {
        this.trDate = trDate;
        this.trTitle = trTitle;
        this.trCategory = trCategory;
        this.trAmount = trAmount;
        this.trDue = trDue;
    }

    private int trAmount;

    private String trDue;



    public String getTrDate() {
        return trDate;
    }

    public void setTrDate(String trDate) {
        this.trDate = trDate;
    }

    public String getTrTitle() {
        return trTitle;
    }

    public void setTrTitle(String trTitle) {
        this.trTitle = trTitle;
    }

    public int getTrCategory() {
        return trCategory;
    }

    public void setTrCategory(int trCategory) {
        this.trCategory = trCategory;
    }

    public int getTrAmount() {
        return trAmount;
    }

    public void setTrAmount(int trAmount) {
        this.trAmount = trAmount;
    }

    public String getTrDue() {
        return trDue;
    }

    public void setTrDue(String trDue) {
        this.trDue = trDue;
    }
}

