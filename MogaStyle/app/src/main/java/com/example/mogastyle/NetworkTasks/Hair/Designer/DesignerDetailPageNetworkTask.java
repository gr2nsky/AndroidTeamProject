package com.example.mogastyle.NetworkTasks.Hair.Designer;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.mogastyle.Bean.Designer;
import com.example.mogastyle.Common.ShareVar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DesignerDetailPageNetworkTask extends AsyncTask<Integer, String, Object> {

    // Field
    Context context = null;
    String mAddr = ShareVar.hostRootAddr;
    ProgressDialog progressDialog = null;
    ArrayList<Designer> designerList = null;     // select 할 때 쓸 ArrayList

    // Network Task를 검색, 입력, 수정, 삭제 구분 없이 하나로 사용하기 위해 생성자 변수 추가.
    String where = null;

    // Construct
    public DesignerDetailPageNetworkTask(Context context, String mAddr, String where) {
        this.context = context;
        this.mAddr = mAddr;
        this.designerList = new ArrayList<Designer>();
        this.where = where;
    }

    // progress 실행
    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Dialog");
        progressDialog.setMessage("Get....");
        progressDialog.show();
    }

    // progress 종료
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        progressDialog.dismiss();
    }

    // progress 취소
    @Override
    protected void onCancelled() {
        super.onCancelled();
        progressDialog.dismiss();
    }

    @Override
    protected Object doInBackground(Integer... integers) {
        Log.v("Message", "DesignerDetailPageNetworkTask_doInBackground");
        StringBuffer stringBuffer = new StringBuffer();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        String result = null;

        try {
            Log.v("Message", "DesignerDetailPageNetworkTask_mAddr : " + mAddr);
            URL url = new URL(mAddr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(10000);

            if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                Log.v("Message", "DesignerDetailPageNetworkTask_HTTP_OK: ");

                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);

                while (true) {
                    Log.v("Message", "DesignerDetailPageNetworkTask_while(true): ");

                    String strline = bufferedReader.readLine();
                    if(strline == null) break;
                    stringBuffer.append(strline + "\n");
                }

                if(where.equals("select")) {
                    Log.v("Message", "DesignerDetailPageNetworkTask_str: ");
                    parserSelect(stringBuffer.toString());
                }else {     // select 만 좀 다르기때문에 나눔@@@@@@
                    result = parserAction(stringBuffer.toString());     // 리턴값을 받아야함~
                }
            }

        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(bufferedReader != null) bufferedReader.close();
                if(inputStreamReader != null) inputStreamReader.close();
                if(inputStream != null) inputStream.close();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }

        if(where.equals("select")) {
            return designerList;
        }else {
            return result;
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

    // parsing이니깐..? Jason임..
    private void parserSelect(String str){
        Log.v("Message", "DesignerDetailPageNetworkTask_parserSelect_str: " + str);
        try {
            Log.v("Message", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@1");
            JSONObject jsonObject = new JSONObject(str);
            JSONArray jsonArray = new JSONArray(jsonObject.getString("designer_info")); // 얘는 데이터가 많기 때문에 Array로 가져오는 것임!
            designerList.clear(); // ArrayList 정리

            for(int i=0; i < jsonArray.length(); i++ ) {
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                String name = jsonObject1.getString("name");
                String certificationDate = jsonObject1.getString("certificationDate");
                String introduction = jsonObject1.getString("introduction");
                int sno = jsonObject1.getInt("sno");
                String image = jsonObject1.getString("image");

                // Bean으로 넣어서 ArrayList 추가
                Designer member = new Designer(name, certificationDate, introduction, sno, image);
                Log.v("Message", "networkTask designer : " + member.print());
                designerList.add(member);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }



} // NetworkTask

