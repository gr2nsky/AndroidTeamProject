package com.example.mogastyle.Activities.Hair.Reservation.Retrofit;

import com.example.mogastyle.Bean.ReservationList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("reservationListLoad.jsp?")
    Call<ReservationList> getList(@Query("userNo") int userNo);

}
