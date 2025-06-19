package com.example.myapplication.fragement.bn.home;

import androidx.lifecycle.ViewModel;

public class HomeVM extends ViewModel {
    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    public void incrementCounter() {
        counter++;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}