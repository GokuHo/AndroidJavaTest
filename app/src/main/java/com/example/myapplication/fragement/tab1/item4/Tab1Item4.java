package com.example.myapplication.fragement.tab1.item4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.NewCoin;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Tab1Item4 extends Fragment {
    private Tab1Item4VM viewModel;

    private RecyclerView recyclerview_price;
    private PriceAdapter priceAdapter;
    private TextView seemore;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab1_item4, container, false);

        recyclerview_price = view.findViewById(R.id.recyclerview_price);
        seemore = view.findViewById(R.id.seemore);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(Tab1Item4VM.class);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        recyclerview_price.setLayoutManager(new LinearLayoutManager(getContext()));
        priceAdapter = new PriceAdapter();
        recyclerview_price.setAdapter(priceAdapter);

        viewModel.getNewCoins().observe(getViewLifecycleOwner(), getNewCoinsDto -> {
            if(getNewCoinsDto != null) {
                int previousSize = priceAdapter.getItemCount();

                priceAdapter.submitList(getNewCoinsDto.getNewCoins());

                if(getNewCoinsDto.getTotal() > getNewCoinsDto.getNewCoins().size()) {
                    seemore.setVisibility(View.VISIBLE);

                    if(previousSize > 0 && previousSize < getNewCoinsDto.getNewCoins().size()) {
                        recyclerview_price.post(() -> {
                            recyclerview_price.smoothScrollToPosition(previousSize);
                        });
                    }
                } else {
                    seemore.setVisibility(View.GONE);
                }

                seemore.setOnClickListener(v -> {
                    viewModel.fetchNewCoins(getNewCoinsDto.getNewCoins().size());
                });
            }
        });

        viewModel.fetchNewCoins(0);
    }

    public class PriceAdapter extends RecyclerView.Adapter<PriceAdapter.PriceViewHolder> {

        private List<NewCoin> newCoinList = new ArrayList<>();

        public void submitList(List<NewCoin> newCoins) {
            newCoinList = new ArrayList<>(newCoins);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public PriceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_price, parent, false);
            return new PriceViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PriceViewHolder holder, int position) {
            NewCoin coin = newCoinList.get(position);
            holder.bind(coin);
        }

        @Override
        public int getItemCount() {
            return newCoinList.size();
        }

        private class PriceViewHolder extends RecyclerView.ViewHolder {
            private final CircleImageView icon;
            private final TextView name, price_main, price_convert, price_rate;

            public PriceViewHolder(@NonNull View itemView) {
                super(itemView);
                icon = itemView.findViewById(R.id.icon);
                name = itemView.findViewById(R.id.name);
                price_main = itemView.findViewById(R.id.price_main);
                price_convert = itemView.findViewById(R.id.price_convert);
                price_rate = itemView.findViewById(R.id.price_rate);
            }

            public void bind(NewCoin coin) {
                Glide.with(requireActivity())
                        .load(coin.getIcon())
                        .placeholder(R.color.init)
                        .error(R.color.init)
                        .into(icon);

                name.setText(coin.getName());
                price_main.setText(coin.getPrice()+"");
                price_convert.setText("$"+coin.getConvertedPrice());
                price_rate.setText(coin.getRate()+"%");

                if(coin.getRate() > 0) {
                    price_rate.setBackgroundResource(R.drawable.rate_rise_bg);
                } else {
                    price_rate.setBackgroundResource(R.drawable.rate_decline_bg);
                }
            }
        }
    }
}