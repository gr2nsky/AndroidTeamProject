package com.example.mogastyle.NetworkTasks.Hair.Reservation;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ReviewUpload extends AsyncTask<Integer, String, Integer> {
    Context context;
    String mAddr;
    ProgressDialog progressDialog;
    String devicePath;

    ImageView imageView;
    String rno;
    String content;
    String rating;

    public ReviewUpload(Context context, String mAddr, String devicePath, ImageView imageView, String rno, String content, String rating) {
        this.context = context;
        this.mAddr = mAddr;
        this.devicePath = devicePath;
        this.imageView = imageView;
        this.rno = rno;
        this.content = content;
        this.rating = rating;
    }
    @Override
    protected Integer doInBackground(Integer... integers) {
        File file = new File(devicePath);
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", file.getName(), RequestBody.create(file, MediaType.parse("image/jpeg")))
                .addFormDataPart("rno" ,rno)
                .addFormDataPart("content", content)
                .addFormDataPart("rating",rating)
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