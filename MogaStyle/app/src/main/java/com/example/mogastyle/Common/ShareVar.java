/*
    <<<<<<<<작성 2021.6.15 윤재필
    공통적으로 사용하는 변수 저장
    hostIP만 수정해 사용하면 된다.
    >>>>>>>>
 */

package com.example.mogastyle.Common;

public class ShareVar {

    public static String hostIP = "192.168.2.4";
    public static String hostRootAddr = "http://" + hostIP + ":8080/MogaStyle/";
    public static String userImgPath = hostRootAddr + "img/user/";

}
