package com.example.mogastyle.Adapters.Diary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mogastyle.Bean.Diary.DiaryBookList;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class DiaryAdapter extends BaseAdapter {
    private Context mContext = null;
    private int layout = 0;
    private ArrayList<DiaryBookList> data = null;
    private LayoutInflater inflater= null;

    public DiaryAdapter(Context mContext, int layout, ArrayList<DiaryBookList> data) {
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
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(this.layout , parent , false);
        ImageView diary_image = convertView.findViewById(R.id.diary_image);
        TextView diary_date = convertView.findViewById(R.id.diary_date);
        TextView diary_shop = convertView.findViewById(R.id.diary_shop);
        TextView diary_designer = convertView.findViewById(R.id.diary_designer);

        Glide.with(convertView)
                .load(ShareVar.diaryImgPath + data.get(position).getImage())
                .into(diary_image);

        diary_date.setText(data.get(position).getStyleDate());
        diary_shop.setText(data.get(position).getHairShop());
        diary_designer.setText(data.get(position).getDesignerName());

        return convertView;
    }
}