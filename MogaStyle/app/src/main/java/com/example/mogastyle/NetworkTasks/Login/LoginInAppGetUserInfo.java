package com.example.mogastyle.NetworkTasks.Login;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.mogastyle.Bean.User;
import com.example.mogastyle.Common.LoginedUserInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class LoginInAppGetUserInfo extends AsyncTask<Integer , String , Object> {
    Context context;
    String mAddr;
    ProgressDialog progressDialog;
    ArrayList<User> userInfo;
    String userNo;

    public LoginInAppGetUserInfo(Context context, String mAddr, String userNo) {
        this.context = context;
        this.mAddr = mAddr;
        this.userInfo = new ArrayList<User>();
        this.userNo = userNo;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Dialog");
        progressDialog.setMessage("get Info...");
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

        try{
            URL url = new URL(mAddr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setRequestProperty("Content-Type" , "application/x-www-form-urlencoded");
            httpURLConnection.setRequestMethod("POST");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());

            String sendMSG = "userNo="+ userNo;
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
                JSONArray jsonArray = new JSONArray(jsonObject.getString("user_info"));
                userInfo.clear();

                JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);
                String userNo = jsonObject1.getString("no");
                String userId = jsonObject1.getString("id");
                String userName = jsonObject1.getString("name");
                String userImage = jsonObject1.getString("image");
                String userPhone = jsonObject1.getString("phone");
                String userJoinType = jsonObject1.getString("joinType");
                String userCheck = jsonObject1.getString("userCheck");
                User user = new User(Integer.parseInt(userNo),userId,userName,userImage,userPhone,userJoinType,userCheck);
                userInfo.add(user);


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

        return userInfo;
    }
}