package com.example.mogastyle.Activities.Hair.Shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mogastyle.Activities.MainActivity;
import com.example.mogastyle.Adapters.Hair.Shop.ShopPagerAdapter;
import com.example.mogastyle.Bean.Shop;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.R;
import com.google.android.material.tabs.TabLayout;

//HairMain에서 미용실을 클릭했을 때, 넘어가는 페이지

public class ShopHomeActivity extends AppCompatActivity {
    ShopPagerAdapter shopPagerAdapter;

    Context con;
    ViewPager viewPager;
    ImageButton imageButton;
    TextView shopTitle;

    Shop shopBean = null;
    String urlAddr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_main);
        con = ShopHomeActivity.this;
        ////////////////////////////////////////////////////////////////
        //                  intent로 값 받아 shop bean 생성               //
        ////////////////////////////////////////////////////////////////
        Intent intent = getIntent();
        int no = intent.getIntExtra("smo", 0);
        String name = intent.getStringExtra("name");
        String tel = intent.getStringExtra("tel");
        String address = intent.getStringExtra("address");
        String postcode = intent.getStringExtra("postcode");
        String introduction = intent.getStringExtra("introduction");
        String holiday = intent.getStringExtra("holiday");
        String image = intent.getStringExtra("image");
        double rating = intent.getDoubleExtra("rating", 0);
        int count = intent.getIntExtra("count", 0);
        shopBean = new Shop(no, name, tel, address, postcode, introduction, holiday, image, rating,count);
        Log.v("^^^^^^^^", "get shopBean : " + shopBean.print());

        shopTitle = findViewById(R.id.tv_shopmain_title);
        shopTitle.setText(name);

        viewPager = findViewById(R.id.shopmain_pager);
        ////////////////////////////////////////////////////////////////////////////
        //      Shop no를 플레그먼트에 전달하기 위해서 어댑터에 값 전달                        //
        ////////////////////////////////////////////////////////////////////////////
        shopPagerAdapter = new ShopPagerAdapter(getSupportFragmentManager(), shopBean, con);
        viewPager.setAdapter(shopPagerAdapter);

        imageButton = findViewById(R.id.btn_home_goHome);
        imageButton.setOnClickListener(onClickListener);

        urlAddr = ShareVar.hostRootAddr+"Hair/Shop/shop_select.jsp";
        Log.v("Message",urlAddr);

        TabLayout tabLayout = findViewById(R.id.tab_layout_shopmain);
        tabLayout.setupWithViewPager(viewPager);

    }


    ////////////////////////////////////////////////////////////////////////////
    //      ImageButton 부분 intent로 메인 홈으로 이동                              //
    ////////////////////////////////////////////////////////////////////////////
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_home_goHome:
                    Intent intent = new Intent(ShopHomeActivity.this,MainActivity.class);
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