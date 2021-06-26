package com.example.mogastyle.NetworkTasks.Diary;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DiaryUpdateDiary extends AsyncTask<Integer, String, Integer> {

    Context context;
    String mAddr;
    ProgressDialog progressDialog;
    String devicePath;
    ImageView iv_my_page_update_image;
    String no;
    String updateTitle;
    String hairLength;

//    DiaryUpdateDiary diaryUpdateDiary = new DiaryUpdateDiary(DiaryEditStyleActivity.this , urlAddr + "MyPage/MyPageUpdateImage.jsp", img_path, selectedImageEdit, no , updateTitle, hairLength);


    public DiaryUpdateDiary(Context context, String mAddr, String devicePath, ImageView iv_my_page_update_image, String no, String updateTitle, String hairLength) {
        this.context = context;
        this.mAddr = mAddr;
        this.devicePath = devicePath;
        this.iv_my_page_update_image = iv_my_page_update_image;
        this.no = no;
        this.updateTitle = updateTitle;
        this.hairLength = hairLength;
    }

    @Override
    protected void onPreExecute() {

        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Status");
        progressDialog.setMessage("Uploading ....");
        progressDialog.show();

    }

    @Override
    protected void onProgressUpdate(String... values) {

        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Integer integer) {

        progressDialog.dismiss();

    }

    @Override
    protected void onCancelled() {

        super.onCancelled();
    }

    @Override
    protected Integer doInBackground(Integer... integers) {
        File file = new File(devicePath);

        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
//    DiaryUpdateDiary diaryUpdateDiary = new DiaryUpdateDiary(DiaryEditStyleActivity.this , urlAddr + "MyPage/MyPageUpdateImage.jsp", img_path, selectedImageEdit, no , updateTitle, hairLength);
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                //
                //              RequestBody.create의 parameter의 순서가 바뀜
                //
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                .addFormDataPart("image", file.getName(), RequestBody.create(file, MediaType.parse("image/jpeg")))
                .addFormDataPart("no" ,no)
                .addFormDataPart("title" , updateTitle)
                .addFormDataPart("hairLength",hairLength)
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                .build();

        Request request = new Request.Builder()
                .url(mAddr)
                .post(requestBody)
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();

            return 1;
        }catch(IOException e){

            e.printStackTrace();
            return 0;
        }catch (Exception e){

            e.printStackTrace();
            return 0;
        }
    }



}
