package com.example.mogastyle.Adapters.Hair.Shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mogastyle.Bean.Shop;
import com.example.mogastyle.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ShopList extends BaseAdapter {
    private Context mContext = null;
    private  int layout = 0;
    private ArrayList<Shop> data = null;
    private LayoutInflater inflater = null;

    public ShopList(Context mContext, int layout, ArrayList<Shop> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
        this.inflater = inflater;
    }

    public ShopList(String name, String tel, String address, String postcode, int image) {
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
        TextView tv_name = convertView.findViewById(R.id.tv_name_list);
        TextView tv_tel = convertView.findViewById(R.id.tv_tel_list);
        TextView tv_address = convertView.findViewById(R.id.tv_address_list);
        TextView tv_postcode = convertView.findViewById(R.id.tv_postcode_list);
        TextView tv_introduce = convertView.findViewById(R.id.tv_introduction_list);
        TextView tv_holiday = convertView.findViewById(R.id.tv_holiday_list);
        TextView tv_phone = convertView.findViewById(R.id.img_image_list);

        tv_name.setText("이름 : "+ data.get(position).getName());
        tv_tel.setText("전화번호 : " + data.get(position).getTel());
        tv_address.setText("주소 : "+ data.get(position).getAddress());
        tv_postcode.setText("우편번호 : "+ data.get(position).getPostcode());
        tv_introduce.setText("소개 : " + data.get(position).getIntroduction());
        tv_holiday.setText("쉬는 날 : " + data.get(position).getHoliday());
        tv_phone.setText("사진 : "+ data.get(position).getImage());

        return convertView;
    }
}
