package com.example.myapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.fragement.Capital;
import com.example.myapplication.fragement.Contract;
import com.example.myapplication.fragement.Home;
import com.example.myapplication.fragement.Quotation;
import com.example.myapplication.fragement.Transaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottom_navigation;
    private final Fragment home = new Home();
    private final Fragment quotation = new Quotation();
    private final Fragment transaction = new Transaction();
    private final Fragment contract = new Contract();
    private final Fragment capital = new Capital();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, home)
                    .commit();
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            bottom_navigation = findViewById(R.id.bottom_navigation);
            bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                    Fragment selectedFragment = null;
                    int itemId = item.getItemId();

                    if(itemId == R.id.bn_quotation) {
                        selectedFragment = quotation;

                    } else if(itemId == R.id.bn_transaction) {
                        selectedFragment = transaction;

                    } else if(itemId == R.id.bn_contract) {
                        selectedFragment = contract;

                    } else if(itemId == R.id.bn_capital) {
                        selectedFragment = capital;

                    } else {
                        selectedFragment = home;
                    }

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();

                    fragmentTransaction.commit();
                    return true;
                }
            });

            return insets;
        });
    }


}