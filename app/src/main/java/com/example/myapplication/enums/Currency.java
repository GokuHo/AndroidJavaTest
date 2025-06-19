package com.example.myapplication.enums;

public enum Currency {
    USD("USD", "$"),
    HKD("HKD", "HK$"),
    RMB("CNY", "¥"),
    EUR("EUR", "€"),
    GBP("GBP", "£"),
    JPY("JPY", "¥");

    private final String code;
    private final String symbol;

    Currency(String code, String symbol) {
        this.code = code;
        this.symbol = symbol;
    }
    public String getCode() {
        return code;
    }
    public String getSymbol() {
        return symbol;
    }
}