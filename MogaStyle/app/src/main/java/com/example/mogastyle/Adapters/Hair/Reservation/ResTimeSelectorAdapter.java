package com.example.mogastyle.Adapters.Hair.Reservation;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    private int selectedPosition = -1;

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

    @Override
    public void onBindViewHolder(ResTimeSelectorAdapter.ResTimeSelectViewHolder holder, int position) {
        if(!holderList.contains(holder)) holderList.add(holder);

        String t = "";
        if( resableTimeList.get(position) < 10) {
            t += "0";
        }
        t += Integer.toString(resableTimeList.get(position)) + ":00";
        holder.tv_time.setText(t);
        if(selectedPosition == position){
            holder.itemView.setBackgroundColor(con.getResources().getColor(R.color.high_light_color));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < holderList.size(); i++){
                    holderList.get(i).itemView.setBackgroundColor(Color.parseColor("#ffffff"));
                }
                holder.itemView.setBackgroundColor(con.getResources().getColor(R.color.high_light_color));
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
