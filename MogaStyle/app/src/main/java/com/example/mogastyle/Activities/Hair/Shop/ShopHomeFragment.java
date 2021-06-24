package com.example.mogastyle.Activities.Hair.Shop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.mogastyle.Activities.Hair.HairMainActivity;
import com.example.mogastyle.Adapters.Hair.Shop.ShopListAdapter;
import com.example.mogastyle.Bean.Shop;
import com.example.mogastyle.NetworkTasks.Hair.ShopNetworkTask;
import com.example.mogastyle.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ShopHomeFragment extends Fragment {
    int shopNo = 0;
    ImageView imageView;
    EditText editText1,editText2,editText3,editText4,editText5,editText6;
    ShopListAdapter adapters = new ShopListAdapter();

    String urlAddr = null;
    ArrayList<Shop> shops;
    String desktopIP = "192.168.2.30";


    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shopNo = adapters.selectedShopNo();

        urlAddr = "http://192.168.2.30:8080/test/shop_select.jsp";
        Log.v("Message",urlAddr);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_shop_home,container,false);
        editText1 = getView().findViewById(R.id.name);
        editText2 = getView().findViewById(R.id.tel);
        editText3 = getView().findViewById(R.id.address);
        editText4 = getView().findViewById(R.id.rate);
        editText5 = getView().findViewById(R.id.review);
        editText6 = getView().findViewById(R.id.introduction);
        return view;
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
                editText1.setText(shops.get(0).getName());
                editText2.setText(shops.get(1).getTel());
            }catch (Exception e){
                e.printStackTrace();
            }
    }

}