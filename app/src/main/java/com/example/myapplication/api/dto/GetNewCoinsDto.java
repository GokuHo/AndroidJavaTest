package com.example.myapplication.api.dto;

import com.example.myapplication.model.NewCoin;

import java.util.List;

public class GetNewCoinsDto {
    private List<NewCoin> newCoins;
    private int total;

    public GetNewCoinsDto() {}

    public GetNewCoinsDto(List<NewCoin> newCoins, int total) {
        this.newCoins = newCoins;
        this.total = total;
    }

    public List<NewCoin> getNewCoins() {
        return newCoins;
    }

    public void setNewCoins(List<NewCoin> newCoins) {
        this.newCoins = newCoins;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "GetNewCoinsDto{" +
                "newCoins=" + newCoins +
                ", total=" + total +
                '}';
    }
}
