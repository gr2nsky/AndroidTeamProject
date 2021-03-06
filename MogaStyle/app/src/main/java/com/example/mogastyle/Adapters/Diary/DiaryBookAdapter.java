package com.example.mogastyle.Adapters.Diary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mogastyle.Bean.Diary.Diary;
import com.example.mogastyle.Common.LoginedUserInfo;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class DiaryBookAdapter extends BaseAdapter {
    private Context mContext = null;
    private int layout = 0;
    private ArrayList<Diary> data = null;
    private LayoutInflater inflater= null;

    public DiaryBookAdapter(Context mContext, int layout, ArrayList<Diary> data) {
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
        Log.d("AAA" , "convert Enter");
        convertView = inflater.inflate(this.layout , parent , false);
        ImageView origin_image = convertView.findViewById(R.id.iv_diary_select_origin_image);
        TextView origin_title = convertView.findViewById(R.id.iv_diary_select_origin_title);
        TextView origin_styleLength = convertView.findViewById(R.id.iv_diary_select_origin_styleLength);
//        TextView origin_styleCount = convertView.findViewById(R.id.origin_styleCount);

        //이미지 작업
        //TODO
        Glide.with(convertView)
                .load(ShareVar.diaryImgPath + data.get(position).getImage())
                .into(origin_image);
        ///

        origin_title.setText(data.get(position).getTitle());
        origin_styleLength.setText(data.get(position).getHairLength());
//        origin_styleCount.setText("총 " + data.get(position).getInfoCount() + " 개의 기록");

        return convertView;
    }

    //====



}

