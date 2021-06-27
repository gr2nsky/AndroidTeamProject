package com.example.mogastyle.Adapters.Home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mogastyle.Activities.Home.HomeFragment;
import com.example.mogastyle.Activities.Home.HomeHairTypeFragment;
import com.example.mogastyle.Activities.Home.HomeShopListFragment;
import com.example.mogastyle.Common.LoginedUserInfo;

import org.jetbrains.annotations.NotNull;

public class HomePageAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;

    public HomePageAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }


    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return HomeHairTypeFragment.newInstance(0,"Page#1" , LoginedUserInfo.user.getName());

            case 1:
                return HomeFragment.newInstance(1,"Page#2", LoginedUserInfo.user.getName());

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
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page" + position;
    }


}
