package com.example.mogastyle.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mogastyle.Bean.User;
import com.example.mogastyle.Common.LoginedUserInfo;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.NetworkTasks.Login.LoginInApp;
import com.example.mogastyle.NetworkTasks.Login.LoginInAppGetUserInfo;
import com.example.mogastyle.NetworkTasks.Login.SignUpInApp;
import com.example.mogastyle.R;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class LoginBasicActivity extends AppCompatActivity {

    TextView tv_login_sign_up ,tv_login_find_id_pw;
    Intent intent;

    EditText et_login_write_id, et_login_write_pw;

    Button btn_login_login;

    String urlAddr = ShareVar.hostRootAddr ;

    ArrayList<User> userInfo;

    CheckBox chk_login_auto;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_basic);

        tv_login_find_id_pw = findViewById(R.id.tv_login_find_id_pw);
        tv_login_sign_up = findViewById(R.id.tv_login_sign_up);

        tv_login_sign_up.setOnClickListener(onClickListener);
        tv_login_find_id_pw.setOnClickListener(onClickListener);

        et_login_write_id = findViewById(R.id.et_login_write_id);
        et_login_write_pw = findViewById(R.id.et_login_write_pw);

        btn_login_login = findViewById(R.id.btn_login_login);
        btn_login_login.setOnClickListener(onClickListener);

        chk_login_auto = findViewById(R.id.chk_login_auto);


    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_login_sign_up:
                    intent = new Intent(LoginBasicActivity.this , SignUpActivity.class);
                    startActivity(intent);
                    break;

                case R.id.tv_login_find_id_pw:
                    intent = new Intent(LoginBasicActivity.this , FindIdPwActivity.class);
                    startActivity(intent);
                    break;


                case R.id.btn_login_login:
                    String loginUserId = et_login_write_id.getText().toString();
                    String loginUserPw = et_login_write_pw.getText().toString();

                    LoginInApp loginInApp = new LoginInApp(LoginBasicActivity.this , urlAddr + "Home/userLoginInApp.jsp" , loginUserId , loginUserPw);

                    Object object = null;

                    //Result == userNo
                    String result = "0";
                    try{
                        object = loginInApp.execute().get();
                    }catch (Exception e){
                        e.printStackTrace();

                    }

                    result = (String) object;

                    Log.d("result" , result);

                    if (result.equals("0")){
                        // ????????? ??????~~
                        Toast.makeText(LoginBasicActivity.this, "????????? ??????", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(LoginBasicActivity.this, "????????? ?????? !", Toast.LENGTH_SHORT).show();

                        //????????? ???????????? ???????????? ????????? ????????? ??????
                        try {
                            LoginInAppGetUserInfo loginInAppGetUserInfo = new LoginInAppGetUserInfo(LoginBasicActivity.this, urlAddr + "Home/userLoginGetInfo.jsp", result);
                            Object userObject = loginInAppGetUserInfo.execute().get();
                            userInfo = (ArrayList<User>) userObject;

                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        // ????????? ???  Bean ??????
                        LoginedUserInfo.user.setNo(userInfo.get(0).getNo());
                        LoginedUserInfo.user.setName(userInfo.get(0).getName());
                        LoginedUserInfo.user.setId(userInfo.get(0).getId());
                        LoginedUserInfo.user.setJoinType(userInfo.get(0).getJoinType());
                        LoginedUserInfo.user.setUserCheck(userInfo.get(0).getUserCheck());
                        LoginedUserInfo.user.setUserPhone(userInfo.get(0).getUserPhone());
                        LoginedUserInfo.user.setUserImage(userInfo.get(0).getUserImage());
                        // ????????? ???  Bean ??????--

                        //????????????????????? .. check ??????

                        if(chk_login_auto.isChecked()) {
                            sharedPreferences = getSharedPreferences("autoLogined", Activity.MODE_PRIVATE);
                            sharedPreferencesEdit = sharedPreferences.edit();
                            sharedPreferencesEdit.putString("userNo", Integer.toString(userInfo.get(0).getNo()));
                            sharedPreferencesEdit.commit();
                        }else{
                            Intent intent = new Intent(LoginBasicActivity.this , MainActivity.class);
                            startActivity(intent);
                        }


                        //

                        Log.d("userName" ,userInfo.get(0).getName());
                        //????????? ????????? ????????? MainActivity ??? ?????????
                        Intent intent = new Intent(LoginBasicActivity.this , MainActivity.class);
                        intent.putExtra("userNo" , result);
                        intent.putExtra("userName" , userInfo.get(0).getName());
                        intent.putExtra("userId" , userInfo.get(0).getId());
                        startActivity(intent);
                    }

                    break;

            }
        }
    };

}