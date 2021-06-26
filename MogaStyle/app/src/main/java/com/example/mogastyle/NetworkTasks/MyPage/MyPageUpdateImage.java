package com.example.mogastyle.NetworkTasks.MyPage;

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

public class MyPageUpdateImage extends AsyncTask<Integer, String, Integer> {

    private static final String TAG = "NETWORKTASK";
    Context context;
    String mAddr;
    ProgressDialog progressDialog;
    String devicePath;
    ImageView iv_my_page_update_image;

    String userNo;

    public MyPageUpdateImage(Context context, String mAddr, String devicePath, ImageView iv_my_page_update_image, String userNo) {
        this.context = context;
        this.mAddr = mAddr;
        this.devicePath = devicePath;
        this.iv_my_page_update_image = iv_my_page_update_image;
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
        Log.v(TAG, "file.getAbsolutePath() : " + file.getAbsolutePath());
        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)

                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                //
                //              RequestBody.create의 parameter의 순서가 바뀜
                //
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                .addFormDataPart("image", file.getName(), RequestBody.create(file, MediaType.parse("image/jpeg")))
                .addFormDataPart("userNo" ,userNo)
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                .build();

        Request request = new Request.Builder()
                .url(mAddr)
                .post(requestBody)
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            Log.v(TAG, "Success : " + response.body().string());
            return 1;
        }catch(IOException e){
            Log.v(TAG, "###IOException###");
            e.printStackTrace();
            return 0;
        }catch (Exception e){
            Log.v(TAG, "ERROR");
            e.printStackTrace();
            return 0;
        }
    }



}
