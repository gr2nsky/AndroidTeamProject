package com.example.mogastyle.Adapters.Hair.Designer;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mogastyle.Activities.Hair.Designer.DesignerDetailPageActivity;
import com.example.mogastyle.Adapters.Hair.Styling.StylingRecyclerAdapter;
import com.example.mogastyle.Bean.Designer;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DesignerAdapter extends RecyclerView.Adapter<DesignerAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_image;
        TextView tv_no, tv_name, tv_introduction;

        public ViewHolder(View item) {
            super(item);
            iv_image = item.findViewById(R.id.iv_designer_list_image);
            tv_no = item.findViewById(R.id.tv_designer_list_no);
            tv_name = item.findViewById(R.id.tv_designer_list_name);
            tv_introduction = item.findViewById(R.id.tv_designer_list_introduction);

        }
    }

    //---------------------------------------
    private Context mContext = null;
    private int layout = 0;
    private ArrayList<Designer> data = null;
    private LayoutInflater inflater = null;

    // 생성자(Constructor)
    public DesignerAdapter(Context mContext, int layout, ArrayList<Designer> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_desiner_detail_view, parent, false);
        DesignerAdapter.ViewHolder viewHolder = new DesignerAdapter.ViewHolder(v);
        return viewHolder;
    }

    // 화면 구성 메소드 = setText
    @Override
    public void onBindViewHolder(DesignerAdapter.ViewHolder v, int position) {
        Log.v("Message", data.get(position).print());

        //이미지 :
        Glide.with(mContext)
                .load(ShareVar.userImgPath + data.get(position).getImage())
                .placeholder(R.drawable.ic_no_image)
                .error(R.drawable.ic_no_image)
                .fallback(R.drawable.ic_no_image)
                .into(v.iv_image);
        v.tv_no.setText("No. " + data.get(position).getNo());
        v.tv_name.setText("이름 : " + data.get(position).getName());
        v.tv_introduction.setText("소개 : " + data.get(position).getIntroduction());


        // RecyclerView 클릭시 - viewHolder의 itemView
        v.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Message", "click");
                Intent intent = new Intent(mContext, DesignerDetailPageActivity.class);
                intent.putExtra("dno", data.get(position).getNo());
                mContext.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return data.size();
    }



} // ---------------------------------------------
