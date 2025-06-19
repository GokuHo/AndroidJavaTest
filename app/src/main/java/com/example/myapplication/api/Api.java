package com.example.myapplication.api;

import com.example.myapplication.model.NewCoin;

import java.util.Set;
import java.util.TreeSet;

public class Api {
    public static int ringNotificationCount = 100;
    public static Set<NewCoin> newCoins = new TreeSet<>();

    public Api() {
        newCoins.add(new NewCoin(0, "https://fastly.picsum.photos/id/649/200/200.jpg?hmac=tj148mYv7Me5ctSyCePc_TNjma4W3n3RwnqJcIogLoI", "NOT", 0.005222, 0.005222, -21.01));
        newCoins.add(new NewCoin(1, "https://fastly.picsum.photos/id/649/200/200.jpg?hmac=tj148mYv7Me5ctSyCePc_TNjma4W3n3RwnqJcIogLoI", "NOT", 0.005222, 0.005222, -21.01));
        newCoins.add(new NewCoin(2, "https://fastly.picsum.photos/id/649/200/200.jpg?hmac=tj148mYv7Me5ctSyCePc_TNjma4W3n3RwnqJcIogLoI", "NOT", 0.005222, 0.005222, -21.01));
    }

    public static int fetchRingNotification() {
        return ringNotificationCount;
    }
}
