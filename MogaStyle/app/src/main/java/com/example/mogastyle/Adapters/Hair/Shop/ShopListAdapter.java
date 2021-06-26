package com.example.mogastyle.Adapters.Hair.Shop;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mogastyle.Activities.Hair.Shop.ShopHomeActivity;
import com.example.mogastyle.Bean.Shop;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.R;

import java.util.ArrayList;

public class ShopListAdapter extends BaseAdapter {
    private Context mContext = null;
    private  int layout = 0;
    private ArrayList<Shop> data = null;
    private LayoutInflater inflater = null;

    public ShopListAdapter() {
    }

    public ShopListAdapter(Context mContext, int layout, ArrayList<Shop> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.v("^^^^^^", data.get(position).print());
        convertView = inflater.inflate(this.layout, parent, false);
        TextView tv_homelist_name = convertView.findViewById(R.id.tv_hairlist_name);
        TextView tv_homelist_introduce = convertView.findViewById(R.id.tv_hairlist_introduction);
        ImageView imageView = convertView.findViewById(R.id.img_hairlist_image1);
        TextView tv_rating = convertView.findViewById(R.id.tv_hairlist_rating);
        TextView tv_num_of_review = convertView.findViewById(R.id.tv_hairlist_rewivew_num);

        tv_homelist_name.setText("이름 : "+ data.get(position).getName());
        tv_homelist_introduce.setText("소개 : " + data.get(position).getIntroduction());
        //////////////////////////////////////////////////////////////////////
        //                       리뷰 수 및 평점 가져오기                         //
        //////////////////////////////////////////////////////////////////////
        double roundedRating = Math.round(data.get(position).getRating()*10)/10;
        tv_rating.setText(Double.toString(roundedRating));
        tv_num_of_review.setText(Integer.toString(data.get(position).getCount()));

        Glide.with(mContext)
                .load(ShareVar.shopImgPath+data.get(position).getImage())
                .placeholder(R.drawable.ic_no_image)
                .error(R.drawable.ic_no_image)
                .fallback(R.drawable.ic_no_image)
                .into(imageView);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int no = data.get(position).getNo();
                String name = data.get(position).getName();
                String tel = data.get(position).getTel();
                String address = data.get(position).getAddress();
                String postcode = data.get(position).getPostcode();
                String introduction = data.get(position).getIntroduction();
                String holiday = data.get(position).getHoliday();
                String image = data.get(position).getImage();
                double rating = data.get(position).getRating();
                int count = data.get(position).getCount();

                Intent intent = new Intent(mContext, ShopHomeActivity.class);
                intent.putExtra("smo", no);
                intent.putExtra("name", name);
                intent.putExtra("tel", tel);
                intent.putExtra("address", address);
                intent.putExtra("postcode", postcode);
                intent.putExtra("introduction", introduction);
                intent.putExtra("holiday", holiday);
                intent.putExtra("image", image);
                intent.putExtra("rating", rating);
                intent.putExtra("count", count);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }
}
