package com.example.mogastyle.Activities.MyPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mogastyle.Common.LoginedUserInfo;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.NetworkTasks.MyPage.MyPageUpdatePw;
import com.example.mogastyle.R;

public class MyPageUpdatePwActivity extends AppCompatActivity {

    EditText et_my_page_update_pw1,et_my_page_update_pw2;

    Button btn_my_page_update_pw;

    TextView tv_my_page_pw_check;

    String urlAddr = ShareVar.hostRootAddr ;

    String updateResult;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page_update_pw);

        et_my_page_update_pw1 = findViewById(R.id.et_my_page_update_pw1);
        et_my_page_update_pw2 = findViewById(R.id.et_my_page_update_pw2);

        tv_my_page_pw_check = findViewById(R.id.tv_my_page_pw_check);

        btn_my_page_update_pw = findViewById(R.id.btn_my_page_update_pw);
        btn_my_page_update_pw.setEnabled(false);


        et_my_page_update_pw2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(et_my_page_update_pw1.getText().toString().equals(et_my_page_update_pw2.getText().toString()) ){
                    tv_my_page_pw_check.setText("Correct");
                    tv_my_page_pw_check.setTextColor(Color.parseColor("#00ff00"));
                }else{
                    tv_my_page_pw_check.setText("InCorrect!");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(tv_my_page_pw_check.getText().equals("Correct")){
                    btn_my_page_update_pw.setEnabled(true);
                }
            }
        });

        btn_my_page_update_pw.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        Intent intent;
        @Override
        public void onClick(View v) {
            String pw1 = et_my_page_update_pw1.getText().toString();
            String userNo = Integer.toString(LoginedUserInfo.user.getNo());

//            Toast.makeText(MyPageUpdatePwActivity.this, pw1 + userNo, Toast.LENGTH_SHORT).show();

            MyPageUpdatePw myPageUpdatePw = new MyPageUpdatePw(MyPageUpdatePwActivity.this ,urlAddr+"MyPage/MyPageUpdatePw.jsp" , pw1 , userNo );

            Object object = null;
            try {
                object = myPageUpdatePw.execute().get();
            }catch(Exception e){
                e.printStackTrace();
            }

            updateResult = (String) object;

            if(updateResult.equals("1")){
                Toast.makeText(MyPageUpdatePwActivity.this, "업데이트 완료! 다음 로그인시 자동로그인이 풀립니다!", Toast.LENGTH_SHORT).show();
                intent = new Intent(MyPageUpdatePwActivity.this , MyPageMainActivity.class);
                // 업데이트 완료시 sharedPreference 지움


                //
                startActivity(intent);

            }else{
                Toast.makeText(MyPageUpdatePwActivity.this, "업데이트 오류!", Toast.LENGTH_SHORT).show();
            }



        }
    };
}