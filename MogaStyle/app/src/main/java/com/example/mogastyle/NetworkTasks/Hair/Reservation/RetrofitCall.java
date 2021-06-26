package com.example.mogastyle.NetworkTasks.Hair.Reservation;

import com.example.mogastyle.Common.ShareVar;

public class RetrofitCall {

    public static final String BASE_URL = ShareVar.hostRootAddr;
    public static final String RES_URL = BASE_URL + "Hair/Reservation/";

    public static RetrofitService reservationService() {
        return RetrofitClient.getClient(RES_URL).create(RetrofitService.class);
    }


}
