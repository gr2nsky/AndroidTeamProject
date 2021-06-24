package com.example.mogastyle.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mogastyle.Activities.Consult.ConsultFragment;
import com.example.mogastyle.Activities.Hair.Designer.DesignerFragment;
import com.example.mogastyle.Activities.Hair.Menu.MenuFragment;
import com.example.mogastyle.Activities.Hair.Shop.ShopHomeFragment;

import java.util.ArrayList;

class HairMainAdapter extends FragmentPagerAdapter {

    private ArrayList<String> name = new ArrayList<>();
    private ArrayList<Fragment> arrayList = new ArrayList<>();

    public HairMainAdapter(FragmentManager fm) {
        super(fm);
        arrayList.add(new ShopHomeFragment());
        arrayList.add(new MenuFragment());
        arrayList.add(new DesignerFragment());
        arrayList.add(new ConsultFragment());

        name.add("홈");
        name.add("메뉴");
        name.add("디자이너");
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

