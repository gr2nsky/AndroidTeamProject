package com.example.mogastyle.Adapters.Home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mogastyle.Activities.Home.HomeFragment;
import com.example.mogastyle.Activities.Home.HomeHairTypeFragment;
import com.example.mogastyle.Activities.Home.HomeShopListFragment;

import org.jetbrains.annotations.NotNull;

public class HomePageAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;

    public HomePageAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return HomeHairTypeFragment.newInstance(0,"Page#1");

            case 1:
                return HomeFragment.newInstance(1,"Page#2");

            case 2:
                return HomeShopListFragment.newInstance(2,"Page#3");
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page" + position;
    }
}
