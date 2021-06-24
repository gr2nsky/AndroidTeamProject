package com.example.mogastyle.NetworkTasks.Hair;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.mogastyle.Bean.Shop;
import com.example.mogastyle.Bean.Styling;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class StyleDetailNetworkTask extends AsyncTask<Integer,String,Object> {
    Context context = null;
    String mAddr = null;
    ProgressDialog progressDialog = null;
    ArrayList<Styling> stylings;
    //NetworkTask를 검색, 입력, 수정, 삭제 구분 없이 하나로 사용하기 위해 생성자 변수 추가
    String where = null;

    public StyleDetailNetworkTask(Context context, String mAddr, String where) {
        this.context = context;
        this.mAddr = mAddr;
        this.stylings = stylings;
        this.stylings = new ArrayList<Styling>();
        this.where = where;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
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

        String result = null; //DB 연결이 잘 되었는지 받는 용도

        try {
            URL url = new URL(mAddr);
            Log.v("MessageHi", url.toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(10000); //10초 동안 연결 시도하다가 실패시 에러

            if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);

                while (true) {
                    String strline = bufferedReader.readLine();
                    if(strline == null) break;
                    stringBuffer.append(strline + "\n");
                }
                if (where.equals("select")) {
                    parserSelect(stringBuffer.toString());
                }else{
                    Log.v("MessageHi", stringBuffer.toString());
                    result = parserAction(stringBuffer.toString());
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
                if (inputStreamReader != null) inputStreamReader.close();
                if (inputStream != null) inputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if (where.equals("select")){
            return stylings;
        }else {
            return result;
        }
    }

    private void parserSelect(String str) {
        try{
            JSONObject jsonObject = new JSONObject(str);
            JSONArray jsonArray = new JSONArray(jsonObject.getString("shop_info"));
            stylings.clear(); //기존에 쌓일 수 있는 데이터를 삭제함

            for (int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                String typecode = jsonObject1.getString("typeCode");
                Log.v("Status", "typeCode : "+typecode);
                String submitDate = jsonObject1.getString("submitDate");
                Log.v("Status", "submitDate : "+submitDate);
                String deleteDate = jsonObject1.getString("deleteDate");
                Log.v("Status", "deleteDate : "+deleteDate);
                String title = jsonObject1.getString("title");
                Log.v("Status", "title : "+title);
                String content = jsonObject1.getString("content");
                Log.v("Status","content : " +content);
                String gender = jsonObject1.getString("gender");
                Log.v("Status","gender: " + gender);
                int price = jsonObject1.getInt("price");
                Log.v("Status","price : " + price);
                String leadTime = jsonObject1.getString("leadTime");
                Log.v("Status","leadTime : " + leadTime);
                int no = jsonObject1.getInt("no");
                Log.v("Status", "no : "+no);


                Styling styling = new Styling(no,typecode,submitDate,deleteDate,title,content,gender,price,leadTime,no);
                stylings.add(styling);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String parserAction(String str) {
        String returnValue = null;
        try{
            JSONObject jsonObject = new JSONObject(str);
            returnValue = jsonObject.getString("result");
        }catch (Exception e){
            e.printStackTrace();
        }
        return returnValue;
    }

}
