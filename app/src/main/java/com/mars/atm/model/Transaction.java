package com.mars.atm.model;

public class Transaction {
    private int amount;
    private String date;
    private String acount;
    private int type;

    public Transaction(String date,  String acount, int amount, int type) {
        this.amount = amount;
        this.date = date;
        this.acount = acount;
        this.type = type;
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

    public String getAcount() {
        return acount;
    }

    public void setAcount(String acount) {
        this.acount = acount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
