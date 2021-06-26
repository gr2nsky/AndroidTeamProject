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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.NetworkTasks.Diary.DiaryMakeDiaryPage;
import com.example.mogastyle.NetworkTasks.Diary.DiaryUpdateDiary;
import com.example.mogastyle.NetworkTasks.Diary.DiaryUpdateDiaryPage;
import com.example.mogastyle.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DiaryListDetailActivity extends AppCompatActivity {

    //IMAGE UPLOAD CODE --
    private final int REQ_CODE_SELECT_IMAGE = 300; // Gallery Return Code
    private String img_path = null; // 최종 file name
    private String f_ext = null;    // 최종 file extension
    File tempSelectFile;
    // --

    String urlAddr = ShareVar.hostRootAddr;
    String devicePath = Environment.getDataDirectory().getAbsolutePath() + "/data/com.example.mogastyle/";

    ImageView image_selected_detail_diary;

    Button btn_image_insert_detail_diary ,btn_detail_update,btn_detail_delete;

    EditText et_visit_detail_date, et_shop_detail_name,et_designer_detail_name,et_contents_detail_memo;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list_detail);

        image_selected_detail_diary = findViewById(R.id.image_selected_detail_diary);
        btn_image_insert_detail_diary = findViewById(R.id.btn_image_insert_detail_diary);
        btn_detail_update = findViewById(R.id.btn_detail_update);
        btn_detail_delete = findViewById(R.id.btn_detail_delete);

        et_visit_detail_date = findViewById(R.id.et_visit_detail_date);
        et_shop_detail_name = findViewById(R.id.et_shop_detail_name);
        et_designer_detail_name = findViewById(R.id.et_designer_detail_name);
        et_contents_detail_memo = findViewById(R.id.et_contents_detail_memo);

        intent = getIntent();

        String date = intent.getStringExtra("date");
        String image = intent.getStringExtra("image");
        String hairShop = intent.getStringExtra("hairShop");
        String designer = intent.getStringExtra("designer");
        String comments = intent.getStringExtra("comments");

        et_visit_detail_date.setText(date);
        et_shop_detail_name.setText(hairShop);
        et_designer_detail_name.setText(designer);
        et_contents_detail_memo.setText(comments);

        //-- button action
        btn_image_insert_detail_diary.setOnClickListener(onClickListener);
        btn_detail_update.setOnClickListener(onClickListener);
        btn_detail_delete.setOnClickListener(onClickListener);

        //image 붙이는 작업
        Glide.with(this)
                .load(ShareVar.diaryImgPath + image)
                .into(image_selected_detail_diary);
        //image 붙이는 작업 ---

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            int no;
            switch(v.getId()){
                case R.id.btn_image_insert_detail_diary:
                    /// new Photo for edit diary_page
                    ActivityCompat.requestPermissions(DiaryListDetailActivity.this, new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

                    intent = new Intent(Intent.ACTION_PICK);
                    intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                    intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);


                    break;

                case R.id.btn_detail_update:
                   // Update diary_page

                    ////////----
                    intent = getIntent();
                    no = intent.getIntExtra("no",0);
                    String strNo = Integer.toString(no);
                    String date = et_visit_detail_date.getText().toString();
                    String hairShop = et_shop_detail_name.getText().toString();
                    String designerName = et_designer_detail_name.getText().toString();
                    String comments = et_contents_detail_memo.getText().toString();

                    DiaryUpdateDiaryPage diaryUpdateDiaryPage = new DiaryUpdateDiaryPage(DiaryListDetailActivity.this , urlAddr + "Diary/styleDiaryPageUpdate.jsp", img_path, image_selected_detail_diary, strNo , date ,hairShop, designerName,comments);

                    try{

                        Integer result = diaryUpdateDiaryPage.execute().get();

                        switch(result){
                            case 1:
                                Toast.makeText(DiaryListDetailActivity.this, hairShop + " is updated!", Toast.LENGTH_SHORT).show();
                                File file = new File(img_path);
                                file.delete();
                                intent = new Intent(DiaryListDetailActivity.this , DiaryShowOriginStyleActivity.class);
                                startActivity(intent);
                                break;
                            case 0:
                                Toast.makeText(DiaryListDetailActivity.this, "Update Fail !", Toast.LENGTH_SHORT).show();
                                break;
                        }

                    }catch(Exception e){

                        e.printStackTrace();
                    }
                    ////////----

                    break;


                case R.id.btn_detail_delete:
                    //Delete diary_page

                    intent = getIntent();
                    no = intent.getIntExtra("no" , 0);

                    urlAddr = urlAddr+"Diary/styleDiaryPageDelete.jsp?no="+no;
                    String hairShop1 = et_shop_detail_name.getText().toString();
                    String deleteResult = connectDeleteData();

                    if(deleteResult.equals("1")){
                        Toast.makeText(DiaryListDetailActivity.this, hairShop1 + " is Deleted!", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(DiaryListDetailActivity.this, "Delete Fail !", Toast.LENGTH_SHORT).show();
                    }
                    break;


            }
        }
    };


    private String connectDeleteData(){
        String result = null;
        try{
            DiaryMakeDiaryPage networkTaskDiary = new DiaryMakeDiaryPage(DiaryListDetailActivity.this,urlAddr ,"delete");
            Object obj = networkTaskDiary.execute().get();
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
                image_selected_detail_diary.setImageBitmap(image_bitmap_copy);

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