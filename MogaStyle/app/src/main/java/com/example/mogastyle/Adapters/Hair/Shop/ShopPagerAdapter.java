package com.example.mogastyle.Adapters.Hair.Shop;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.example.mogastyle.Activities.Review.ReviewFragment;
import com.example.mogastyle.Activities.Hair.Designer.DesignerFragment;
import com.example.mogastyle.Activities.Hair.Menu.MenuFragment;
import com.example.mogastyle.Activities.Hair.Shop.ShopHomeFragment;

import java.util.ArrayList;

public class ShopPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<String> name = new ArrayList<>();
    private ArrayList<Fragment> arrayList = new ArrayList<>();
    /////////////////////////////////////////////////////////
    //       Shop no를 Shop home activity로부터 받아옴        //
    /////////////////////////////////////////////////////////
    int sno = 0;

    public ShopPagerAdapter(FragmentManager fm, int sno) {
        super(fm);
        arrayList.add(new ShopHomeFragment(sno));
        arrayList.add(new MenuFragment());
        arrayList.add(new DesignerFragment());
        arrayList.add(new ReviewFragment());
        this.sno = sno;

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
