package com.example.mogastyle.NetworkTasks.Hair.Reservation;

import com.example.mogastyle.Bean.ReservationList;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("reservationListLoad.jsp?")
    Call<ReservationList> getList(@Query("userNo") int userNo);

    @GET("designerResedDateRead.jsp?")
    Call<ReservationList> getListForResDateData(@Query("dno") int dno);

    @GET("reviewListByDesigner.jsp?")
    Call<ReservationList> getListForReviewByDesigner(@Query("dno") int dno);

    @GET("reviewListByShop.jsp")
    Call<ReservationList> getListForReviewByShop(@Query("sno") int sno);

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

    @Multipart
    @POST("reviewUpdate.jsp")
    Call<String> insertReview(@PartMap Map<String, RequestBody> params,
                              @Part MultipartBody.Part files);
    @Multipart
    @POST("reviewDelete.jsp")
    Call<String> deleteReview(@Query("rno") int rno);



}
