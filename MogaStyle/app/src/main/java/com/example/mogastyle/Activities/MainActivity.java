/*
    <<<<<<<<작성 2021.6.15 윤재필
    -
    >>>>>>>>
 */
package com.example.mogastyle.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;


import com.example.mogastyle.DiaryFragment;
import com.example.mogastyle.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment;
    ConsultFragment consultFragment;
    HairShopFragment hairShopFragment;
    DiaryFragment diaryFragment;
    MyPageFragment myPageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = new HomeFragment();
        consultFragment = new ConsultFragment();
        hairShopFragment = new HairShopFragment();
        diaryFragment = new DiaryFragment();
        myPageFragment = new MyPageFragment();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout , homeFragment).commitAllowingStateLoss();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.page_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout,homeFragment).commitAllowingStateLoss();

                        return true;

                    case R.id.page_consult:

                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout,consultFragment).commitAllowingStateLoss();
                        return true;
                    case R.id.page_reservation:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout,hairShopFragment).commitAllowingStateLoss();

                        return true;
                    case R.id.page_diary:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout,diaryFragment).commitAllowingStateLoss();

                        return true;
                    case R.id.page_user:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout,myPageFragment).commitAllowingStateLoss();

                        return true;


                }

                return false;
            }
        });

    }
}