package com.example.mogastyle.NetworkTasks.Hair.Reservation;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.mogastyle.Bean.ReservationList;

import java.io.IOException;

import retrofit2.Call;

public class getReviewList extends AsyncTask<Call, Void, ReservationList> {

    String TAG = "getReviewList#############";
    int shopNo = 0;
    int designerNo = 0;
    //designer : 1, shop : 2
    int typeToken = 0;

    /***
     * @param no : shopNo or Designer No
     * @param type : "shop" or "designer"
     */
    public getReviewList(int no, String type) {
        if(type.equals("designer")){
            designerNo = no;
            typeToken = 1;
        }else {
            shopNo = no;
            typeToken = 2;
        }
    }

    @Override
    protected ReservationList doInBackground(Call... params) {
        RetrofitService retrofitService = RetrofitCall.reservationService();
        Call<ReservationList> call = null;
        if(typeToken == 1){
            call = retrofitService.getListForReviewByDigner(designerNo);
        } else if (typeToken == 2){
            call = retrofitService.getListForReviewByShop(shopNo);
        } else {

        }
        try {
            ReservationList result = call.execute().body();
            Log.v(TAG, "result : " + result.desPrint());
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ReservationList reservationList) {

    }
}
