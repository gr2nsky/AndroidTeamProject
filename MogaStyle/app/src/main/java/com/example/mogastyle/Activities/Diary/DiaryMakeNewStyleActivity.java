package com.example.mogastyle.Activities.Diary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mogastyle.Common.LoginedUserInfo;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.NetworkTasks.Diary.DiaryMakeNewStyle;
import com.example.mogastyle.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DiaryMakeNewStyleActivity extends AppCompatActivity {

    //IMAGE UPLOAD CODE --
    private final int REQ_CODE_SELECT_IMAGE = 300; // Gallery Return Code
    private String img_path = null; // 최종 file name
    private String f_ext = null;    // 최종 file extension
    File tempSelectFile;
    // --

    ImageView iv_diary_make_new_diary_selectedImage;

    Button btn_diary_make_new_diary_getGalleryPhoto ,btn_diary_make_new_diary_save;

    EditText et_diary_make_new_diary_styleTitle;

    Spinner sp_diary_make_new_diary_styleSpinner;

    String devicePath = Environment.getDataDirectory().getAbsolutePath() + "/data/com.example.mogastyle/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_make_new_style);

        iv_diary_make_new_diary_selectedImage = findViewById(R.id.iv_diary_make_new_diary_selectedImage);

        btn_diary_make_new_diary_getGalleryPhoto = findViewById(R.id.btn_diary_make_new_diary_getGalleryPhoto);
        btn_diary_make_new_diary_save = findViewById(R.id.btn_diary_make_new_diary_save);

        et_diary_make_new_diary_styleTitle = findViewById(R.id.et_diary_make_new_diary_styleTitle);

        sp_diary_make_new_diary_styleSpinner = findViewById(R.id.sp_diary_make_new_diary_styleSpinner);

        ArrayAdapter styleAdapter = ArrayAdapter.createFromResource(this ,R.array.hairStyleArray , android.R.layout.simple_spinner_dropdown_item);

        sp_diary_make_new_diary_styleSpinner.setAdapter(styleAdapter);


        btn_diary_make_new_diary_getGalleryPhoto.setOnClickListener(onClickListener);
        btn_diary_make_new_diary_save.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.btn_diary_make_new_diary_getGalleryPhoto:
                    ActivityCompat.requestPermissions(DiaryMakeNewStyleActivity.this, new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                    intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);

                    break;

                case R.id.btn_diary_make_new_diary_save:
                    DiaryMakeNewStyle networkTask = new DiaryMakeNewStyle(DiaryMakeNewStyleActivity.this, iv_diary_make_new_diary_selectedImage, ShareVar.hostRootAddr + "Diary/DiaryMakeNewStyle.jsp",img_path, et_diary_make_new_diary_styleTitle.getText().toString() , sp_diary_make_new_diary_styleSpinner.getSelectedItem().toString() , Integer.toString(LoginedUserInfo.user.getNo()));
                    try {
                        Integer result = networkTask.execute().get();

                        //              doInBackground의 결과값으로 Toast생성
                        switch (result) {
                            case 1:
                                Toast.makeText(DiaryMakeNewStyleActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                                //              Device에 생성한 임시 파일 삭제
                                File file = new File(img_path);
                                file.delete();
                                finish();
                                break;
                            case 0:
                                Toast.makeText(DiaryMakeNewStyleActivity.this, "Error", Toast.LENGTH_SHORT).show();
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
                iv_diary_make_new_diary_selectedImage.setImageBitmap(image_bitmap_copy);

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