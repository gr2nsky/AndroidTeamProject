package com.example.mogastyle.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mogastyle.Activities.MyPage.ConsultSettingFragment;
import com.example.mogastyle.R;

public class FindIdPwActivity extends AppCompatActivity {

    TextView tv_Find_id ,  tv_Find_pw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_id_pw);

        tv_Find_id = findViewById(R.id.tv_Find_id);
        tv_Find_pw = findViewById(R.id.tv_Find_pw);

        //
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        FindIdFragment findIdFragment = new FindIdFragment();
        transaction.replace(R.id.find_login_info_setting_frame , findIdFragment);
        transaction.commit();
        int myColor = ContextCompat.getColor(getApplicationContext(),R.color.btn_super_positive);
        tv_Find_id.setTextColor(0xAAef484a);
        tv_Find_pw.setTextColor(myColor);
        //

        tv_Find_id.setOnClickListener(onClickListener);
        tv_Find_pw.setOnClickListener(onClickListener);


    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            int myColor = ContextCompat.getColor(getApplicationContext(),R.color.btn_super_positive);
            switch(v.getId()){
                case R.id.tv_Find_id:
                    tv_Find_id.setTextColor(0xAAef484a);
                    tv_Find_pw.setTextColor(myColor);
                    FindIdFragment findIdFragment = new FindIdFragment();
                    transaction.replace(R.id.find_login_info_setting_frame , findIdFragment);
                    transaction.commit();




//                    tv_Find_id.setTextColor(myColor);
//                    tv_Find_pw.setTextColor(0xAAef484a);
//                    FindPwFragment findPwFragment = new FindPwFragment();
//                    transaction.replace(R.id.find_login_info_setting_frame , findPwFragment);
//                    transaction.commit();색

                    break;

                case R.id.tv_Find_pw:

                    Toast.makeText(FindIdPwActivity.this, "ID 를 검 후에 PW 찾기가 가능합니다!!", Toast.LENGTH_SHORT).show();


                    break;
            }
        }
    };

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.find_login_info_setting_frame ,fragment).commit();
    }

}