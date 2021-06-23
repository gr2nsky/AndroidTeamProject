package com.example.mogastyle.Activities.MyPage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mogastyle.R;

public class MyPageUpdatePwActivity extends AppCompatActivity {

    EditText et_my_page_update_pw1,et_my_page_update_pw2;

    Button btn_my_page_update_pw;

    TextView tv_my_page_pw_check;

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




    }
}