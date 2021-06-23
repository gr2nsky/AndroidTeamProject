package com.example.mogastyle.Activities.MyPage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mogastyle.Common.LoginedUserInfo;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.R;

public class MyPageUpdateActivity extends AppCompatActivity {

    ImageView iv_my_page_update_image;

    Button btn_my_page_update_image_find,btn_my_page_update_image_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page_update);


        iv_my_page_update_image = findViewById(R.id.iv_my_page_update_image);

        Glide.with(this)
                .load(ShareVar.userImgPath + LoginedUserInfo.user.getUserImage())
                .into(iv_my_page_update_image);

    }
}