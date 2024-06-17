/*
Name> Luis C. Sepulveda
date> 2024/06/15

.THis class will gives the information on the stok price for an specific date
* It will help us to manage and store data */


package org.example.assignment_1_applestocks;

public class StockInfo {
    private String date;
    private double open;
    private double high;
    private double low;
    private double close;
    private long volume;


//Constructor
    //this contructor will initializes all the fields when a stockInfo object is created.
    public StockInfo(String date, double open, double high, double low, double close, long volume){
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }


    // Getter & setter for all the variables


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }


}
