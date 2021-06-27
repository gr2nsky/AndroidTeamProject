package com.example.mogastyle.Activities.Hair;

import android.content.Context;
import android.content.Intent;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mogastyle.Activities.Consult.ConsultMainActivity;
import com.example.mogastyle.Activities.Diary.DiaryMainActivity;
import com.example.mogastyle.Activities.MainActivity;
import com.example.mogastyle.Activities.MyPage.MyPageMainActivity;
import com.example.mogastyle.Adapters.Hair.Shop.ShopListAdapter;
import com.example.mogastyle.Bean.Shop;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.NetworkTasks.Hair.ShopNetworkTask;
import com.example.mogastyle.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

public class HairMainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    String urlAddr = null;
    ArrayList<Shop> shops;
    ShopListAdapter adapters;
    ListView listView;
    String desktopIP = ShareVar.hostIP;

    //MapView
    public MapView mapView;
    public MapPoint mapPoint;
    public LocationManager locationManager;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hair_main);

        listView = findViewById(R.id.lv_shoplist);
        urlAddr = ShareVar.hostRootAddr+"Hair/Shop/shop_select.jsp";

        //Map StartPoint
             mapView = new MapView(this);

            ViewGroup mapViewContainer = findViewById(R.id.map_view);
            mapViewContainer.addView(mapView);
            mapView.removeAllPOIItems();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading);
            }
        },4000);

        Marker("한세고" , 37.5514579595, 126.951949155);

        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);



        //Map StartPoint---

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.page_reservation);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
    } //onCreate



    //KAKAO-MAP
    public void Marker(String MarkerName , double startX, double startY){
        mapPoint = MapPoint.mapPointWithGeoCoord(startY , startX);
        mapView.setMapCenterPoint(mapPoint , true);

        MapPOIItem marker = new MapPOIItem();
        marker.setItemName(MarkerName);
        marker.setMapPoint(mapPoint);

        //Custom image 로 할수 이따!!
        marker.setMarkerType(MapPOIItem.MarkerType.RedPin);

        marker.setSelectedMarkerType(MapPOIItem.MarkerType.BluePin);
        mapView.addPOIItem(marker);
    }



    //KAKAO-MAP--

    @Override
    protected void onResume() {
        super.onResume();
        connectGetData();
    }

    private void connectGetData(){
        try{
            ShopNetworkTask networkTask = new ShopNetworkTask(HairMainActivity.this, urlAddr, "select");
            Object obj = networkTask.execute().get();
            shops = (ArrayList<Shop>) obj;

            adapters = new ShopListAdapter(HairMainActivity.this, R.layout.list_item_hair_main_shop_list, shops);
            listView.setAdapter(adapters);
//            listView.setOnItemClickListener(onItemClickListener);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext() , MainActivity.class));
        overridePendingTransition(0,0);
    }


    //bottom navigation
    BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
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
    };
}