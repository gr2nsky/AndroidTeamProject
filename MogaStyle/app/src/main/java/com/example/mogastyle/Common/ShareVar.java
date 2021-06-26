/*
    <<<<<<<<작성 2021.6.15 윤재필
    공통적으로 사용하는 변수 저장
    hostIP만 수정해 사용하면 된다.
    >>>>>>>>
 */

package com.example.mogastyle.Common;

import android.app.Activity;

import java.util.ArrayList;

public class ShareVar {





    public static String hostIP = "";

    public final static String hostRootAddr = "http://" + hostIP + ":8080/MogaStyle/";
    public final static String userImgPath = hostRootAddr + "img/user/";
    public final static String shopImgPath = hostRootAddr + "img/shop/";
    public final static String styleImgPath = hostRootAddr + "img/style/";
    public final static String reviewImgPath = hostRootAddr + "img/review/";
    public final static String diaryImgPath = hostRootAddr + "img/diary/";

    ////////////////////////////////////////////////////////////////////////
    //                        home키에 사용될 친구입니다.                       //
    ///////////////////////////////////////////////////////////////////////
    public static ArrayList<Activity> stackedActivities = new ArrayList<>();

    public static void goHome(){
        if (stackedActivities.isEmpty())
            return;
        for(Activity a : stackedActivities){
            a.finish();
        }
    }


}
 
