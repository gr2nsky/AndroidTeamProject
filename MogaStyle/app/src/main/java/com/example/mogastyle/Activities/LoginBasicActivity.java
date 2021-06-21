package com.example.mogastyle.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mogastyle.R;

public class LoginBasicActivity extends AppCompatActivity {

    TextView tv_login_sign_up ,tv_login_find_id_pw;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_basic);

        tv_login_find_id_pw = findViewById(R.id.tv_login_find_id_pw);
        tv_login_sign_up = findViewById(R.id.tv_login_sign_up);

        tv_login_sign_up.setOnClickListener(onClickListener);
        tv_login_find_id_pw.setOnClickListener(onClickListener);
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
            }
        }
    };

}