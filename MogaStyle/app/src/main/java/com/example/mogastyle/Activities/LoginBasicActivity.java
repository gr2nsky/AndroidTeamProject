package com.example.mogastyle.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.NetworkTasks.Login.LoginInApp;
import com.example.mogastyle.NetworkTasks.Login.SignUpInApp;
import com.example.mogastyle.R;

public class LoginBasicActivity extends AppCompatActivity {

    TextView tv_login_sign_up ,tv_login_find_id_pw;
    Intent intent;

    EditText et_login_write_id, et_login_write_pw;

    Button btn_login_login;

    String urlAddr = ShareVar.hostRootAddr ;
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

                    LoginInApp loginInApp = new LoginInApp(LoginBasicActivity.this , urlAddr + "Home/userLoginInAPP.jsp" , loginUserId , loginUserPw);

                    Object object = null;
                    String result = "0";
                    try{
                        object = loginInApp.execute().get();
                    }catch (Exception e){
                        e.printStackTrace();

                    }

                    result = (String) object;

                    Log.d("result" , result);

                    if (result.equals("1")){
                        // 로그인 성공~~
                        Toast.makeText(LoginBasicActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginBasicActivity.this , MainActivity.class);

                        startActivity(intent);

                    }else if(result.equals("2")) {
                        Toast.makeText(LoginBasicActivity.this, "이미 로그인 되어있는 아이디입니다", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(LoginBasicActivity.this, "회원가입 실패!", Toast.LENGTH_SHORT).show();
                    }
                    break;

            }
        }
    };

}