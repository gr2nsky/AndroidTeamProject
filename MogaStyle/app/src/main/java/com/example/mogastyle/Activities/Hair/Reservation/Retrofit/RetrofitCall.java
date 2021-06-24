package com.example.mogastyle.Activities.Hair.Reservation.Retrofit;

import com.example.mogastyle.Bean.ReservationList;
import com.example.mogastyle.Common.ShareVar;

public class RetrofitCall {

    public static final String BASE_URL = ShareVar.hostRootAddr;
    public static final String RES_URL = BASE_URL + "Hair/Reservation/";

    public static RetrofitService getReservationList() {

        return RetrofitClient.getClient(RES_URL).create(RetrofitService.class);
    }

}
