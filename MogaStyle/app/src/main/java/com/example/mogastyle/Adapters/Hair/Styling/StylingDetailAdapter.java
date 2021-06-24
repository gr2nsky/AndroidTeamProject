package com.example.mogastyle.Adapters.Hair.Styling;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mogastyle.Activities.Hair.Shop.ShopHomeFragment;
import com.example.mogastyle.Bean.Shop;
import com.example.mogastyle.Bean.Styling;
import com.example.mogastyle.R;

import java.util.ArrayList;

public class StylingDetailAdapter extends BaseAdapter {

    private Context mContext = null;
    private  int layout = 0;
    private ArrayList<Styling> data = null;
    private LayoutInflater inflater = null;
    private int no;

    public StylingDetailAdapter(Context mContext, int layout, ArrayList<Styling> data, LayoutInflater inflater) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public StylingDetailAdapter(){

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position).getNo();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(this.layout, parent, false);

        TextView tv_name = convertView.findViewById(R.id.style_detail_name);
        TextView tv_price = convertView.findViewById(R.id.style_detail_price);
        TextView tv_content = convertView.findViewById(R.id.style_detail_image);

        //networktask에서 불러오기



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
