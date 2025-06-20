package com.example.myapplication.fragement.bn.home;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.myapplication.fragement.tab1.item1.Tab1Item1;
import com.example.myapplication.fragement.tab1.item2.Tab1Item2;
import com.example.myapplication.fragement.tab1.item3.Tab1Item3;
import com.example.myapplication.fragement.tab1.item4.Tab1Item4;
import com.example.myapplication.fragement.tab1.item5.Tab1Item5;
import com.example.myapplication.fragement.tab1.item6.Tab1Item6;
import com.example.myapplication.fragement.tab2.item1.Tab2Item1;
import com.example.myapplication.fragement.tab2.item2.Tab2Item2;
import com.example.myapplication.fragement.tab2.item3.Tab2Item3;
import com.example.myapplication.fragement.tab2.item4.Tab2Item4;
import com.example.myapplication.fragement.tab2.item5.Tab2Item5;
import com.example.myapplication.fragement.tab2.item6.Tab2Item6;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {

    private HomeVM viewModel;
    private EditText searchBar;
    private ImageView ring;
    private TextView ringCount;
    private TabLayout tab1, tab2;
    private ViewPager2 pager1, pager2;
    private List<Fragment> fragments1, fragments2;
    private List<String> tabNames1, tabNames2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initFragmentsAndTabs();
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    private void initFragmentsAndTabs() {
        fragments1 = new ArrayList<>();

        fragments1.add(new Tab1Item1());
        fragments1.add(new Tab1Item2());
        fragments1.add(new Tab1Item3());
        fragments1.add(new Tab1Item4());
        fragments1.add(new Tab1Item5());
        fragments1.add(new Tab1Item6());

        tabNames1 = new ArrayList<>();
        tabNames1.add(getResources().getString(R.string.tab1_1));
        tabNames1.add(getResources().getString(R.string.tab1_2));
        tabNames1.add(getResources().getString(R.string.tab1_3));
        tabNames1.add(getResources().getString(R.string.tab1_4));
        tabNames1.add(getResources().getString(R.string.tab1_5));
        tabNames1.add(getResources().getString(R.string.tab1_6));

        fragments2 = new ArrayList<>();

        fragments2.add(new Tab2Item1());
        fragments2.add(new Tab2Item2());
        fragments2.add(new Tab2Item3());
        fragments2.add(new Tab2Item4());
        fragments2.add(new Tab2Item5());
        fragments2.add(new Tab2Item6());

        tabNames2 = new ArrayList<>();
        tabNames2.add(getResources().getString(R.string.tab2_1));
        tabNames2.add(getResources().getString(R.string.tab2_2));
        tabNames2.add(getResources().getString(R.string.tab2_3));
        tabNames2.add(getResources().getString(R.string.tab2_4));
        tabNames2.add(getResources().getString(R.string.tab2_5));
        tabNames2.add(getResources().getString(R.string.tab2_6));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchBar = view.findViewById(R.id.searchBar);
        ring = view.findViewById(R.id.ring);
        ringCount = view.findViewById(R.id.ringCount);
        tab1 = view.findViewById(R.id.tab1);
        tab2 = view.findViewById(R.id.tab2);
        pager1 = view.findViewById(R.id.pager1);
        pager2 = view.findViewById(R.id.pager2);

        viewModel = new ViewModelProvider(this).get(HomeVM.class);

        searchBarFeature();
        ringNotificationFeature();
//        tab1Feature();
        tab2Feature();
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
        FragmentStateAdapter pagerAdapter1 = new ScreenSlidePagerAdapter1(requireActivity());
        pager1.setAdapter(pagerAdapter1);

        new TabLayoutMediator(tab1, pager1, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabNames1.get(position));
            }
        }).attach();
    }
    private void tab2Feature() {
        FragmentStateAdapter pagerAdapter2 = new ScreenSlidePagerAdapter2(requireActivity());
        pager2.setAdapter(pagerAdapter2);

        new TabLayoutMediator(tab2, pager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabNames2.get(position));
            }
        }).attach();
    }

    private class ScreenSlidePagerAdapter1 extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter1(FragmentActivity fa) {
            super(fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments1.get(position);
        }

        @Override
        public int getItemCount() {
            return fragments1.size();
        }
    }

    private class ScreenSlidePagerAdapter2 extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter2(FragmentActivity fa) {
            super(fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments2.get(position);
        }

        @Override
        public int getItemCount() {
            return fragments2.size();
        }
    }

}