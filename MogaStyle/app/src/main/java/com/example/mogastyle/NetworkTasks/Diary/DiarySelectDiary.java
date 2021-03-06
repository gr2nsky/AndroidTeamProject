package com.example.mogastyle.NetworkTasks.Diary;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.mogastyle.Bean.Diary.Diary;
import com.example.mogastyle.Bean.Diary.DiaryBookList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DiarySelectDiary extends AsyncTask<Integer , String, Object> {
    Context context;
    String mAddr ;
    String devicePath;
    ProgressDialog progressDialog = null;
    ArrayList<Diary> styles;


    public DiarySelectDiary(Context context, String mAddr) {
        this.context = context;
        this.mAddr = mAddr;
        this.devicePath = devicePath;
        this.styles = new ArrayList<Diary>();


    }
    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Dialog");
        progressDialog.setMessage("Get.....");
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        progressDialog.dismiss();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        progressDialog.dismiss();
    }



    @Override
    protected Object doInBackground(Integer... integers) {
        StringBuffer stringBuffer = new StringBuffer();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        String result = null;

        try{
            URL url = new URL(mAddr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(10000);

            if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader  = new BufferedReader(inputStreamReader);

                while(true){
                    String strLine = bufferedReader.readLine();
                    if(strLine == null ) break;
                    stringBuffer.append(strLine + "\n");
                }

                    parserSelect(stringBuffer.toString());

            }


        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(bufferedReader != null) bufferedReader.close();
                if(inputStreamReader != null) inputStreamReader.close();
                if(inputStream != null) inputStream.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }


            return styles;
    }


    private void parserSelect(String str){
        try{
            JSONObject jsonObject = new JSONObject(str);

            JSONArray jsonArray = new JSONArray(jsonObject.getString("diary_book"));
            styles.clear();

            for(int i= 0 ; i<jsonArray.length();i++){
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                int no = jsonObject1.getInt("no");
                String image = jsonObject1.getString("titleimage");
                String title = jsonObject1.getString("styletitle");
                String hairLength = jsonObject1.getString("stylelength");

                Diary style = new Diary(image,title,hairLength,no);
                styles.add(style);

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }



}