package com.example.mogastyle.Activities.Hair.Shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mogastyle.Activities.Consult.ConsultMainActivity;
import com.example.mogastyle.Activities.Diary.DiaryMainActivity;
import com.example.mogastyle.Activities.MainActivity;
import com.example.mogastyle.Activities.MyPage.MyPageMainActivity;
import com.example.mogastyle.Adapters.Hair.Shop.ShopListAdapter;
import com.example.mogastyle.Adapters.Hair.Shop.ShopPagerAdapter;
import com.example.mogastyle.Bean.Shop;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.NetworkTasks.Hair.ShopNetworkTask;
import com.example.mogastyle.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

//HairMain에서 미용실을 클릭했을 때, 넘어가는 페이지

public class ShopHomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ShopPagerAdapter shopPagerAdapter;
    Intent intent;
    ViewPager viewPager;
    ImageButton imageButton;
    TextView ShopTitle;
    int shopNo = 0;
    private String name,tel,address,introduction,holiday,postcode,image;

    String urlAddr = null;
    ArrayList<Shop> shops;
    ShopListAdapter adapters;
    String desktopIP = ShareVar.hostIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_main);

        ShopTitle = findViewById(R.id.tv_shopmain_title);

        intent = getIntent();
        shopNo = intent.getIntExtra("smo",0);
        Log.v("######################", "sno : " + shopNo);

        viewPager = findViewById(R.id.shopmain_pager);
        ////////////////////////////////////////////////////////////////////////////
        //      Shop no를 플레그먼트에 전달하기 위해서 어댑터에 전달                      //
        ////////////////////////////////////////////////////////////////////////////
        ShopPagerAdapter adapters = new ShopPagerAdapter(getSupportFragmentManager(), shopNo);
        viewPager.setAdapter(adapters);

        imageButton = findViewById(R.id.btn_home_goHome);

        imageButton.setOnClickListener(onClickListener);

        desktopIP = intent.getStringExtra("desktopIP");
        urlAddr = ShareVar.hostRootAddr+"Hair/Shop/shop_select.jsp";
        Log.v("Message",urlAddr);



        TabLayout tabLayout = findViewById(R.id.tab_layout_shopmain);
        tabLayout.setupWithViewPager(viewPager);

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
    protected void onResume() {
        super.onResume();
        //데이터를 갱신할 수 있는 LIFECYCLE
        connectGetData();
    }

    private void connectGetData(){
        try{
            ShopNetworkTask networkTask = new ShopNetworkTask(ShopHomeActivity.this, urlAddr+"?sno="+shopNo, "select");
            Object obj = networkTask.execute().get();
            shops = (ArrayList<Shop>) obj;
            ShopTitle.setText(shops.get(0).getName());

            adapters = new ShopListAdapter(ShopHomeActivity.this, R.layout.activity_shop_main, shops);

     //       listView.setAdapter(adapters);
    //        listView.setOnItemClickListener(onItemClickListener);
//            listView.setOnItemLongClickListener(onItemLongClickListener);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_home_goHome:
                    intent = new Intent(ShopHomeActivity.this,MainActivity.class);
                    startActivity(intent);

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