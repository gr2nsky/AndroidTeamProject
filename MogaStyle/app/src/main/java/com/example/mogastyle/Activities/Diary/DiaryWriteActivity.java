package com.example.mogastyle.Activities.Diary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mogastyle.Common.LoginedUserInfo;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.NetworkTasks.Diary.DiaryMakeNewPage;
import com.example.mogastyle.NetworkTasks.Diary.DiaryMakeNewStyle;
import com.example.mogastyle.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DiaryWriteActivity extends AppCompatActivity {

    //IMAGE UPLOAD CODE --
    private final int REQ_CODE_SELECT_IMAGE = 300; // Gallery Return Code
    private String img_path = null; // 최종 file name
    private String f_ext = null;    // 최종 file extension
    File tempSelectFile;
    // --

    String devicePath = Environment.getDataDirectory().getAbsolutePath() + "/data/com.example.mogastyle/";

    ImageView image_selected_diary;

    Button btn_image_insert_diary, btn_diary_insert;
    EditText et_visit_date,et_shop_name,et_designer_name,et_contents_memo;

    int diary_no = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_write);

        image_selected_diary = findViewById(R.id.image_selected_diary);
        et_visit_date = findViewById(R.id.et_visit_date);
        et_shop_name = findViewById(R.id.et_shop_name);
        et_designer_name = findViewById(R.id.et_designer_name);
        et_contents_memo = findViewById(R.id.et_contents_memo);

        btn_image_insert_diary = findViewById(R.id.btn_image_insert_diary);
        btn_diary_insert = findViewById(R.id.btn_diary_insert);



        btn_diary_insert.setOnClickListener(onClickListener);
        btn_image_insert_diary.setOnClickListener(onClickListener);

        Intent intent = getIntent();
        diary_no = intent.getIntExtra("diary_no", 0);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_image_insert_diary:
                    //이미지 불러오기 -------
                    ActivityCompat.requestPermissions(DiaryWriteActivity.this, new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                    intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);

                    break;



                case R.id.btn_diary_insert:
                    // 다이어리 저장 하기 ------


                    String date = et_visit_date.getText().toString();
                    String hairShop = et_shop_name.getText().toString();
                    String designerName = et_designer_name.getText().toString();
                    String comments = et_contents_memo.getText().toString();

                    DiaryMakeNewPage diaryMakeNewPage = new DiaryMakeNewPage(DiaryWriteActivity.this ,ShareVar.hostRootAddr + "Diary/DiaryMakeNewPage.jsp" ,img_path , image_selected_diary , date , hairShop , designerName , comments ,Integer.toString(diary_no) );
                    try{
                        Integer result = diaryMakeNewPage.execute().get();
                        switch (result) {
                            case 1:
                                Toast.makeText(DiaryWriteActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                                //              Device에 생성한 임시 파일 삭제
                                File file = new File(img_path);
                                file.delete();
                                finish();
                                break;
                            case 0:
                                Toast.makeText(DiaryWriteActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }catch (Exception e){
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
                image_selected_diary.setImageBitmap(image_bitmap_copy);

                // 파일 이름 및 경로 바꾸기(임시 저장, 경로는 임의로 지정 가능)
                String date = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());
                String imageName = date + "." + f_ext;
                tempSelectFile = new File(devicePath , imageName);
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
        f_ext = imgPath.substring(imgPath.length()-3, imgPath.length());

        return imgPath;
    }

}