package com.example.myapplication.fragement.bn;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.example.myapplication.fragement.tab1.Item1;
import com.example.myapplication.fragement.tab1.Item2;
import com.example.myapplication.fragement.tab1.Item3;
import com.example.myapplication.fragement.tab1.Item4;
import com.example.myapplication.fragement.tab1.Item5;
import com.example.myapplication.fragement.tab1.Item6;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {

    private TabLayout tab1;
    private ViewPager2 pager1;
    private List<Fragment> fragments;
    private List<String> tabNames;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        fragments = new ArrayList<>();
        fragments.add(new Item1());
        fragments.add(new Item2());
        fragments.add(new Item3());
        fragments.add(new Item4());
        fragments.add(new Item5());
        fragments.add(new Item6());

        tabNames = new ArrayList<>();
        tabNames.add(getResources().getString(R.string.tab1_1));
        tabNames.add(getResources().getString(R.string.tab1_2));
        tabNames.add(getResources().getString(R.string.tab1_3));
        tabNames.add(getResources().getString(R.string.tab1_4));
        tabNames.add(getResources().getString(R.string.tab1_5));
        tabNames.add(getResources().getString(R.string.tab1_6));

        tab1 = view.findViewById(R.id.tab1);
        pager1 = view.findViewById(R.id.pager1);

        FragmentStateAdapter pagerAdapter = new ScreenSlidePagerAdapter(requireActivity());
        pager1.setAdapter(pagerAdapter);

        new TabLayoutMediator(tab1, pager1, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabNames.get(position));
            }
        }).attach();

        return view;
    }

    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemCount() {
            return fragments.size();
        }
    }

}