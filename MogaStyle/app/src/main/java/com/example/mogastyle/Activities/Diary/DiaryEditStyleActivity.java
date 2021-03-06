package com.example.mogastyle.Activities.Diary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
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

import com.bumptech.glide.Glide;
import com.example.mogastyle.Common.LoginedUserInfo;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.NetworkTasks.Diary.DiaryEditDeleteDiary;
import com.example.mogastyle.NetworkTasks.Diary.DiaryUpdateDiary;
import com.example.mogastyle.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DiaryEditStyleActivity extends AppCompatActivity {

    //IMAGE UPLOAD CODE --
    private final int REQ_CODE_SELECT_IMAGE = 300; // Gallery Return Code
    private String img_path = null; // 최종 file name
    private String f_ext = null;    // 최종 file extension
    File tempSelectFile;
    // --

    String urlAddr = ShareVar.hostRootAddr;
    String devicePath = Environment.getDataDirectory().getAbsolutePath() + "/data/com.example.mogastyle/";

    ImageView selectedImageEdit;

    Button btn_getGalleryPhotoEdit , btn_styleUpdate , btn_styleDelete;

    EditText styleTitleEdit;

    Spinner styleSpinnerEdit;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_edit_style);




        selectedImageEdit = findViewById(R.id.selectedImageEdit);

        intent = getIntent();
        String imageName = intent.getStringExtra("image");
        //image 붙이는 작업
        Glide.with(this)
                .load(ShareVar.diaryImgPath + imageName)
                .into(selectedImageEdit);
        //image 붙이는 작업 ---

        btn_getGalleryPhotoEdit = findViewById(R.id.btn_getGalleryPhotoEdit);
        btn_styleUpdate = findViewById(R.id.btn_styleUpdate);
        btn_styleDelete = findViewById(R.id.btn_styleDelete);

        styleTitleEdit = findViewById(R.id.styleTitleEdit);

        styleSpinnerEdit = findViewById(R.id.styleSpinnerEdit);
        ArrayAdapter styleEditAdapter = ArrayAdapter.createFromResource(this , R.array.hairStyleArray , android.R.layout.simple_spinner_dropdown_item);
        styleSpinnerEdit.setAdapter(styleEditAdapter);


        intent = getIntent();
        String hairLength = intent.getStringExtra("hairLength");

        switch(hairLength){
            case "장발":
                styleSpinnerEdit.setSelection(0);
                break;
            case "단발":
                styleSpinnerEdit.setSelection(1);
                break;
            case "숏컷":
                styleSpinnerEdit.setSelection(2);
                break;
        }
        btn_getGalleryPhotoEdit.setOnClickListener(onClickListener);
        btn_styleUpdate.setOnClickListener(onClickListener);
        btn_styleDelete.setOnClickListener(onClickListener);

        String styleTitle = intent.getStringExtra("title");

        styleTitleEdit.setText(styleTitle);

    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_getGalleryPhotoEdit:



                    ActivityCompat.requestPermissions(DiaryEditStyleActivity.this, new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                    intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);

                    break;

                case R.id.btn_styleUpdate:

                    intent = getIntent();
                    int no = intent.getIntExtra("no",0);
                    String strNo = Integer.toString(no);
                    String updateTitle = styleTitleEdit.getText().toString();
                    String hairLength = styleSpinnerEdit.getSelectedItem().toString();

                    DiaryUpdateDiary diaryUpdateDiary = new DiaryUpdateDiary(DiaryEditStyleActivity.this , urlAddr + "Diary/styleDiaryBookUpdate.jsp", img_path, selectedImageEdit, strNo , updateTitle, hairLength);

                    try{

                        Integer result = diaryUpdateDiary.execute().get();

                        switch(result){
                            case 1:
                                Toast.makeText(DiaryEditStyleActivity.this, updateTitle + " is updated!", Toast.LENGTH_SHORT).show();
                                File file = new File(img_path);
                                file.delete();
                                intent = new Intent(DiaryEditStyleActivity.this , DiaryShowOriginStyleActivity.class);
                                startActivity(intent);
                                break;
                            case 0:
                                Toast.makeText(DiaryEditStyleActivity.this, "Update Fail !", Toast.LENGTH_SHORT).show();
                                break;
                        }

                    }catch(Exception e){

                        e.printStackTrace();
                    }

                    break;

                case R.id.btn_styleDelete:
                    intent = getIntent();
                    int no1 = intent.getIntExtra("no",0);
                    urlAddr = urlAddr+"Diary/styleDiaryBookDelete.jsp?no="+no1;

                    String deleteResult = connectDeleteData();
                    String deleteTitle = styleTitleEdit.getText().toString();
                    if(deleteResult.equals("1")){
                        Toast.makeText(DiaryEditStyleActivity.this, deleteTitle + " is Deleted!", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(DiaryEditStyleActivity.this, "Delete Fail !", Toast.LENGTH_SHORT).show();
                    }

                    break;

            }
        }
    };


    private String connectDeleteData(){
        String result = null;
        try{
            DiaryEditDeleteDiary networkTask = new DiaryEditDeleteDiary(DiaryEditStyleActivity.this,urlAddr,"delete");
            Object obj = networkTask.execute().get();
            result = (String) obj;
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;

    }

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
                selectedImageEdit.setImageBitmap(image_bitmap_copy);

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