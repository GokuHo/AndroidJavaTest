package com.example.myapplication.model;

public class NewCoin {
    private int id;
    private String icon, name;
    private double price, convertedPrice, rate;

    public NewCoin(int id, String icon, String name, double price, double convertedPrice, double rate) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.price = price;
        this.convertedPrice = convertedPrice;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getConvertedPrice() {
        return convertedPrice;
    }

    public void setConvertedPrice(double convertedPrice) {
        this.convertedPrice = convertedPrice;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
