package com.example.trackme.data.model;

import com.google.gson.annotations.SerializedName;

public class GeneratePdf {

    @SerializedName("month")
    private String Month;

    public String getMonth() {
        return Month;
    }

    public String getYear() {
        return Year;
    }

    public GeneratePdf(String month, String year) {
        this.Month = month;
        this.Year = year;
    }

    @SerializedName("year")
    private String Year;





}
