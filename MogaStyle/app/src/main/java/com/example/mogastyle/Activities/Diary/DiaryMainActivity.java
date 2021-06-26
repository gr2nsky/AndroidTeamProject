package com.example.mogastyle.Activities.Diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.mogastyle.Activities.Consult.ConsultMainActivity;
import com.example.mogastyle.Activities.Hair.HairMainActivity;
import com.example.mogastyle.Activities.MainActivity;
import com.example.mogastyle.Activities.MyPage.MyPageMainActivity;
import com.example.mogastyle.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class DiaryMainActivity extends AppCompatActivity {

    Button btn_newStyle,btn_originStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_main);


        btn_newStyle = findViewById(R.id.btn_newStyle);
        btn_originStyle = findViewById(R.id.btn_originStyle);

        btn_newStyle.setOnClickListener(onClickListener);
        btn_originStyle.setOnClickListener(onClickListener);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.page_diary);

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

    View.OnClickListener onClickListener = new View.OnClickListener() {
        Intent intent;
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.btn_newStyle:
                    intent = new Intent(DiaryMainActivity.this , DiaryMakeNewStyleActivity.class);
                    startActivity(intent);
                    break;

                case R.id.btn_originStyle:
                    intent = new Intent(DiaryMainActivity.this , DiaryShowOriginStyleActivity.class);
                    startActivity(intent);
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