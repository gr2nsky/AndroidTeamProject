/*
    <<<<<<<<작성 2021.6.15 윤재필
    -
    >>>>>>>>
 */
package com.example.mogastyle.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mogastyle.Activities.Consult.ConsultMainActivity;
import com.example.mogastyle.Activities.Diary.DiaryMainActivity;
import com.example.mogastyle.Activities.Hair.HairMainActivity;
import com.example.mogastyle.Activities.MyPage.MyPageMainActivity;
import com.example.mogastyle.Adapters.Home.HomePageAdapter;
import com.example.mogastyle.Common.LoginedUserInfo;
import com.example.mogastyle.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import me.relex.circleindicator.CircleIndicator;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FragmentPagerAdapter fragmentPagerAdapter;

    private String strUserKakaoName;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setFinishOnTouchOutside(false);

        // main 에서 3개의 프레그먼트 이동
        ViewPager viewPager = findViewById(R.id.viewPager_main_loginSuccess);
        fragmentPagerAdapter = new HomePageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.setCurrentItem(1);

        CircleIndicator indicator = findViewById(R.id.main_indicator);
        indicator.setViewPager(viewPager);
        // -- 프레그 먼트 이동 끝


        //getPhoneDialog 두둥 등장
        // null -> 4자리 나오면 ...
        if(LoginedUserInfo.user.getUserPhone().length() == 4) {
            openPhoneDialog();
        }
        //--getPhoneDialog 두둥 등장 end

        //KAKAO LOGIN GET INFO --

        intent = getIntent();
        strUserKakaoName = intent.getStringExtra("userKakaoName");

        //KAKAO LOGIN GET INFO END --



        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.page_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.page_home:
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
                        startActivity(new Intent(getApplicationContext(),MyPageMainActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;

                }


                return false;
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "앱을 종료합니다.", Toast.LENGTH_SHORT).show();
        ActivityCompat.finishAffinity(this);
    }

    public void openPhoneDialog(){
        MainGetPhoneDialog mainGetPhoneDialog = new MainGetPhoneDialog();
        mainGetPhoneDialog.show(getSupportFragmentManager(),"example dialog");
        mainGetPhoneDialog.setCancelable(false);

    }

}
