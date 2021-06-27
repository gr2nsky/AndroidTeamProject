package com.example.mogastyle.Adapters.Hair.Shop;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.example.mogastyle.Activities.Review.ReviewFragment;
import com.example.mogastyle.Activities.Hair.Designer.DesignerFragment;
import com.example.mogastyle.Activities.Hair.Menu.MenuFragment;
import com.example.mogastyle.Activities.Hair.Shop.ShopHomeFragment;
import com.example.mogastyle.Bean.Designer;
import com.example.mogastyle.Bean.Shop;
import com.example.mogastyle.Bean.Styling;

import java.util.ArrayList;

public class ShopPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<String> name = new ArrayList<>();
    private ArrayList<Fragment> arrayList = new ArrayList<>();
    /////////////////////////////////////////////////////////
    //       Shop을 Shop home activity로부터 받아옴        //
    /////////////////////////////////////////////////////////
    Shop shopBean = null;
    Styling StylingBean = null;
    Designer DesignerBean = null;
    Context con;

    public ShopPagerAdapter(FragmentManager fm, Shop shopBean, Styling StylingBean, Designer DesignerBean, Context con) {
        super(fm);
        this.shopBean = shopBean;
        this.StylingBean = StylingBean;
        this.DesignerBean = DesignerBean;
        this.con = con;

        arrayList.add(new ShopHomeFragment(shopBean, con));
        arrayList.add(new MenuFragment(StylingBean, con));
        arrayList.add(new DesignerFragment(DesignerBean, con));
        arrayList.add(new ReviewFragment(shopBean, con));

        name.add("홈");
        name.add("스타일링");
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
