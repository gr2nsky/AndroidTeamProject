package com.example.mogastyle.Activities.Hair.Shop;

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

import com.example.mogastyle.NetworkTasks.Hair.ShopNetworkTask;
import com.example.mogastyle.R;

import org.jetbrains.annotations.NotNull;

public class ShopHomeFragment extends Fragment {
    ImageView imageView;
    EditText title,tel,address,rate,review,introduction;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_shop_home,container,false);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        connectGetDate();
    }

    private void connectGetDate(){
        try {
            title = getView().findViewById(R.id.shopname);
            tel = getView().findViewById(R.id.tel);
            address = getView().findViewById(R.id.address);
            rate = getView().findViewById(R.id.rate);
            review = getView().findViewById(R.id.review);
            introduction = getView().findViewById(R.id.introduction);
        }catch (Exception e){

        }
    }



}