package com.example.myapplication.fragement.bn.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.api.Api;

public class HomeVM extends ViewModel {
    private MutableLiveData<Integer> notificationCount = new MutableLiveData<>();

    public void fetchNotificationCount() {
        notificationCount.postValue(Api.fetchRingNotification());
    }

    public LiveData<Integer> getNotificationCount() {
        return notificationCount;
    }
}