package com.example.mogastyle.Activities.Hair.Designer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.mogastyle.Adapters.Hair.Designer.FragmentDesignerDetailPageAdapter;
import com.example.mogastyle.Bean.Designer;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.NetworkTasks.Hair.Designer.DesignerDetailPageNetworkTask;
import com.example.mogastyle.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class DesignerDetailPageActivity extends AppCompatActivity {

    // Field
//    String urlAddr = "http://192.168.0.105:8080/test/" + "designer_detail_page_query_all.jsp";
    String urlAddr = ShareVar.hostRootAddr + "Hair/Designer/designer_detail_page_query_all.jsp";
    int dno = 0;
    int sno = 0;

    // Field
    Designer designer;
    ArrayList<Designer> members = null;

    // 화면에 있는 것
    TextView tv_name, tv_certificationDate, tv_introduction;
    ImageView iv_image;

    // onCreate : 초반에 딱 1번만 실행
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("Message", "DesignerDetailPageActivity_onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer_detail_page);

        Intent intent = getIntent();
        dno = intent.getIntExtra("dno",0);
        Log.v("Message", "dno : "+ dno);

        // ID값 가져오기
        iv_image = findViewById(R.id.iv_designer_detail_page_image);
        tv_name = findViewById(R.id.tv_designer_detail_page_name);
        tv_certificationDate = findViewById(R.id.tv_designer_detail_page_certificationDate);
        tv_introduction = findViewById(R.id.tv_designer_detail_page_introduction);

        // 네트워크 데이터 연결
        connectGetData();

        // Designer listView 중 한가지 항목 클릭했을 때 가져오는 값
        //이미지 :
        Glide.with(DesignerDetailPageActivity.this)
                .load(ShareVar.userImgPath + members.get(0).getImage())
                .placeholder(R.drawable.ic_no_image)
                .error(R.drawable.ic_no_image)
                .fallback(R.drawable.ic_no_image)
                .into(iv_image);
        tv_name.setText("이름 : " + members.get(0).getName());
        tv_certificationDate.setText("미용 자격증 취득일 : " + members.get(0).getCertificationDate());
        tv_introduction.setText("소개 : " + members.get(0).getIntroduction());
        sno = members.get(0).getShopNo();

        // Tab 관련
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        ViewPager viewPager = findViewById(R.id.pager);
        PagerAdapter pagerAdapter = new FragmentDesignerDetailPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        // Adapter와 연결
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager); // 같이 호출 불러주는 것 추가

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // Tab 부분 버튼을 클릭했을 때
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.v("Message", "onTabSelected_MainActivity");
                viewPager.setCurrentItem(tab.getPosition());
                Toast.makeText(DesignerDetailPageActivity.this, "선택됨", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.v("Message", "onTabUnselected_MainActivity");

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.v("Message", "onTabReselected_MainActivity");

            }
        });


    }

    // 네트워크 데이터를 타고 들어가서 검색하는 것
    private void connectGetData() {
        Log.v("Message", "DesignerDetailPageActivity_connectGetData");
        try {
            DesignerDetailPageNetworkTask networkTask = new DesignerDetailPageNetworkTask(DesignerDetailPageActivity.this, urlAddr+"?dno="+dno, "select");

            Log.v("Message", "############################0");
            Object obj = networkTask.execute().get();
            Log.v("Message", "############################1");
            members = (ArrayList<Designer>) obj;
            Log.v("Message", "############################2");

           // designer = members.get(0).getName();
            Log.v("Message", "designer : " +  members.get(0).getName()); // JSON 안에 []는 배열이라서 배열은0번부터 시작하기 때문에 0번을 가져온 것임 = 0번 배열의 Name

        }catch (Exception e){
            e.printStackTrace();
        }
    }


} // -------------------------------