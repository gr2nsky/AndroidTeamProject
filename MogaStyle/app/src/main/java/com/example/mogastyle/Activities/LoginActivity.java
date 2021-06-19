/*
    <<<<<<<<작성 2021.6.15 윤재필
    LoginActivity
    >>>>>>>>
 */
package com.example.mogastyle.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mogastyle.R;

import java.security.MessageDigest;

public class LoginActivity extends AppCompatActivity {

    Button btn_login_goMain;
    TextView tv_login_another_way_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login_goMain = findViewById(R.id.btn_login_goMain);
        btn_login_goMain.setOnClickListener(onClickListener);
        tv_login_another_way_login = findViewById(R.id.tv_login_another_way_login);
        tv_login_another_way_login.setOnClickListener(onClickListener);

        // 반영후에는 삭제될 메소드
        getAppKeyHash();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch(v.getId()){
                case R.id.btn_login_goMain:
                    intent = new Intent(LoginActivity.this , MainActivity.class);
                    startActivity(intent);
                    break;

                case R.id.tv_login_another_way_login:
                    intent = new Intent(LoginActivity.this , LoginBasicActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    /*
     * TODO
     * 카카오 로그인 시 필요한 해시키를 얻는 메소드.
     *  << 반영 후 에는 삭제될 메소드 >>
     * 각자의 해시키 보고 올려주시면 반영하겠습니다 ~
     *  YJ = "keRXhFxHMBcPgn2fDjT4GdL8aQU="
     */
    private void getAppKeyHash(){
        try{
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for(Signature signature : info.signatures){
                MessageDigest messageDigest;
                messageDigest = MessageDigest.getInstance("SHA");
                messageDigest.update(signature.toByteArray());
                String appKeyHash = new String(Base64.encode(messageDigest.digest() , 0));
                Log.e("Hash key",appKeyHash);
            }


        }catch(Exception e){
            Log.e("name not found",e.toString());
        }

    }

}