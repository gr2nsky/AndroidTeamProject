package com.example.mogastyle.NetworkTasks.Hair.Reservation;

import com.example.mogastyle.Bean.ReservationList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("reservationListLoad.jsp?")
    Call<ReservationList> getList(@Query("userNo") int userNo);

    @GET("designerResedDateRead.jsp?")
    Call<ReservationList> getListForResDateData(@Query("dno") int dno);

    @GET("reservationListCancel.jsp?")
    Call<String> getCancelResult(@Query("resNo") int resNo);

}
