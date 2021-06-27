package com.example.mogastyle.Adapters.Hair.Designer;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mogastyle.Activities.Hair.Designer.DesignerDetailPageStylingFragment;
import com.example.mogastyle.Activities.Review.ReviewFragment;
import com.example.mogastyle.Bean.Designer;

public class FragmentDesignerDetailPageAdapter extends FragmentPagerAdapter {

    int tabCount;
    Designer designerBean;

    // 생성자
    public FragmentDesignerDetailPageAdapter(FragmentManager fm, int behavior, Designer designerBean) {
        super(fm, behavior);
        this.tabCount = behavior;
        this.designerBean = designerBean;
        Log.v("Message", "FragmentDesignerDetailPageAdapter_FragmentDesignerDetailPageAdapter");
    }

    @Override
    public Fragment getItem(int position) {
        Log.v("Message", "getItem_TabPagerAdapter");
        switch (position){
            // return이므로 break가 필요 없음!
            // 0번 째 있는 것을 띄워라
            case 0:
                Log.v("Message", "getItem_DesignerDetailPageStylingFragment");
                DesignerDetailPageStylingFragment designerDetailPageStylingFragment = new DesignerDetailPageStylingFragment();
                return designerDetailPageStylingFragment;
            case 1:
                Log.v("Message", "getItem_DesignerDetailPageReviewFragment");
                ReviewFragment reviewFragment = new ReviewFragment(designerBean);
                return reviewFragment;
            default:
                return null;
        }
    }

    @Override
    // count 몇 개니?
    public int getCount() {
        Log.v("Message", "getCount_FragmentDesignerDetailPageAdapter");
        return tabCount;

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return "Styling";

            case 1:
                return "Review";

        }

        return null;
    }
}  // ------------------------------------------------

