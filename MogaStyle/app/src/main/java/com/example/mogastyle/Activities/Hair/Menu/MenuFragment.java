package com.example.mogastyle.Activities.Hair.Menu;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mogastyle.Activities.Hair.Styling.StylingListRecyclerActivity;
import com.example.mogastyle.Adapters.Hair.Styling.StylingRecyclerAdapter;
import com.example.mogastyle.Bean.Shop;
import com.example.mogastyle.Bean.Styling;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.NetworkTasks.Hair.Styling.StylingNetworkTask;
import com.example.mogastyle.R;

import java.util.ArrayList;

public class MenuFragment extends Fragment {

    ArrayList<Styling> members;
    StylingRecyclerAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    // IP
    String urlAddr = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_styling_list_recycler,container,false);

        recyclerView = view.findViewById(R.id.lv_styling_list);

        // 밑 2개 세트
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        urlAddr = ShareVar.hostRootAddr + "Hair/Styling/styling_query_all.jsp";

        connectGetData();

        return view;
    }


    // 네트워크 데이터를 타고 들어가서 검색하는 것
    private void connectGetData() {
        Log.v("Message", "StylingListRecyclerActivity_connectGetData");
        try {
            // Data Setting
            StylingNetworkTask networkTask = new StylingNetworkTask(getContext(), urlAddr, "select");
            Object obj = networkTask.execute().get();
            members = (ArrayList<Styling>) obj;

            // 밑줄부터가 Task(JSON 가져옴)행동 끝나면 돌아오는 곳(여기서부터 못 불러오면 Adapter가 문제인 것임)
            Log.v("Message", members.get(0).toString());
            adapter = new StylingRecyclerAdapter(getContext(), R.layout.list_item_styling_detail_view, members);
            recyclerView.setAdapter(adapter);


        }catch (Exception e){
            e.printStackTrace();
        }
    }






















    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }










} // ----------------------------------------