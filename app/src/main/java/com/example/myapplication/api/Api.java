package com.example.myapplication.api;

import android.util.Log;

import com.example.myapplication.api.dto.GetNewCoinsDto;
import com.example.myapplication.model.NewCoin;

import java.util.ArrayList;
import java.util.List;

public class Api {
    public static int ringNotificationCount = 100;
    private static final int EACH_NEW_COINS = 6;
    public static List<NewCoin> newCoins = new ArrayList<>() {{
        add(new NewCoin(0, "https://fastly.picsum.photos/id/999/200/200.jpg?hmac=iwXALEStJtHL4Thxk_YbLNHNmjq9ZrIQYFUvtxndOaU", "NOT", 0.005222, 0.005222, -21.01));
        add(new NewCoin(1, "https://fastly.picsum.photos/id/649/200/200.jpg?hmac=tj148mYv7Me5ctSyCePc_TNjma4W3n3RwnqJcIogLoI", "BB", 0.3105, 0.3105, -11.39));
        add(new NewCoin(2, "https://fastly.picsum.photos/id/503/200/200.jpg?hmac=genECHjox9165KfYsOiMMCmN-zGqh9u-lnhqcFinsrU", "REZ", 0.1023, 0.1023, -6.23));
        add(new NewCoin(3, "https://fastly.picsum.photos/id/337/200/200.jpg?hmac=9bd24xSAcmLdObO71hB9dXskhXQmQ2b0YB3QTAzhUtY", "OMNI", 13.68, 13.68, -7.57));
        add(new NewCoin(4, "https://fastly.picsum.photos/id/371/200/200.jpg?hmac=VShu_HdkBA6-hi8lkHlFMbkqxiu0BgA4mvEKoJke228", "TAO", 364.1, 364.1, -5.16));
        add(new NewCoin(5, "https://fastly.picsum.photos/id/970/200/200.jpg?hmac=KSuLGYyLCaNNAlylEC4xTZgle3Y_8zxKhAIXJaLCY4o", "SAGA", 1.9916, 1.9916, -10.77));

        add(new NewCoin(6, "https://fastly.picsum.photos/id/999/200/200.jpg?hmac=iwXALEStJtHL4Thxk_YbLNHNmjq9ZrIQYFUvtxndOaU", "NOT1", 0.005222, 0.005222, -21.01));
        add(new NewCoin(7, "https://fastly.picsum.photos/id/649/200/200.jpg?hmac=tj148mYv7Me5ctSyCePc_TNjma4W3n3RwnqJcIogLoI", "BB1", 0.3105, 0.3105, -11.39));
        add(new NewCoin(8, "https://fastly.picsum.photos/id/503/200/200.jpg?hmac=genECHjox9165KfYsOiMMCmN-zGqh9u-lnhqcFinsrU", "REZ1", 0.1023, 0.1023, -6.23));
        add(new NewCoin(9, "https://fastly.picsum.photos/id/337/200/200.jpg?hmac=9bd24xSAcmLdObO71hB9dXskhXQmQ2b0YB3QTAzhUtY", "OMNI1", 13.68, 13.68, 7.57));
        add(new NewCoin(10, "https://fastly.picsum.photos/id/371/200/200.jpg?hmac=VShu_HdkBA6-hi8lkHlFMbkqxiu0BgA4mvEKoJke228", "TAO1", 364.1, 364.1, -5.16));
        add(new NewCoin(11, "https://fastly.picsum.photos/id/970/200/200.jpg?hmac=KSuLGYyLCaNNAlylEC4xTZgle3Y_8zxKhAIXJaLCY4o", "SAGA1", 1.9916, 1.9916, -10.77));

        add(new NewCoin(12, "https://fastly.picsum.photos/id/999/200/200.jpg?hmac=iwXALEStJtHL4Thxk_YbLNHNmjq9ZrIQYFUvtxndOaU", "NOT2", 0.005222, 0.005222, -21.01));
        add(new NewCoin(13, "https://fastly.picsum.photos/id/649/200/200.jpg?hmac=tj148mYv7Me5ctSyCePc_TNjma4W3n3RwnqJcIogLoI", "BB2", 0.3105, 0.3105, -11.39));
    }};

    public static int fetchRingNotification() {
        return ringNotificationCount;
    }

    public static GetNewCoinsDto fetchNewCoins(int current) {
        int totalItems = newCoins.size();
        int endIndex = Math.min(current + EACH_NEW_COINS, totalItems);

        return  new GetNewCoinsDto(new ArrayList<>(newCoins.subList(Math.min(current, totalItems), endIndex)), totalItems);
    }
}
