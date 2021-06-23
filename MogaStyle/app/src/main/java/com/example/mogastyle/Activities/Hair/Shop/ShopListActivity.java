package com.example.mogastyle.Activities.Hair.Shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.example.mogastyle.Activities.Consult.ConsultMainActivity;
import com.example.mogastyle.Activities.Diary.DiaryMainActivity;
import com.example.mogastyle.Activities.MainActivity;
import com.example.mogastyle.Activities.MyPage.MyPageMainActivity;
import com.example.mogastyle.Adapters.Hair.Shop.ShopPagerAdapter;
import com.example.mogastyle.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class ShopListActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ShopPagerAdapter shopPagerAdapter;
    Intent intent;
    ViewPager viewPager;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        viewPager = findViewById(R.id.pager);
        ShopPagerAdapter adapter = new ShopPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        imageButton = findViewById(R.id.btn_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.page_reservation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.page_home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.page_consult:
                        startActivity(new Intent(getApplicationContext(), ConsultMainActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.page_reservation:
                        return true;
                    case R.id.page_diary:
                        startActivity(new Intent(getApplicationContext(), DiaryMainActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.page_user:
                        startActivity(new Intent(getApplicationContext(), MyPageMainActivity.class));
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
        startActivity(new Intent(getApplicationContext() , MainActivity.class));
        overridePendingTransition(0,0);
    }
}