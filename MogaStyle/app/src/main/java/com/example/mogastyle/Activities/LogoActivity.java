/*
    <<<<<<<<작성 2021.6.15 윤재필
    5초 대기후 LoginActivity로 이동
    >>>>>>>>
 */
package com.example.mogastyle.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.mogastyle.Bean.User;
import com.example.mogastyle.Common.LoginedUserInfo;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.NetworkTasks.Login.LoginInAppGetUserInfo;
import com.example.mogastyle.R;

import java.util.ArrayList;

public class LogoActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    ArrayList<User> userInfo;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
//        getHashKey();

        sharedPreferences = getSharedPreferences("autoLogined" , Activity.MODE_PRIVATE);
        String userNo = sharedPreferences.getString("userNo" , null);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(userNo == null) {

                    intent = new Intent(LogoActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                }else if(userNo != null){

                    Toast.makeText(LogoActivity.this, "로그인 성공 !", Toast.LENGTH_SHORT).show();
                    String urlAddr = ShareVar.hostRootAddr;
                    //로그인 성공시에 들고오는 유저의 정보들 작업
                    try {
                        LoginInAppGetUserInfo loginInAppGetUserInfo = new LoginInAppGetUserInfo(LogoActivity.this, urlAddr + "Home/userLoginGetInfo.jsp", userNo);
                        Object userObject = loginInAppGetUserInfo.execute().get();
                        userInfo = (ArrayList<User>) userObject;

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    // 로그인 시  Bean 저장
                    LoginedUserInfo.user.setNo(userInfo.get(0).getNo());
                    LoginedUserInfo.user.setName(userInfo.get(0).getName());
                    LoginedUserInfo.user.setId(userInfo.get(0).getId());
                    LoginedUserInfo.user.setJoinType(userInfo.get(0).getJoinType());
                    LoginedUserInfo.user.setUserCheck(userInfo.get(0).getUserCheck());
                    LoginedUserInfo.user.setUserPhone(userInfo.get(0).getUserPhone());
                    LoginedUserInfo.user.setUserImage(userInfo.get(0).getUserImage());

                    intent = new Intent(LogoActivity.this , MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 2000);

    }

//    private void getHashKey(){
//        PackageInfo packageInfo = null;
//        try {
//            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        if (packageInfo == null)
//            Log.e("KeyHash", "KeyHash:null");
//
//        for (Signature signature : packageInfo.signatures) {
//            try {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            } catch (NoSuchAlgorithmException e) {
//                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
//            }
//        }
//    }
}
