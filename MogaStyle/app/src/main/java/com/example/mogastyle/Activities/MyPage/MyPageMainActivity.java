package com.example.mogastyle.Activities.MyPage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mogastyle.Activities.Consult.ConsultMainActivity;
import com.example.mogastyle.Activities.Diary.DiaryMainActivity;
import com.example.mogastyle.Activities.Hair.HairMainActivity;
import com.example.mogastyle.Activities.MainActivity;
import com.example.mogastyle.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class MyPageMainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    //Activity VAR
    LinearLayout linear_layout_my_page_info;

    TextView tv_my_page_user_name,tv_my_page_user_id,tv_my_page_consult_setting ,tv_my_page_reservation_setting,tv_my_page_diary_setting;

    ImageView iv_my_page_user_image;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page_main);

        //Binding
        linear_layout_my_page_info = findViewById(R.id.linear_layout_my_page_info);

        iv_my_page_user_image = findViewById(R.id.iv_my_page_user_image);
        tv_my_page_user_name = findViewById(R.id.tv_my_page_user_name);
        tv_my_page_user_id = findViewById(R.id.tv_my_page_user_id);

        tv_my_page_consult_setting = findViewById(R.id.tv_my_page_consult_setting);
        tv_my_page_reservation_setting = findViewById(R.id.tv_my_page_reservation_setting);
        tv_my_page_diary_setting = findViewById(R.id.tv_my_page_diary_setting);

        //

        linear_layout_my_page_info.setOnClickListener(onClickListener);

        //

        tv_my_page_consult_setting.setOnClickListener(onClickListener);
        tv_my_page_reservation_setting.setOnClickListener(onClickListener);
        tv_my_page_diary_setting.setOnClickListener(onClickListener);



        // 초기 세팅 프레그먼트 구성
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ConsultSettingFragment consultSettingFragment = new ConsultSettingFragment();
        transaction.replace(R.id.my_page_setting_frame , consultSettingFragment);
        transaction.commit();
        int myColor = ContextCompat.getColor(getApplicationContext(),R.color.btn_super_positive);
        tv_my_page_consult_setting.setTextColor(0xAAef484a);
        tv_my_page_reservation_setting.setTextColor(myColor);
        tv_my_page_diary_setting.setTextColor(myColor);
        //

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.page_user);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.page_home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.page_consult:
                        startActivity(new Intent(getApplicationContext(),ConsultMainActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.page_reservation:
                        startActivity(new Intent(getApplicationContext(),HairMainActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.page_diary:
                        startActivity(new Intent(getApplicationContext(),DiaryMainActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.page_user:
                        return true;

                }

                return false;
            }
        });

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            int myColor = ContextCompat.getColor(getApplicationContext(),R.color.btn_super_positive);
            switch (v.getId()){
                case R.id.linear_layout_my_page_info:

                    Intent intent = new Intent(MyPageMainActivity.this , MyPageUpdateActivity.class);
                    startActivity(intent);

                    break;

                case R.id.tv_my_page_consult_setting:
                    tv_my_page_consult_setting.setTextColor(0xAAef484a);
                    tv_my_page_reservation_setting.setTextColor(myColor);
                    tv_my_page_diary_setting.setTextColor(myColor);
                    ConsultSettingFragment consultSettingFragment = new ConsultSettingFragment();
                    transaction.replace(R.id.my_page_setting_frame , consultSettingFragment);
                    transaction.commit();
                    break;
                case R.id.tv_my_page_reservation_setting:
                    tv_my_page_consult_setting.setTextColor(myColor);
                    tv_my_page_reservation_setting.setTextColor(0xAAef484a);
                    tv_my_page_diary_setting.setTextColor(myColor);
                    ReservationSettingFragment reservationSettingFragment = new ReservationSettingFragment();
                    transaction.replace(R.id.my_page_setting_frame , reservationSettingFragment);
                    transaction.commit();
                    break;

                case R.id.tv_my_page_diary_setting:
                    tv_my_page_consult_setting.setTextColor(myColor);
                    tv_my_page_reservation_setting.setTextColor(myColor);
                    tv_my_page_diary_setting.setTextColor(0xAAef484a);
                    DiarySettingFragment diarySettingFragment = new DiarySettingFragment();
                    transaction.replace(R.id.my_page_setting_frame , diarySettingFragment);
                    transaction.commit();
                    break;
            }
        }
    };



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext() , MainActivity.class));
        overridePendingTransition(0,0);
    }
}