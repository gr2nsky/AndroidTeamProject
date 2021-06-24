package com.example.mogastyle.Adapters.Hair.Styling;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mogastyle.Activities.Review.ReviewFragment;
import com.example.mogastyle.Activities.Hair.Styling.StylingInfoFragment;

import java.util.ArrayList;

public class StylingPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<String> name = new ArrayList<>();
    private ArrayList<Fragment> arrayList = new ArrayList<>();

    public StylingPagerAdapter(FragmentManager fm) {
        super(fm);
        arrayList.add(new StylingInfoFragment());
        arrayList.add(new ReviewFragment());

        name.add("정보");
        name.add("리뷰");

    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return name.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }
}

