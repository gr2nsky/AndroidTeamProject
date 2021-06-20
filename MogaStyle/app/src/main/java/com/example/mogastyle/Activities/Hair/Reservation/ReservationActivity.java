package com.example.mogastyle.Activities.Hair.Reservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mogastyle.Adapters.Hair.Reservation.ResDateSelectorAdapter;
import com.example.mogastyle.Adapters.Hair.Reservation.ResTimeSelectorAdapter;
import com.example.mogastyle.Bean.ResDateData;
import com.example.mogastyle.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ReservationActivity extends AppCompatActivity {

    String TAG = "ReservationActivity";

    //-------------------Layout items
    ////메뉴 정보
    TextView tv_shop_name;
    TextView tv_menu_name;
    ////날짜 선택
    RecyclerView list_date_selector;
    RecyclerView.LayoutManager dateLayoutManager;
    ////시간 선택
    ImageView iv_designer_photo;
    TextView tv_designer_name;
    TextView tv_designer_intro;
    RecyclerView list_time_selector;
    RecyclerView.LayoutManager timeLayoutManager;
    ////최하단
    TextView tv_total_price;
    Button btn_go_payment;

    //res datas
    int shopNo;
    int menuNo;
    int designNo;

    //adapters
    ResDateSelectorAdapter dateSelectorAdapter;
    ResTimeSelectorAdapter timeSelectorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        //layout item binding
        tv_shop_name = findViewById(R.id.tv_reservation_shop_name);
        tv_menu_name = findViewById(R.id.tv_reservation_menu_name);
        list_date_selector = findViewById(R.id.recycler_reservation_date_selector);
        iv_designer_photo = findViewById(R.id.iv_reservation_designer_photo);
        tv_designer_name = findViewById(R.id.tv_reservation_designer_name);
        tv_designer_intro = findViewById(R.id.tv_reservation_designer_intro);
        list_time_selector = findViewById(R.id.recycler_reservation_time_selector);
        tv_total_price = findViewById(R.id.tv_reservation_total_price);
        btn_go_payment = findViewById(R.id.btn_reservation_go_payment);

        btn_go_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                startActivity(intent);
            }
        });

        //list_date_selector
        ArrayList<ResDateData> dds = dateDataMaker();
        dateLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        list_date_selector.setLayoutManager(dateLayoutManager);
        dateSelectorAdapter = new ResDateSelectorAdapter(ReservationActivity.this, R.layout.list_item_reservation_date_selector, dds);
        list_date_selector.setAdapter(dateSelectorAdapter);

        //list_time_selector
        ArrayList<Integer> resableTime = resableTimeMaker();
        timeLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        list_time_selector.setLayoutManager(timeLayoutManager);
        timeSelectorAdapter = new ResTimeSelectorAdapter(ReservationActivity.this, R.layout.list_item_reservation_time_selector, resableTime);
        list_time_selector.setAdapter(timeSelectorAdapter);

    }


    //날자 배열 생성
    public ArrayList<ResDateData> dateDataMaker(){
        int datIneterval = 30;
        ArrayList<ResDateData> dateData = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        Date nowDateInfo = cal.getTime();
        SimpleDateFormat nowYearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat nowMonthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat nowDateFormat = new SimpleDateFormat("dd");
        int nowYear = Integer.parseInt(nowYearFormat.format(nowDateInfo));
        int nowMonth = Integer.parseInt(nowMonthFormat.format(nowDateInfo));
        int nowDate = Integer.parseInt(nowDateFormat.format(nowDateInfo));
        int dayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int resableNextMonthDate = datIneterval - (dayOfMonth - nowDate);

        for (int i = nowDate; i <= dayOfMonth; i++){
            ResDateData dd = new ResDateData(nowYear, nowMonth, i, dayMaker(dayOfWeek));
            dateData.add(dd);
            Log.v(TAG, "dateDataMaker : " + dd.printAll());
            dayOfWeek += 1;
            if(dayOfWeek == 8){
                dayOfWeek = 1;
            }
        }
        nowMonth += 1;
        for( int i = 1; i <= resableNextMonthDate; i++){
            ResDateData dd = new ResDateData(nowYear, nowMonth, i, dayMaker(dayOfWeek));
            dateData.add(dd);
            Log.v(TAG, "dateDataMaker : " + dd.printAll());
            dayOfWeek += 1;
            if(dayOfWeek == 8){
                dayOfWeek = 1;
            }
        }
        return dateData;
    }

    public String dayMaker(int i){
        String result = "";
        switch (i){
            case 1:
                return "일";
            case 2:
                return "월";
            case 3:
                return "화";
            case 4:
                return "수";
            case 5:
                return "목";
            case 6:
                return "금";
            case 7:
                return "토";
        }
        return Integer.toString(i);
    }

    //예약가능시간 배열 생성
    public ArrayList<Integer> resableTimeMaker(){
        ArrayList<Integer> resableTimeList = new ArrayList<>();
        //이후 예약된 시간 배열을 불러와서 예외처리
        for(int i = 10; i <= 20; i++){
            if(i == 12 || i == 13) continue;
            Log.v(TAG, "resableTime : " + i);
            resableTimeList.add(i);
        }
        return resableTimeList;
    }
}