package com.example.mogastyle.Adapters.Hair.Shop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mogastyle.Activities.Hair.Shop.ShopHomeFragment;
import com.example.mogastyle.Bean.Shop;
import com.example.mogastyle.R;

import java.util.ArrayList;

public class ShopHomeAdapter extends BaseAdapter {

    private Context mContext = null;
    private  int layout = 0;
    private ArrayList<Shop> data = null;
    private LayoutInflater inflater = null;
    private int no;

    public ShopHomeAdapter(Context mContext, int layout, ArrayList<Shop> data ) {
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
        TextView tv_name = convertView.findViewById(R.id.tv_shopmain_title);
        TextView tv_tel = convertView.findViewById(R.id.edt_shop_tel);
        TextView tv_address = convertView.findViewById(R.id.edt_shop_address);
        TextView tv_postcode = convertView.findViewById(R.id.tv_home_postcode);
        TextView tv_introduce = convertView.findViewById(R.id.edt_shop_introduction);
        TextView tv_holiday = convertView.findViewById(R.id.tv_home_holiday);
        TextView tv_image = convertView.findViewById(R.id.img_home_image);

        tv_name.setText("이름 : "+ data.get(position).getName());
        tv_tel.setText("전화번호 : " + data.get(position).getTel());
        tv_address.setText("주소 : "+ data.get(position).getAddress());
        tv_postcode.setText("우편번호 : "+ data.get(position).getPostcode());
        tv_introduce.setText("소개 : " + data.get(position).getIntroduction());
        tv_holiday.setText("쉬는 날 : " + data.get(position).getHoliday());
        tv_image.setText("사진 : "+ data.get(position).getImage());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no = data.get(position).getNo();
                Intent intent = new Intent(mContext, ShopHomeFragment.class);
                mContext.startActivity(intent);
            }
        });

        return convertView;

    }

    public int selectedShopNo(){
        return no;
    }
}
