package com.example.myapplication.fragement.tab1.item4;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.api.Api;
import com.example.myapplication.api.dto.GetNewCoinsDto;
import com.example.myapplication.model.NewCoin;

import java.util.ArrayList;
import java.util.List;

public class Tab1Item4VM extends ViewModel {
    private MutableLiveData<GetNewCoinsDto> getNewCoinsDtoMLD = new MutableLiveData<>();

    public void fetchNewCoins(int current) {
        GetNewCoinsDto newData = Api.fetchNewCoins(current);

        GetNewCoinsDto currentValue = getNewCoinsDtoMLD.getValue();

        if (currentValue == null) {
            getNewCoinsDtoMLD.postValue(newData);
        } else {
            List<NewCoin> mergedList = new ArrayList<>(currentValue.getNewCoins());
            mergedList.addAll(newData.getNewCoins());

            getNewCoinsDtoMLD.postValue(new GetNewCoinsDto(mergedList, newData.getTotal()));
        }
    }

    public LiveData<GetNewCoinsDto> getNewCoins() {
        return getNewCoinsDtoMLD;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}