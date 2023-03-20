

package com.example.trackme;

public class Report {
    private String trDate;
    private String trTitle;
    private String trCategory;

    public Report(String trDate, String trTitle, String trCategory, String trAmount) {
        this.trDate = trDate;
        this.trTitle = trTitle;
        this.trCategory = trCategory;
        this.trAmount = trAmount;
    }

    private String trAmount;

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

    public String getTrCategory() {
        return trCategory;
    }

    public void setTrCategory(String trCategory) {
        this.trCategory = trCategory;
    }

    public String getTrAmount() {
        return trAmount;
    }

    public void setTrAmount(String trAmount) {
        this.trAmount = trAmount;
    }

    public String getTrDue() {
        return trDue;
    }

    public void setTrDue(String trDue) {
        this.trDue = trDue;
    }
}

