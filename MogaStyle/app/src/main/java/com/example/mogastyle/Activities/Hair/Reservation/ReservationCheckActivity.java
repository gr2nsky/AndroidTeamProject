package com.example.mogastyle.Activities.Hair.Reservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.mogastyle.NetworkTasks.Hair.Reservation.RetrofitCall;
import com.example.mogastyle.NetworkTasks.Hair.Reservation.RetrofitService;
import com.example.mogastyle.Adapters.Hair.Reservation.ResCheckAdapter;
import com.example.mogastyle.Bean.ReservationBean;
import com.example.mogastyle.Bean.ReservationList;
import com.example.mogastyle.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationCheckActivity extends AppCompatActivity {
    String TAG = "############################";

    // <<<<<<<<<<<<<layout item>>>>>>>>>>>>>>>>
    LinearLayout lin_res_check_pre_use;
    View view_res_check_pre_use;
    LinearLayout lin_res_check_used;
    View view_res_check_used;
    LinearLayout lin_res_check_canceled;
    View view_res_check_canceled;
    RecyclerView list_res_check;
    RecyclerView.LayoutManager lm;
    ResCheckAdapter adapter;

    //<<<<<<<<<<<<<<<data>>>>>>>>>>>>>>>>>>>
    //////////////////////////////////////////
    ////           수정해야 함                 //
    /////////////////////////////////////////
    //int userNo = LoginedUserInfo.user.getNo();
    int userNo = 1;
    ArrayList<ReservationBean> rbList;
    ArrayList<ReservationBean> preUseRbList;
    ArrayList<ReservationBean> usedRbList;
    ArrayList<ReservationBean> cancelRbList;

    //<<<<<<<<<<<<<<token>>>>>>>>>>>>>>>>>>>>
    int checkType = 0;

    //<<<<<<<<<<<<<<Retrofit2>>>>>>>>>>>>>>>>
    private RetrofitService retrofitService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_check);

        // <<<<<<<<<<<<<layout item>>>>>>>>>>>>>>>>
        lin_res_check_pre_use = findViewById(R.id.lin_res_check_pre_use);
        view_res_check_pre_use = findViewById(R.id.view_res_check_pre_use);
        lin_res_check_used = findViewById(R.id.lin_res_check_used);
        view_res_check_used = findViewById(R.id.view_res_check_used);
        lin_res_check_canceled = findViewById(R.id.lin_res_check_canceled);
        view_res_check_canceled = findViewById(R.id.view_res_check_canceled);
        list_res_check = findViewById(R.id.list_res_check);

        lin_res_check_pre_use.setOnClickListener(onClickListener);
        lin_res_check_used.setOnClickListener(onClickListener);
        lin_res_check_canceled.setOnClickListener(onClickListener);

        rbList = new ArrayList<>();
        preUseRbList = new ArrayList<>();
        usedRbList = new ArrayList<>();
        cancelRbList = new ArrayList<>();

        retrofitService = RetrofitCall.reservationService();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    public void getData(){
        rbList = new ArrayList<>();
        retrofitService.getList(userNo).enqueue(new Callback<ReservationList>() {
            @Override
            public void onResponse(Call<ReservationList> call, Response<ReservationList> response) {
                ReservationList result = response.body();
                rbList = result.getReservationList();

                Log.d(TAG, "onResponse:성공");
                Log.d(TAG, "결과 body: " + response.body());
                Log.d(TAG, "결과 reservationList: " + result.print());

                classifyList();
            }

            @Override
            public void onFailure(Call<ReservationList> call, Throwable t) {
                Log.d(TAG, "onResponse:실패");
                Log.d(TAG, "결과 : " + t.toString());
            }
        });
    }

    public void classifyList(){
        preUseRbList = new ArrayList<>();
        usedRbList = new ArrayList<>();
        cancelRbList = new ArrayList<>();
        Log.d(TAG, "classifyList run");

        for(ReservationBean rb : rbList){
            if(!rb.getCancelDate().equals("null")){
                Log.d(TAG, "classifyList : is cancelRbList");
                cancelRbList.add(rb);
                continue;
            }
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String today = sdf.format(date);
            String resDate = rb.getReservationDate() + "-" + rb.getReservationTime() + "-00-00";

            long iToday = Long.parseLong(today.replace("-", ""));
            long iResDate = Long.parseLong(resDate.replace("-", ""));

            long calDate = iToday - iResDate;
            Log.d(TAG, "calDate = iToday - iResDate : " + calDate + " = " + iToday + " - " + iResDate);

            if(calDate > 0 ){
                Log.d(TAG, "classifyList : is usedRbList");
                usedRbList.add(rb);
            }else {
                Log.d(TAG, "classifyList : is preUseRbList");
                preUseRbList.add(rb);
            }
        }
        setList();
    }

    public void setList(){
        Log.d(TAG, "checktype"+checkType);
        ArrayList<ReservationBean> list = null;
        switch (checkType){
            case 0:
                list = preUseRbList;
                break;
            case 1:
                list = usedRbList;
                break;
            case 2:
                list = cancelRbList;
                break;
        }
        ReservationList zz = new ReservationList();
        zz.setReservationList(list);
        Log.d(TAG, "현재 보여줄 자료 \n" + zz.print());
        lm = new LinearLayoutManager(ReservationCheckActivity.this);
        list_res_check.setLayoutManager(lm);
        adapter = new ResCheckAdapter(this, R.layout.list_item_reservation_check, list, checkType);
        list_res_check.setAdapter(adapter);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.lin_res_check_pre_use:
                    checkType = 0;
                    view_res_check_pre_use.setBackgroundColor(getResources().getColor(R.color.menu_nomal));
                    view_res_check_used.setBackgroundColor(getResources().getColor(R.color.btn_super_negative));
                    view_res_check_canceled.setBackgroundColor(getResources().getColor(R.color.btn_super_negative));
                    break;
                case R.id.lin_res_check_used:
                    checkType = 1;
                    view_res_check_pre_use.setBackgroundColor(getResources().getColor(R.color.btn_super_negative));
                    view_res_check_used.setBackgroundColor(getResources().getColor(R.color.menu_nomal));
                    view_res_check_canceled.setBackgroundColor(getResources().getColor(R.color.btn_super_negative));
                    break;
                case R.id.lin_res_check_canceled:
                    checkType = 2;
                    view_res_check_pre_use.setBackgroundColor(getResources().getColor(R.color.btn_super_negative));
                    view_res_check_used.setBackgroundColor(getResources().getColor(R.color.btn_super_negative));
                    view_res_check_canceled.setBackgroundColor(getResources().getColor(R.color.menu_nomal));
                    break;
            }
            setList();
        }
    };
}