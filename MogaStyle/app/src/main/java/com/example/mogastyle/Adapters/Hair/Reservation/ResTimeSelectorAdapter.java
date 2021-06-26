
package com.example.mogastyle.Adapters.Hair.Reservation;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mogastyle.R;

import java.util.ArrayList;


public class ResTimeSelectorAdapter extends RecyclerView.Adapter<ResTimeSelectorAdapter.ResTimeSelectViewHolder>{
    String TAG = "ResTimeSelectorAdapter";

    public class ResTimeSelectViewHolder extends RecyclerView.ViewHolder{

        TextView tv_time;

        public ResTimeSelectViewHolder(View itemView) {
            super(itemView);

            tv_time = itemView.findViewById(R.id.tv_list_item_res_time_selector);
        }
    }

    Context con = null;
    int layout = 0;
    private LayoutInflater inflater = null;
    ArrayList<Integer> resableTimeList = null;
    private ArrayList<RecyclerView.ViewHolder> holderList = null;
    public int selectedPosition = -1;

    //휴무날일떈 이걸로 받음. 999 : 샵 휴무일, 888 : 개인 휴무일, 777 : 예약이 꽉 참
    public ResTimeSelectorAdapter(Context con, int layout, int i) {
        this.con = con;
        this.layout = layout;
        inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        resableTimeList = new ArrayList<>();
        resableTimeList.add(i);
        holderList = new ArrayList<>();
    }

    public ResTimeSelectorAdapter(Context con, int layout, ArrayList<Integer> resableTimeList) {
        this.con = con;
        this.layout = layout;
        inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resableTimeList = resableTimeList;
        holderList = new ArrayList<>();
    }

    @Override
    public ResTimeSelectorAdapter.ResTimeSelectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_reservation_time_selector, parent, false);
        ResTimeSelectorAdapter.ResTimeSelectViewHolder viewHolder = new ResTimeSelectorAdapter.ResTimeSelectViewHolder(v);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(ResTimeSelectorAdapter.ResTimeSelectViewHolder holder, int position) {
        if(!holderList.contains(holder)) holderList.add(holder);

        if(resableTimeList.get(position) == 999){
            holder.tv_time.setText("샵 휴무일");
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            holder.tv_time.setLayoutParams(lp);
            holder.itemView.setBackground(con.getDrawable(R.drawable.background_rounded_conner_border_red));
            return;
        } else if (resableTimeList.get(position) == 888){
            holder.tv_time.setText("개인 휴무일");
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            holder.tv_time.setLayoutParams(lp);
            holder.itemView.setBackground(con.getDrawable(R.drawable.background_rounded_conner_border_red));
            return;
        } else if(resableTimeList.get(position) == 777){
            holder.tv_time.setText("예약이 가능한 시간이 없습니다.");
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            holder.tv_time.setLayoutParams(lp);
            holder.itemView.setBackground(con.getDrawable(R.drawable.background_rounded_conner_border_red));
            return;
        }

        String t = "";
        if( resableTimeList.get(position) < 10) {
            t += "0";
        }
        t += Integer.toString(resableTimeList.get(position)) + ":00";
        holder.tv_time.setText(t);
        if(selectedPosition == position){
            holder.itemView.setBackground(con.getDrawable(R.drawable.background_rounded_conner_border_full));
        } else {
            holder.itemView.setBackground(con.getDrawable(R.drawable.background_rounded_conner_border));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < holderList.size(); i++){
                    holderList.get(i).itemView.setBackground(con.getDrawable(R.drawable.background_rounded_conner_border));
                }
                holder.itemView.setBackground(con.getDrawable(R.drawable.background_rounded_conner_border_full));
                selectedPosition = position;
            }
        });
    }

    @Override
    public int getItemCount() {
        return resableTimeList.size();
    }

    public int getSelectedTime(){
        if(selectedPosition == -1) return -1;
        return resableTimeList.get(selectedPosition);
    }


}
