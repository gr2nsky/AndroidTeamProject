package com.example.mogastyle.NetworkTasks.Hair.Reservation;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mogastyle.Bean.ReservationBean;
import com.example.mogastyle.Bean.ReservationList;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.http.Query;

public class InsertReservation extends AsyncTask<Call, Void, String> {

    String TAG = "GetDesginerResData#############";

    String reservationDate;
    int reservationTime;
    int totalPrice;
    int designer_no;
    int shop_no;
    int user_no;
    int styling_no;

    public InsertReservation(ReservationBean bean){
        this. reservationDate = bean.getReservationDate();
        this.reservationTime = bean.getReservationTime();
        this.totalPrice = bean.getTotalPrice();
        this.designer_no = bean.getDesigner_no();
        this.shop_no = bean.getShop_no();
        this.user_no = bean.getUser_no();
        this.styling_no = bean.getStyling_no();
    }

    public InsertReservation(String reservationDate, int reservationTime, int totalPrice, int designer_no, int shop_no, int user_no, int styling_no) {
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
        this.totalPrice = totalPrice;
        this.designer_no = designer_no;
        this.shop_no = shop_no;
        this.user_no = user_no;
        this.styling_no = styling_no;
    }

    @Override
    protected String doInBackground(Call... params) {
        RetrofitService retrofitService = RetrofitCall.reservationService();
        Call<String> call = retrofitService.insertRes(reservationDate, reservationTime, totalPrice,
                designer_no, shop_no, user_no, styling_no);
        try {
            String result = call.execute().body();
            Log.v(TAG, "result : " + result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {

    }

}
