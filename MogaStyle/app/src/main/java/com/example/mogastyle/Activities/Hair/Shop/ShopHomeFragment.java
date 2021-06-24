package com.example.mogastyle.Activities.Hair.Shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mogastyle.Adapters.Hair.Shop.ShopHomeAdapter;
import com.example.mogastyle.Bean.Shop;
import com.example.mogastyle.NetworkTasks.Hair.ShopNetworkTask;
import com.example.mogastyle.R;
import com.example.mogastyle.ShareVar.ShareVar;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ShopHomeFragment extends Fragment {
    int shopNo = 0;
    ImageView imageView;
   // EditText ShopName,ShopTel,ShopAddress,ShopPostcode,ShopIntroduction;
    EditText ShopName,ShopTel,ShopAddress,ShopIntroduction;
    String urlAddr = null;
    ArrayList<Shop> shops;
    String desktopIP = ShareVar.WindowIP;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_shop_home,container,false);
        shopNo = ShopHomeActivity.shopNo;

        urlAddr = ShareVar.hostRootAddr2;
        Log.v("Message",urlAddr);

        ShopName = view.findViewById(R.id.edt_shop_name);
        ShopTel = view.findViewById(R.id.edt_shop_tel);
        ShopAddress = view.findViewById(R.id.edt_shop_address);
     //   ShopPostcode = view.findViewById(R.id.shop_postcode);
        ShopIntroduction = view.findViewById(R.id.edt_shop_introduction);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        connectGetDate();
    }

    private void connectGetDate(){
            try{
                ShopNetworkTask networkTask = new ShopNetworkTask(getContext(), urlAddr, "select");
                Object obj = networkTask.execute().get();
                shops = (ArrayList<Shop>) obj;
                ShopName.setText(shops.get(0).getName());
                ShopTel.setText("tel : "+shops.get(1).getTel());
                ShopAddress.setText("주소 :"+shops.get(2).getAddress());
              //  ShopPostcode.setText("우편번호 : "+shops.get(3).getPostcode());
                ShopIntroduction.setText("소개"+shops.get(3).getIntroduction());
            }catch (Exception e){
                e.printStackTrace();
            }
    }

}