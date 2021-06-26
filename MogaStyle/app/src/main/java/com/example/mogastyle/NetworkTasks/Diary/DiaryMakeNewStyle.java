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

public class DiaryMakeNewStyle extends AsyncTask<Integer, String, Integer> {
    Context context;
    String mAddr;
    ProgressDialog progressDialog;
    String devicePath;
    ImageView iv_diary_make_new_diary_selectedImage;
    String title;
    String hairLength;
    String userNo;

    public DiaryMakeNewStyle(Context context, ImageView iv_diary_make_new_diary_selectedImage , String mAddr, String devicePath, String title, String hairLength, String userNo) {
        this.context = context;
        this.mAddr = mAddr;
        this.devicePath = devicePath;
        this.title = title;
        this.iv_diary_make_new_diary_selectedImage = iv_diary_make_new_diary_selectedImage;
        this.hairLength = hairLength;
        this.userNo = userNo;
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

                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                //
                //              RequestBody.create의 parameter의 순서가 바뀜
                //
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                .addFormDataPart("image", file.getName(), RequestBody.create(file, MediaType.parse("image/jpeg")))
                .addFormDataPart("title" ,title)
                .addFormDataPart("hairLength" ,hairLength)
                .addFormDataPart("userNo", userNo)
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

