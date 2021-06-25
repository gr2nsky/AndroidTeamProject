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

    @GET("reservationInsert.jsp?")
    Call<String> insertRes(@Query("reservationDate") String reservationDate,
                           @Query("reservationTime") int reservationTime,
                           @Query("totalPrice") int totalPrice,
                           @Query("designer_no") int designer_no,
                           @Query("shop_no") int shop_no,
                           @Query("user_no") int user_no,
                           @Query("styling_no") int styling_no);
}
