package com.example.mogastyle.Adapters.Hair.Reservation;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mogastyle.Bean.ResDateData;
import com.example.mogastyle.R;

import java.util.ArrayList;

public class ResDateSelectorAdapter extends RecyclerView.Adapter<ResDateSelectorAdapter.ResDateSelectViewHolder> {

    String TAG = "ResDateSelectorAdapter";

    public class ResDateSelectViewHolder extends RecyclerView.ViewHolder{

        TextView tv_day;
        TextView tv_date;
        TextView tv_today;

        public ResDateSelectViewHolder(View itemView) {
            super(itemView);

            tv_day = itemView.findViewById(R.id.tv_list_item_res_data_selector_day);
            tv_date = itemView.findViewById(R.id.tv_list_item_res_data_selector_date);
            tv_today = itemView.findViewById(R.id.tv_list_item_res_data_selector_today);
        }
    }

    private Context con;
    private int layout = 0;
    private LayoutInflater inflater = null;
    private ArrayList<ResDateData> dds = null;
    private ArrayList<RecyclerView.ViewHolder> holderList = null;
    private int selectedPosition = -1;

    public ResDateSelectorAdapter(Context con, int layout, ArrayList<ResDateData> dds) {
        this.con = con;
        this.layout = layout;
        inflater = (LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.dds = dds;
        holderList = new ArrayList<>();
    }

    //최초 아이템 생성
    @Override
    public ResDateSelectorAdapter.ResDateSelectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_reservation_date_selector, parent, false);
        ResDateSelectViewHolder viewHolder = new ResDateSelectViewHolder(v);
        return viewHolder;
    }

    //생성된 아이템 재활용
    @Override
    public void onBindViewHolder(ResDateSelectorAdapter.ResDateSelectViewHolder holder, int position) {
        ResDateData dd = dds.get(position);
        Log.v(TAG, "dd : " + dd.printAll());
        holder.tv_day.setText(dd.getDay());
        holder.tv_date.setText(Integer.toString(dd.getDate()));
        if(position == 0){
            holder.tv_today.setText("오늘");
        }
        if (selectedPosition == position){
            holder.itemView.setBackgroundColor(con.getResources().getColor(R.color.high_light_color));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        if (!holderList.contains(holder)) holderList.add(holder);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0 ; i < holderList.size(); i++){
                    holderList.get(i).itemView.setBackgroundColor(Color.parseColor("#ffffff"));
                }
                holder.itemView.setBackgroundColor(con.getResources().getColor(R.color.high_light_color));
                selectedPosition = position;
            }
        });
    }

    @Override
    public int getItemCount() {
        return dds.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public ResDateData getSelectedData(){
        if(selectedPosition == -1){
            return null;
        }
        return dds.get(selectedPosition);
    }

}
