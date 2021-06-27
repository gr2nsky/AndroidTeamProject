package com.example.mogastyle.Activities.Review;


import android.Manifest;
import android.app.Activity;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.NetworkTasks.Hair.Reservation.RetrofitCall;
import com.example.mogastyle.NetworkTasks.Hair.Reservation.RetrofitService;
import com.example.mogastyle.NetworkTasks.Hair.Reservation.ReviewUpload;
import com.example.mogastyle.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewSubmitActivity extends AppCompatActivity {
    String TAG = "!!!!!!!!!!!!!!!!!";

    ImageView imageView;
    RatingBar ratingBar;
    EditText et_content;
    Button btn_submit;
    Button btn_cancel;

    int rno = 0;
    int typeToken = 0;

    private final int REQ_CODE_SELECT_IMAGE = 300; // Gallery Return Code
    private String filePath = null; // 최종 file name
    private String f_ext = null;    // 최종 file extension
    String imageName = null;
    File tempSelectFile;
    // --
    String devicePath = Environment.getDataDirectory().getAbsolutePath() + "/data/com.example.mogastyle/";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_submit_review);

        /////////////////////////////////////
        Intent intent2  = getIntent();
        rno = intent2.getIntExtra("rno", 0);
        typeToken = intent2.getIntExtra("typeTokent", 0);
        ////////////////////////////////////

        imageView = findViewById(R.id.iv_review_submit);
        ratingBar = findViewById(R.id.rb_review_submit);
        et_content = findViewById(R.id.et_review_submit);
        btn_submit = findViewById(R.id.btn_review_submit_ok);
        btn_cancel = findViewById(R.id.btn_review_submit_cancel);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dataDinding();
                netTask();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imageView.setOnClickListener(imageSet);
    }  //onCreate

    ///////////////////////////////////////////////
    //         전송전 전역변수를 multi 폼으로 바인딩      //
    ///////////////////////////////////////////////
    public void dataDinding(){
        String rnoToString = Integer.toString(rno);
        String rating = Integer.toString(Math.round(ratingBar.getRating()));
        String content = et_content.getText().toString();
        RequestBody rnoBody = RequestBody.create(MediaType.parse("text/plain"), rnoToString);
        RequestBody ratingBody = RequestBody.create(MediaType.parse("text/plain"), rating);
        RequestBody contentBody = RequestBody.create(MediaType.parse("text/plain"), content);

        HashMap<String, RequestBody> requestMap = new HashMap<>();
        requestMap.put("rno", rnoBody);
        requestMap.put("rating", ratingBody);
        requestMap.put("content", contentBody);

        // Uri 타입의 파일경로를 가지는 RequestBody 객체 생성
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), filePath);
        // RequestBody로 Multipart.Part 객체 생성
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("image", imageName, fileBody);

        submitTask(requestMap, filePart);
    }
    ///////////////////////////////////////////////
    //            retrofit으로 비동기 통신           //
    ///////////////////////////////////////////////
    public void submitTask(HashMap<String, RequestBody> requestMap, MultipartBody.Part filePart){
        RetrofitService retrofitService = RetrofitCall.reservationService();
        retrofitService.insertReview(requestMap, filePart).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String result = response.body();
                Log.d(TAG, "onResponse:성공");
                Log.d(TAG, "결과 body: " + response.body());
                Log.d(TAG, "결과 reservationList: " + result);
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG, "onResponse:실패");
                Log.d(TAG, "결과 : " + t.toString());
            }
        });
    }
    ///////////////////////////////////////////////
    //                확인버튼 누르면 수정 수행         //
    ///////////////////////////////////////////////
    public void updateTask(){
    }
    ///////////////////////////////////////////////////////
    //   이미지버튼 클릭시 갤러리를 통해 선택한 파일 패스 가져옴       //
    //////////////////////////////////////////////////////
    View.OnClickListener imageSet = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ActivityCompat.requestPermissions(ReviewSubmitActivity.this, new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
            intent = new Intent(Intent.ACTION_PICK);
            intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
            intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
        }
    };
    ///////////////////////////////////////////////////////
    //       갤러리에서 사진 선택한 결과로 이름 및 경로 확장자 가져옴   //
    //////////////////////////////////////////////////////
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE_SELECT_IMAGE && resultCode == Activity.RESULT_OK) {
            try {
                //이미지의 URI를 얻어 경로값으로 반환.
                filePath = getImagePathToUri(data.getData());
                Log.v(TAG, "image path :" + filePath);
                Log.v(TAG, "Data :" +String.valueOf(data.getData()));
                //이미지를 비트맵형식으로 반환
                Bitmap image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                //image_bitmap 으로 받아온 이미지의 사이즈를 임의적으로 조절함. width: 400 , height: 300
                Bitmap image_bitmap_copy = Bitmap.createScaledBitmap(image_bitmap, 400, 300, true);
                imageView.setImageBitmap(image_bitmap_copy);
                // 파일 이름 및 경로 바꾸기(임시 저장, 경로는 임의로 지정 가능)
                String date = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());
                imageName = date + "." + f_ext;
                tempSelectFile = new File(devicePath , imageName);
                OutputStream out = new FileOutputStream(tempSelectFile);
                image_bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                // 임시 파일 경로로 위의 img_path 재정의
                filePath = devicePath + imageName;
                Log.v(TAG,"fileName :" + filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    ///////////////////////////////////////////////////////
    //                           선택한 파일의 uri 획득      //
    //////////////////////////////////////////////////////
    private String getImagePathToUri(Uri data) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        //이미지의 경로 값
        String filePath = cursor.getString(column_index);
        Log.v(TAG, "Image Path :" + filePath);
        //이미지의 이름 값
        String imageName = filePath.substring(filePath.lastIndexOf("/") + 1);
        // 확장자 명 저장
        f_ext = filePath.substring(filePath.length()-3, filePath.length());
        return filePath;
    }

    public void netTask(){
        String urlAddr = ShareVar.hostRootAddr + "Reservation/reviewUpdate.jsp";

        String rnoToString = Integer.toString(rno);
        String rating = Integer.toString(Math.round(ratingBar.getRating()));
        String content = et_content.getText().toString();
        ReviewUpload reviewUpload = new ReviewUpload(ReviewSubmitActivity.this , urlAddr, filePath, imageView, rnoToString ,content, rating);
        try{
            Integer result = reviewUpload.execute().get();
            switch(result){
                case 1:
                    Toast.makeText(ReviewSubmitActivity.this, " is updated!", Toast.LENGTH_SHORT).show();
                    File file = new File(filePath);
                    file.delete();
                    break;
                case 0:
                    Toast.makeText(ReviewSubmitActivity.this, "Update Fail ! : " + result.toString(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}