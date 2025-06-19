package com.example.myapplication.fragement.tab1.item4;

import android.util.Log;

import androidx.lifecycle.ViewModel;

public class Item4VM extends ViewModel {
    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    public void incrementCounter() {
        counter++;
    }

    public void fetchApi() {
        Log.e("==test==", "123");
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}