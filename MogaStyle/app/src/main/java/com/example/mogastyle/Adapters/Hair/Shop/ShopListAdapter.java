package com.example.mogastyle.Adapters.Hair.Shop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
    private int no;

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
        convertView = inflater.inflate(this.layout, parent, false);
        TextView tv_homelist_name = convertView.findViewById(R.id.tv_hairlist_name);
        TextView tv_homelist_tel = convertView.findViewById(R.id.tv_hairlist_tel);
        TextView tv_homelist_address = convertView.findViewById(R.id.tv_hairlist_address);
        TextView tv_homelist_postcode = convertView.findViewById(R.id.tv_hairlist_postcode);
        TextView tv_homelist_introduce = convertView.findViewById(R.id.tv_hairlist_introduction);
        TextView tv_homelist_holiday = convertView.findViewById(R.id.tv_hairlist_holiday);
        TextView tv_homelist_image = convertView.findViewById(R.id.img_hairlist_image);

        tv_homelist_name.setText("이름 : "+ data.get(position).getName());
        tv_homelist_tel.setText("전화번호 : " + data.get(position).getTel());
        tv_homelist_address.setText("주소 : "+ data.get(position).getAddress());
        tv_homelist_postcode.setText("우편번호 : "+ data.get(position).getPostcode());
        tv_homelist_introduce.setText("소개 : " + data.get(position).getIntroduction());
        tv_homelist_holiday.setText("쉬는 날 : " + data.get(position).getHoliday());
        tv_homelist_image.setText("사진 : "+ data.get(position).getImage());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no = data.get(position).getNo();
                Intent intent = new Intent(mContext, ShopHomeActivity.class);
                intent.putExtra("smo",data.get(position).getNo());
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }
    

    public int selectedShopNo(){
        return no;
    }


}
