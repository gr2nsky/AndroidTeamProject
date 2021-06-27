package com.example.mogastyle.Activities.Hair.Shop;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mogastyle.Bean.Shop;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.R;

public class ShopHomeFragment extends Fragment {
    Context con;
    Shop shopBean = null;
    ImageView imageView;
    TextView tv_shop_tel, tv_shop_address, tv_shop_introduction;
    TextView tv_rating;
    TextView tv_count;

    public ShopHomeFragment(Shop shopBean, Context con) {
        this.shopBean = shopBean;
        this.con = con;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_shop_home,container,false);

        tv_shop_tel = view.findViewById(R.id.tv_shop_tel);
        tv_shop_address = view.findViewById(R.id.tv_shop_address);
        tv_shop_introduction = view.findViewById(R.id.tv_shop_introduction);
        imageView = view.findViewById(R.id.iv_shop_img);
        tv_rating = view.findViewById(R.id.tv_shop_home_rate);
        tv_count = view.findViewById(R.id.tv_shop_home_review_count);

        tv_shop_tel.setText(shopBean.getTel());
        tv_shop_address.setText(shopBean.getAddress());
        tv_shop_introduction.setText(shopBean.getIntroduction());
        Glide.with(con)
                .load(ShareVar.shopImgPath+shopBean.getImage())
                .placeholder(R.drawable.ic_no_image)
                .error(R.drawable.ic_no_image)
                .fallback(R.drawable.ic_no_image)
                .into(imageView);
        double roundedRating = Math.round(shopBean.getRating()*10)/10;
        tv_rating.setText(Double.toString(roundedRating));
        tv_count.setText(Integer.toString(shopBean.getCount()));

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}