package com.example.mogastyle.NetworkTasks.Hair.Reservation;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mogastyle.Bean.ReservationBean;
import com.example.mogastyle.Bean.ReservationList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class GetDesginerResData extends AsyncTask<Call, Void, ReservationList> {

    String TAG = "GetDesginerResData#############";
    int dno;

    public GetDesginerResData(int dno) {
        this.dno = dno;
    }

    @Override
    protected ReservationList doInBackground(Call... params) {
        RetrofitService retrofitService = RetrofitCall.reservationService();
        Call<ReservationList> call = retrofitService.getListForResDateData(dno);
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
