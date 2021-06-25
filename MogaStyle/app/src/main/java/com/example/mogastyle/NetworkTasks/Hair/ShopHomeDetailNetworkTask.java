package com.example.mogastyle.NetworkTasks.Hair;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.mogastyle.Bean.Shop;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ShopHomeDetailNetworkTask extends AsyncTask<Integer,String,Object> {
    Context context = null;
    String mAddr = null;
    ProgressDialog progressDialog = null;
    ArrayList<Shop> shops;
    String where = null;

    public ShopHomeDetailNetworkTask(Context context, String mAddr, String where) {
        this.context = context;
        this.mAddr = mAddr;
        this.shops = shops;
        this.shops = new ArrayList<Shop>();
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
            return shops;
        }else {
            return result;
        }
    }
    private void parserSelect(String str) {
        try{
            JSONObject jsonObject = new JSONObject(str);
            JSONArray jsonArray = new JSONArray(jsonObject.getString("shoplist_info"));
            shops.clear(); //기존에 쌓일 수 있는 데이터를 삭제함

            for (int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                String name = jsonObject1.getString("name");
                Log.v("Status", "name : "+name);
                String tel = jsonObject1.getString("tel");
                Log.v("Status", "tel : "+tel);
                String address = jsonObject1.getString("address");
                Log.v("Status", "address : "+address);
                String postcode = jsonObject1.getString("postCode");
                Log.v("Status", "postCode : "+postcode);
                String introduction = jsonObject1.getString("introduction");
                Log.v("Status","introduction: " +introduction);
                String holiday = jsonObject1.getString("holiday");
                Log.v("Status","holiday: " +holiday);
                String image = jsonObject1.getString("image");
                Log.v("Status","image : " + image);
                int no = jsonObject1.getInt("no");
                Log.v("Status", "no : "+no);


                Shop shop = new Shop(no,name,tel,address,postcode,introduction,holiday,image);
                shops.add(shop);
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
