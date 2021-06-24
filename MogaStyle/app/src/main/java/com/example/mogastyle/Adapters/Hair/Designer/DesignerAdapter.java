package com.example.mogastyle.Adapters.Hair.Designer;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mogastyle.Activities.Hair.Designer.DesignerDetailPageActivity;
import com.example.mogastyle.Bean.Designer;
import com.example.mogastyle.R;

import java.util.ArrayList;

public class DesignerAdapter extends BaseAdapter {

    private Context mContext = null;
    private int layout = 0;
    private ArrayList<Designer> data = null;
    private LayoutInflater inflater = null;

    // 생성자(Constructor) : inflater는 ()괄호 안에 들어가면 안 돼!
    public DesignerAdapter(Context mContext, int layout, ArrayList<Designer> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // Data 개수 세기
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
        Log.v("Message", "DesignerAdapter_getView");

        convertView = inflater.inflate(this.layout, parent, false);

        // designer listView : ID 값 불러오기
        TextView tv_no = convertView.findViewById(R.id.tv_designer_list_no);
        TextView tv_name = convertView.findViewById(R.id.tv_designer_list_name);
        TextView tv_introduction = convertView.findViewById(R.id.tv_designer_list_introduction);

        // 화면에 보여주기
        tv_no.setText("No. " + data.get(position).getNo());
        tv_name.setText("이름 : " + data.get(position).getName());
        tv_introduction.setText("소개 : " + data.get(position).getIntroduction());

        // listView 클릭시
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Message", "click");
                Intent intent = new Intent(mContext, DesignerDetailPageActivity.class);
                intent.putExtra("dno", data.get(position).getNo());
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

} // ---------------------------------------------
