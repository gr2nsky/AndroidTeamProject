package com.example.mogastyle.Adapters.Hair.Reservation;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mogastyle.Activities.Hair.Reservation.ReservationCheckActivity;
import com.example.mogastyle.NetworkTasks.Hair.Reservation.RetrofitCall;
import com.example.mogastyle.NetworkTasks.Hair.Reservation.RetrofitService;
import com.example.mogastyle.Bean.ReservationBean;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResCheckAdapter extends RecyclerView.Adapter<ResCheckAdapter.ResCheckViewHolder>{

    String TAG = "ResCheckAdapter";

    public class ResCheckViewHolder extends RecyclerView.ViewHolder{

        ImageView shop_img;
        TextView shop_name;
        TextView shop_addr;
        ImageView designer_img;
        TextView designer_name;
        TextView style_title;
        TextView tv_date;
        TextView btn_bottom;
        public ResCheckViewHolder(View itemView) {
            super(itemView);
            shop_img = itemView.findViewById(R.id.img_list_item_res_check_shop);
            shop_name = itemView.findViewById(R.id.tv_list_item_res_check_shop_name);
            shop_addr = itemView.findViewById(R.id.tv_list_item_res_check_shop_addr);
            designer_img = itemView.findViewById(R.id.img_list_item_res_check_designer);
            designer_name = itemView.findViewById(R.id.tv_list_item_res_check_designer_name);
            style_title = itemView.findViewById(R.id.tv_list_item_res_check_designer_menu);
            tv_date = itemView.findViewById(R.id.tv_list_item_res_check_date);
            btn_bottom = itemView.findViewById(R.id.tv_list_item_res_check_bottom);
        }
    } //ResCheckViewHolder

    ReservationCheckActivity ac;
    Context con = null;
    int layout = 0;
    private LayoutInflater inflater = null;
    ArrayList<ReservationBean> rbList = null;
    int checkType = 999;
    String btn_text = "";

    public ResCheckAdapter(ReservationCheckActivity ac, int layout, ArrayList<ReservationBean> rbList, int checkType) {
        this.ac = ac;
        this.con = ac.getApplication();
        this.layout = layout;
        this.rbList = rbList;
        this.checkType = checkType;
        inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        switch (checkType){
            case 0:
                btn_text = "취소하기";
                break;
            case 1:
                btn_text = "리뷰쓰기";
                break;
        }
    }

    @Override
    public ResCheckViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_reservation_check, parent, false);
        ResCheckAdapter.ResCheckViewHolder viewHolder = new ResCheckAdapter.ResCheckViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ResCheckViewHolder holder, int position) {
        ReservationBean rb = rbList.get(position);

        Glide.with(con)
                .load(ShareVar.shopImgPath+rb.getShopImage())
                .placeholder(R.drawable.jpeg_default_profile_photo)
                .error(R.drawable.jpeg_default_profile_photo)
                .fallback(R.drawable.jpeg_default_profile_photo)
                .into(holder.shop_img);
        holder.shop_name.setText(rb.getShopName());
        holder.shop_addr.setText(rb.getShopAddress());
        Glide.with(con)
                .load(ShareVar.userImgPath+rb.getDesignerImage())
                .placeholder(R.drawable.jpeg_default_profile_photo)
                .error(R.drawable.jpeg_default_profile_photo)
                .fallback(R.drawable.jpeg_default_profile_photo)
                .into(holder.designer_img);
        holder.designer_name.setText(rb.getDesignerName());
        holder.style_title.setText(rb.getStylingTitle());
        holder.btn_bottom.setText(btn_text);
        holder.tv_date.setText(datePaser(rb));

        if(btn_text.equals("")){
            holder.btn_bottom.setVisibility(View.INVISIBLE);
        } else {
            holder.btn_bottom.setVisibility(View.VISIBLE);
        }

        holder.btn_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (checkType){
                    case 0:
                        resCancelDialog(rb.getNo());
                        break;
                    case 1:
                        /////////////
                        // 리뷰쓰기  //
                        ////////////
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return rbList.size();
    }

    public String datePaser(ReservationBean rb){
        String date = rb.getReservationDate();
        String time = rb.getReservationTime() + ":00";
        String[] pDate = date.split("-");
        return pDate[0] + "년 " + pDate[1] + "월 " + pDate[2] + "일\n" + time;
    }

    public void resCancelDialog(int resNo){
        AlertDialog.Builder dialog = new AlertDialog.Builder(ac);
        dialog.setTitle("예약취소");
        dialog.setMessage("예약취소 하시겠습니까?");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resCancel(resNo);
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void resCancel(int resNo){
        RetrofitService retrofitService = RetrofitCall.reservationService();
        retrofitService.getCancelResult(resNo).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String result = response.body();
                Log.d(TAG, "onResponse:성공");
                Log.d(TAG, "결과 reservationList: " + result);
                AlertDialog.Builder dialog = new AlertDialog.Builder(ac);
                dialog.setTitle("예약취소 완료");
                dialog.setMessage("예약취소가 완료되었습니다.");
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ac.getData();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG, "onResponse:실패");
                Log.d(TAG, "결과 : " + t.toString());
            }
        });
    }
}
