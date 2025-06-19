package com.example.myapplication.fragement.bn.home;

import static androidx.core.content.ContextCompat.getSystemService;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.example.myapplication.fragement.tab1.item4.Item4;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class Home extends Fragment {

    private HomeVM viewModel;
    private EditText searchBar;
    private ImageView ring;
    private TextView ringCount;
    private TabLayout tab1;
    private ViewPager2 pager1;
    private List<Fragment> fragments;
    private List<String> tabNames;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initFragmentsAndTabs();
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    private void initFragmentsAndTabs() {
        fragments = new ArrayList<>();
        fragments.add(new Item4());
        fragments.add(new Item4());
        fragments.add(new Item4());
        fragments.add(new Item4());
        fragments.add(new Item4());
        fragments.add(new Item4());

//        fragments.add(new Item1());
//        fragments.add(new Item2());
//        fragments.add(new Item3());
//        fragments.add(new Item4());
//        fragments.add(new Item5());
//        fragments.add(new Item6());

        tabNames = new ArrayList<>();
        tabNames.add(getResources().getString(R.string.tab1_1));
        tabNames.add(getResources().getString(R.string.tab1_2));
        tabNames.add(getResources().getString(R.string.tab1_3));
        tabNames.add(getResources().getString(R.string.tab1_4));
        tabNames.add(getResources().getString(R.string.tab1_5));
        tabNames.add(getResources().getString(R.string.tab1_6));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchBar = view.findViewById(R.id.searchBar);
        ring = view.findViewById(R.id.ring);
        ringCount = view.findViewById(R.id.ringCount);
        tab1 = view.findViewById(R.id.tab1);
        pager1 = view.findViewById(R.id.pager1);

        viewModel = new ViewModelProvider(this).get(HomeVM.class);

        searchBarFeature();
        ringNotificationFeature();
        tab1Feature();

    }


    @SuppressLint("ClickableViewAccessibility")
    private void searchBarFeature() {
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    searchBar.setCompoundDrawablesWithIntrinsicBounds(R.drawable.search, 0, R.drawable.clear, 0);
                } else {
                    searchBar.setCompoundDrawablesWithIntrinsicBounds(R.drawable.search, 0, 0, 0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        searchBar.setOnTouchListener((v, event) -> {
            final int DRAWABLE_LEFT = 0;
            final int DRAWABLE_RIGHT = 2;

            EditText et = (EditText) v;
            Drawable[] drawables = et.getCompoundDrawables();

            if (event.getAction() == MotionEvent.ACTION_UP) {
                int[] location = new int[2];
                et.getLocationOnScreen(location);
                int viewLeft = location[0];
                int viewRight = location[0] + et.getWidth();

                if (drawables[DRAWABLE_RIGHT] != null) {
                    int drawableRightWidth = drawables[DRAWABLE_RIGHT].getBounds().width();
                    int rightIconLeft = viewRight - drawableRightWidth - et.getPaddingRight();
                    if (event.getRawX() >= rightIconLeft) {
                        et.setText("");
                        et.performClick();
                        return true;
                    }
                }

                if (drawables[DRAWABLE_LEFT] != null) {
                    int drawableLeftWidth = drawables[DRAWABLE_LEFT].getBounds().width();
                    int leftIconRight = viewLeft + drawableLeftWidth + et.getPaddingLeft();
                    if (event.getRawX() <= leftIconRight) {
                        String text = et.getText().toString();
                        if(text.isEmpty()) return false;
                        Toast.makeText(et.getContext(), text, Toast.LENGTH_SHORT).show();
                        et.performClick();
                        return true;
                    }
                }
            }
            return false;
        });
    }

    private void ringNotificationFeature() {
        viewModel.getNotificationCount().observe(getViewLifecycleOwner(), count -> {
            if(count == null || count <= 0) {
                ringCount.setVisibility(View.GONE);
                return;
            }

            ringCount.setVisibility(View.VISIBLE);
            if(count >= 100) {
                ringCount.setText("99+");
            } else {
                ringCount.setText(count+"");
            }
        });
        viewModel.fetchNotificationCount();


        ring.setOnClickListener(v -> {
            ringCount.setVisibility(View.GONE);
        });

        ringCount.setOnClickListener(v -> {
            ringCount.setVisibility(View.GONE);
        });
    }

    private void tab1Feature() {
        FragmentStateAdapter pagerAdapter = new ScreenSlidePagerAdapter(requireActivity());
        pager1.setAdapter(pagerAdapter);

        new TabLayoutMediator(tab1, pager1, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabNames.get(position));
            }
        }).attach();
    }

    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @NonNull
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