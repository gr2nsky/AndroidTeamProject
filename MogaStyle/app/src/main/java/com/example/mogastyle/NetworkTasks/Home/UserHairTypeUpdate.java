package com.example.mogastyle.NetworkTasks.Home;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserHairTypeUpdate extends AsyncTask<Integer , String, Object> {

    Context context;
    String mAddr;

    String userHairType, userNo;

    public UserHairTypeUpdate(Context context, String mAddr, String userHairType,String userNo) {
        this.context = context;
        this.mAddr = mAddr;
        this.userHairType = userHairType;
        this.userNo = userNo;
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
            httpURLConnection.setRequestProperty("Content-Type" , "application/x-www-form-urlencoded");
            httpURLConnection.setRequestMethod("POST");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());

            String sendMSG = "userHairType="+userHairType +"&userNo="+userNo;
            outputStreamWriter.write(sendMSG);
            outputStreamWriter.flush();

            if (httpURLConnection.getResponseCode() == httpURLConnection.HTTP_OK){

                inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream(),"UTF-8");
                bufferedReader = new BufferedReader(inputStreamReader);


                while (true) {
                    String str = bufferedReader.readLine();
                    if (str == null) break;
                    stringBuffer.append(str + "\n");
                }

                JSONObject jsonObject = new JSONObject(stringBuffer.toString());
                result = jsonObject.getString("result");

            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
                if (inputStreamReader != null) inputStreamReader.close();
                if (inputStream != null) inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

}

