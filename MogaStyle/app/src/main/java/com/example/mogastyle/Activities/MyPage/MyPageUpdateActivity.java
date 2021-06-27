package com.example.mogastyle.Activities.MyPage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mogastyle.Common.LoginedUserInfo;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.NetworkTasks.MyPage.MyPageUpdateImage;
import com.example.mogastyle.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyPageUpdateActivity extends AppCompatActivity {

    ImageView iv_my_page_update_image;

    Button btn_my_page_update_image_find, btn_my_page_update_image_update;


    //IMAGE UPLOAD CODE --
    private final int REQ_CODE_SELECT_IMAGE = 300; // Gallery Return Code
    private String img_path = null; // 최종 file name
    private String f_ext = null;    // 최종 file extension
    File tempSelectFile;

    // --
    String devicePath = Environment.getDataDirectory().getAbsolutePath() + "/data/com.example.mogastyle/";

    String urlAddr = ShareVar.hostRootAddr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page_update);


        iv_my_page_update_image = findViewById(R.id.iv_my_page_update_image);

        Log.d("NET" ,devicePath);


            Glide.with(this)
                    .load(LoginedUserInfo.user.getUserImage())
                    .placeholder(R.drawable.ic_no_image)
                    .error(R.drawable.ic_no_image)
                    .fallback(R.drawable.ic_no_image)
                    .into(iv_my_page_update_image);


        //사용자에게 사진(Media) 사용 권한 받기
        ActivityCompat.requestPermissions(MyPageUpdateActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        btn_my_page_update_image_update = findViewById(R.id.btn_my_page_update_image_update);
        btn_my_page_update_image_find = findViewById(R.id.btn_my_page_update_image_find);

        btn_my_page_update_image_find.setOnClickListener(onClickListener);
        btn_my_page_update_image_update.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_my_page_update_image_find:
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                    intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);

                    break;

                case R.id.btn_my_page_update_image_update:
                    MyPageUpdateImage myPageUpdateImage = new MyPageUpdateImage(MyPageUpdateActivity.this, urlAddr + "MyPage/MyPageUpdateImage.jsp", img_path, iv_my_page_update_image, Integer.toString(LoginedUserInfo.user.getNo()));
                    try {
                        Integer result = myPageUpdateImage.execute().get();

                        //              doInBackground의 결과값으로 Toast생성
                        switch (result) {
                            case 1:

                                Toast.makeText(MyPageUpdateActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                                //              Device에 생성한 임시 파일 삭제
                                File file = new File(img_path);
                                file.delete();

                                Intent intent1 = new Intent(MyPageUpdateActivity.this , MyPageMainActivity.class);
                                startActivity(intent1);

                                break;
                            case 0:
                                Toast.makeText(MyPageUpdateActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    break;


            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        if (requestCode == REQ_CODE_SELECT_IMAGE && resultCode == Activity.RESULT_OK) {
            try {
                //이미지의 URI를 얻어 경로값으로 반환.
                img_path = getImagePathToUri(data.getData());


                //이미지를 비트맵형식으로 반환
                Bitmap image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());

                //image_bitmap 으로 받아온 이미지의 사이즈를 임의적으로 조절함. width: 400 , height: 300
                Bitmap image_bitmap_copy = Bitmap.createScaledBitmap(image_bitmap, 400, 300, true);
                iv_my_page_update_image.setImageBitmap(image_bitmap_copy);

                // 파일 이름 및 경로 바꾸기(임시 저장, 경로는 임의로 지정 가능)
                String date = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());
                String imageName = date + "." + f_ext;

                tempSelectFile = new File(devicePath, imageName);
                OutputStream out = new FileOutputStream(tempSelectFile);
                image_bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

                // 임시 파일 경로로 위의 img_path 재정의
                img_path = devicePath + imageName;

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private String getImagePathToUri(Uri data) {

        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        //이미지의 경로 값
        String imgPath = cursor.getString(column_index);


        //이미지의 이름 값
        String imgName = imgPath.substring(imgPath.lastIndexOf("/") + 1);

        // 확장자 명 저장
        f_ext = imgPath.substring(imgPath.length() - 3, imgPath.length());

        return imgPath;
    }
}