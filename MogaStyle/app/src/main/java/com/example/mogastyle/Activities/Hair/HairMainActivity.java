package com.example.mogastyle.Activities.Hair;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mogastyle.Activities.Consult.ConsultMainActivity;
import com.example.mogastyle.Activities.Diary.DiaryMainActivity;
import com.example.mogastyle.Activities.Hair.Shop.ShopHomeActivity;
import com.example.mogastyle.Activities.Hair.Shop.ShopHomeFragment;
import com.example.mogastyle.Activities.MainActivity;
import com.example.mogastyle.Activities.MyPage.MyPageMainActivity;
import com.example.mogastyle.Adapters.Hair.Shop.ShopListAdapter;
import com.example.mogastyle.Bean.Shop;
import com.example.mogastyle.NetworkTasks.Hair.ShopNetworkTask;
import com.example.mogastyle.R;
import com.example.mogastyle.ShareVar.ShareVar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HairMainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    //MapView mapView = new MapView(this);
    private Spinner spinner;
    ArrayAdapter<CharSequence> adapter = null;


    String urlAddr = null;
    ArrayList<Shop> shops;
    ShopListAdapter adapters;
    ListView listView;
    String desktopIP = ShareVar.WindowIP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //MapView를 보이게 하기 위해서 해 놓은 코드 그러나 지워도 됨.
//       ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
//        mapViewContainer.addView(mapView);


       // ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
       // mapViewContainer.addView(mapView);

        setContentView(R.layout.activity_hair_main);

        listView = findViewById(R.id.lv_shoplist);

        adapter = ArrayAdapter.createFromResource(this,R.array.ShopChoose,
                android.R.layout.simple_spinner_dropdown_item);
        spinner = findViewById(R.id.sp_hairmain_choose);
        spinner.setAdapter(adapter);

        Intent intent = getIntent();
        desktopIP = intent.getStringExtra("desktopIP");
        urlAddr = ShareVar.hostRootAddr+"test/shop_select.jsp";
        Log.v("Message",urlAddr);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.page_reservation);

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
    protected void onResume() {
        super.onResume();
        //데이터를 갱신할 수 있는 LIFECYCLE
        connectGetData();
    }

    private void connectGetData(){
        try{
            ShopNetworkTask networkTask = new ShopNetworkTask(HairMainActivity.this, urlAddr, "select");
            Object obj = networkTask.execute().get();
            shops = (ArrayList<Shop>) obj;

            adapters = new ShopListAdapter(HairMainActivity.this, R.layout.activity_hair_layout, shops);
            listView.setAdapter(adapters);
          listView.setOnItemClickListener(onItemClickListener);
//            listView.setOnItemLongClickListener(onItemLongClickListener);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        Intent intent = null;
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            intent = new Intent(HairMainActivity.this, ShopHomeActivity.class);
            intent.putExtra("name", shops.get(position).getName());
            intent.putExtra("tel", shops.get(position).getTel());
            intent.putExtra("address", shops.get(position).getAddress());
            intent.putExtra("postcode", shops.get(position).getPostcode());
            intent.putExtra("introduction", shops.get(position).getIntroduction());
            intent.putExtra("holiday", shops.get(position).getHoliday());
            intent.putExtra("image", shops.get(position).getImage());
            intent.putExtra("desktopIP", desktopIP);
            startActivity(intent);
        }
    };
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext() , MainActivity.class));
        overridePendingTransition(0,0);
    }
}