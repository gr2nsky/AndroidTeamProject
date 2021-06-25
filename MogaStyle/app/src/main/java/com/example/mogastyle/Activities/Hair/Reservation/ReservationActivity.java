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

import com.bumptech.glide.Glide;
import com.example.mogastyle.NetworkTasks.Hair.Reservation.GetDesginerResData;
import com.example.mogastyle.NetworkTasks.Hair.Reservation.RetrofitCall;
import com.example.mogastyle.NetworkTasks.Hair.Reservation.RetrofitService;
import com.example.mogastyle.Adapters.Hair.Reservation.RecyclerDecoration;
import com.example.mogastyle.Adapters.Hair.Reservation.ResDateSelectorAdapter;
import com.example.mogastyle.Adapters.Hair.Reservation.ResTimeSelectorAdapter;
import com.example.mogastyle.Bean.PaymentBeanStack;
import com.example.mogastyle.Bean.ResDateData;
import com.example.mogastyle.Bean.ReservationBean;
import com.example.mogastyle.Bean.ReservationList;
import com.example.mogastyle.Bean.TempDesignerBean;
import com.example.mogastyle.Bean.TempShopBean;
import com.example.mogastyle.Bean.TempStyleBean;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationActivity extends AppCompatActivity {

    String TAG = "ReservationActivity";

    //-------------------Layout items
    ////메뉴 정보
    TextView tv_shop_name;
    TextView tv_menu_name;
    TextView tv_menu_price;
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

    //adapters
    ResDateSelectorAdapter dateSelectorAdapter;
    ResTimeSelectorAdapter timeSelectorAdapter;

    ///////////////////////////////////
    //            temp data          //
    ///////////////////////////////////
    ArrayList<ReservationBean> rbList;
    ArrayList<ResDateData> reservedDates;
    ResDateData today;
    TempDesignerBean tempDesignerBean;
    TempStyleBean tempStyleBean;
    TempShopBean tempShopBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        //layout item binding
        tv_shop_name = findViewById(R.id.tv_reservation_shop_name);
        tv_menu_name = findViewById(R.id.tv_reservation_menu_name);
        tv_menu_price = findViewById(R.id.tv_reservation_shop_price);
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
                PaymentBeanStack.stack.setResDateData(dateSelectorAdapter.getSelectedData());
                PaymentBeanStack.stack.setResTime(timeSelectorAdapter.getSelectedTime());
                startActivity(intent);
            }
        });

        //////////////////////////////////
        //      temp data maker         //
        /////////////////////////////////
        tempDataMaker();

        //list_date_selector
        ArrayList<ResDateData> dds = dateDataMaker();
        dateLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        list_date_selector.setLayoutManager(dateLayoutManager);
        dateSelectorAdapter = new ResDateSelectorAdapter(this, R.layout.list_item_reservation_date_selector, dds, tempShopBean.getHolidays());
        list_date_selector.setAdapter(dateSelectorAdapter);

        //최초 1회성으로 DataSelector가 오늘로 기본으로 선택되게 하였으므로 오늘 데이터를 넘김.
        ///////////////////////////////////////////////////
        //      오늘이 휴무라면 어떻게 해야 하나 ?                //
        ///////////////////////////////////////////////////
        //휴무일이라면 선택이 안되게 하는게 아니라 tiemSelector의 값이 "정기휴무" 하나로 되도록 세팅해야 함

        //list_time_selector
        ArrayList<Integer> resableTime = resableTimeMaker(today);
        //예약 빈자리가 없을 경우
        if(resableTime.isEmpty()){
            timeSelectorDataChange(777);
        }
        timeLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        list_time_selector.setLayoutManager(timeLayoutManager);
        timeSelectorAdapter = new ResTimeSelectorAdapter(ReservationActivity.this, R.layout.list_item_reservation_time_selector, resableTime);
        list_time_selector.setAdapter(timeSelectorAdapter);
        RecyclerDecoration decoration = new RecyclerDecoration(20);
        list_time_selector.addItemDecoration(decoration);

        ////////////////////////////////////////////////////////////
        //               temp data binding to view                //
        ////////////////////////////////////////////////////////////
        tv_shop_name.setText(tempShopBean.getName());
        tv_menu_name.setText(tempStyleBean.getTitle());
        tv_menu_price.setText(Integer.toString(tempStyleBean.getPrice()));
        Glide.with(this)
                .load(ShareVar.userImgPath+tempDesignerBean.getImg())
                .placeholder(R.drawable.jpeg_default_profile_photo)
                .error(R.drawable.jpeg_default_profile_photo)
                .fallback(R.drawable.jpeg_default_profile_photo)
                .into(iv_designer_photo);
        tv_designer_name.setText(tempDesignerBean.getName());
        String[] getCertificationSplit = tempDesignerBean.getCertificationDate().split("-");
        int gerCertificationYear = Integer.parseInt(getCertificationSplit[0]);
        tv_designer_intro.setText("경력 " + (today.getYear() - gerCertificationYear + 1) +"년");
        tv_total_price.setText(Integer.toString(tempStyleBean.getPrice()));
    }//onCreate

    //dataSeletor에서 아이템을 클릭할떄마다 해당 함수 호출
    public void timeSelectorDataChange(ResDateData rdd){

        //개인 휴무일일 경우
        if(tempDesignerBean.getHolidays().contains(rdd.getDayOfWeek())) {
            timeSelectorDataChange(888);
            return;
        }

        ArrayList<Integer> resableTime = resableTimeMaker(rdd);
        //예약 빈자리가 없을 경우
        if(resableTime.isEmpty()){
            timeSelectorDataChange(777);
            return;
        }

        timeLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        list_time_selector.setLayoutManager(timeLayoutManager);
        timeSelectorAdapter = new ResTimeSelectorAdapter(ReservationActivity.this, R.layout.list_item_reservation_time_selector, resableTime);
        list_time_selector.setAdapter(timeSelectorAdapter);
    }
    //샵의 정기휴무일때
    public void timeSelectorDataChange(int i){
        timeLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        list_time_selector.setLayoutManager(timeLayoutManager);
        timeSelectorAdapter = new ResTimeSelectorAdapter(ReservationActivity.this, R.layout.list_item_reservation_time_selector, i);
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

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("hh");
        int nowHour = Integer.parseInt(sdf.format(date));

        //오늘 날자는 별도로 저장
        today = new ResDateData(nowYear, nowMonth, nowDate, dayOfWeek, nowHour);

        /////////////////////////////////////////////////////////////////
        //                휴무 데이터는 여기서 처리해주어야 함                   //
        /////////////////////////////////////////////////////////////////

        for (int i = nowDate; i <= dayOfMonth; i++){
            ResDateData dd = new ResDateData(nowYear, nowMonth, i, dayOfWeek);
            dateData.add(dd);
            Log.v(TAG, "dateDataMaker : " + dd.printAll());
            dayOfWeek += 1;
            if(dayOfWeek == 8){
                dayOfWeek = 1;
            }
        }
        nowMonth += 1;
        for( int i = 1; i <= resableNextMonthDate; i++){
            ResDateData dd = new ResDateData(nowYear, nowMonth, i, dayOfWeek);
            dateData.add(dd);
            Log.v(TAG, "dateDataMaker : " + dd.printAll());
            dayOfWeek += 1;
            if(dayOfWeek == 8){
                dayOfWeek = 1;
            }
        }
        return dateData;
    }

    //예약가능시간 배열 생성
    public ArrayList<Integer> resableTimeMaker(ResDateData resDateData){

        ArrayList<Integer> resableTimeList = new ArrayList<>();

        //예약가능시간은 10시부터
        int startTime = 10;

        //들어온 데이터가 오늘이라면 현재시간 +1시간부터 예약가능
        if(resDateData.getDate() == today.getDate()){
            if(startTime <=10){
                startTime = 10;
            }
            else if (startTime > 20){
                return resableTimeList;
            } else {
                startTime = today.getNowHour() + 1;
            }
        }

        for(int i = startTime; i <= 20; i++){
            if(i == 12 || i == 13) continue;
            Log.v(TAG, "resableTime : " + i);
            resableTimeList.add(i);
        }
        //////////////////////////////////////////////
        //       이후 예약된 시간 배열을 불러와서 예외처리     //
        //////////////////////////////////////////////
        ArrayList<ResDateData> todayResData = new ArrayList<>();
        for(ResDateData rdd : reservedDates){
            if(rdd.getDate() == resDateData.getDate()){
                 ArrayList<Integer> reservedTime = rdd.getTime();
                 for(Integer i : reservedTime){
                     int index = resableTimeList.indexOf(i);
                     if(index != -1){
                         resableTimeList.remove(index);
                     }
                 }
            }
        }

        return resableTimeList;
    }

    /////////////////////////////////////////////
    //                 temp data maker         //
    /////////////////////////////////////////////
    public void tempDataMaker(){
        reservedDates = new ArrayList<>();
        tempDesignerBean = new TempDesignerBean(2, "최준", "2020-05-26", "tempImg.png", "5");
        loadDesignerResDatas(tempDesignerBean.getNo());
        tempDesignerBean.setResDates(reservedDates);

        ///////////////////////////////////////////////////////////////////
        //                       진짜 bean 가져오도록 이후 수정                //
        //////////////////////////////////////////////////////////////////
        tempStyleBean = new TempStyleBean(1, "봄탄소년단", 50000);
        tempShopBean = new TempShopBean(3, "더존미용실 강남점", "1,7");
        PaymentBeanStack.stack.setDesignerBean(tempDesignerBean);
        PaymentBeanStack.stack.setStyleBean(tempStyleBean);
        PaymentBeanStack.stack.setShopBean(tempShopBean);
    }

    public void loadDesignerResDatas(int dno){
        GetDesginerResData getDesginerResData = new GetDesginerResData(dno);
        try {
            ReservationList sibal = getDesginerResData.execute().get();
//            parserResDateData(rl);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void parserResDateData(ArrayList<ReservationBean> rbList){
        for(ReservationBean rb : rbList){
            ResDateData rdd = new ResDateData(rb.getReservationDate(), rb.getReservationTime(), rb.getLeadTime());
            reservedDates.add(rdd);
            Log.v(TAG, reservedDates.toString());
        }
    }
}