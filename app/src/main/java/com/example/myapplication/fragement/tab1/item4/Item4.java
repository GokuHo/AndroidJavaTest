package com.example.myapplication.fragement.tab1.item4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.fragement.bn.home.HomeVM;
import com.example.myapplication.fragement.tab1.recycler.PriceAdapter;

import java.util.ArrayList;
import java.util.List;

public class Item4 extends Fragment {
    private Item4VM viewModel;

    private RecyclerView recyclerview_price;
    private PriceAdapter priceAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab1_item4, container, false);

        recyclerview_price = view.findViewById(R.id.recyclerview_price);

        viewModel = new ViewModelProvider(this).get(Item4VM.class);

        viewModel.fetchApi();

        recyclerview_price.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<String> data = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            data.add("text " + i);
        }

        priceAdapter = new PriceAdapter(data);
        recyclerview_price.setAdapter(priceAdapter);


        return view;
    }
}