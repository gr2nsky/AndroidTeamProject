/*
    <<<<<<<<작성 2021.6.15 윤재필
    로그인한 유저의 정보를 저장
    >>>>>>>>
 */
package com.example.mogastyle.Common;

import com.example.mogastyle.Bean.User;

public class LoginedUserInfo {

    //로그인 이후 덮어씌우면 된다
    public static User user = new User();

}
