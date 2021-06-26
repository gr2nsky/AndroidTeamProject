package com.example.mogastyle.NetworkTasks.Diary;

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

public class DiaryUpdateDiaryPage extends AsyncTask<Integer, String, Integer> {
    Context context;
    String mAddr;
    ProgressDialog progressDialog;
    String devicePath;
    ImageView image_selected_detail_diary;
    String strNo;
    String date;
    String hairShop;
    String designerName;
    String comments;

    public DiaryUpdateDiaryPage(Context context, String mAddr, String devicePath, ImageView image_selected_detail_diary, String strNo, String date,String hairShop, String designerName, String comments) {
        this.context = context;
        this.mAddr = mAddr;
        this.devicePath = devicePath;
        this.image_selected_detail_diary = image_selected_detail_diary;
        this.strNo = strNo;
        this.date = date;
        this.hairShop = hairShop;
        this.designerName = designerName;
        this.comments = comments;
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
                .addFormDataPart("no" ,strNo)
                .addFormDataPart("date" , date)
                .addFormDataPart("hairShop",hairShop)
                .addFormDataPart("designerName",designerName)
                .addFormDataPart("comments",comments)
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
