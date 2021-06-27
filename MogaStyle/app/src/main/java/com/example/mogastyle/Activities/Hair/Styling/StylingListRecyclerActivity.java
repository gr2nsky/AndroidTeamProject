package com.example.mogastyle.Activities.Hair.Styling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.mogastyle.Adapters.Hair.Styling.StylingRecyclerAdapter;
import com.example.mogastyle.Bean.Styling;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.NetworkTasks.Hair.Styling.StylingNetworkTask;
import com.example.mogastyle.R;

import java.util.ArrayList;

public class StylingListRecyclerActivity extends AppCompatActivity {

    // Field
    ArrayList<Styling> members;
    StylingRecyclerAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    // IP
    String urlAddr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("Message", "StylingListRecyclerActivity_onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_styling_list_recycler);

        recyclerView = findViewById(R.id.lv_styling_list);

        // 밑 2개 세트
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        urlAddr = ShareVar.hostRootAddr + "Hair/Styling/styling_query_all.jsp";

 // 아래는 내 톰캣 서버 : 위에 있는 Git폴더 경로로 하면 안 뜸
 //       urlAddr = "http://192.168.0.105:8080/test/" + "styling_query_all.jsp";


    }

    // onResume
    @Override
    protected void onResume() {
        super.onResume();
        connectGetData();
    }

    // 네트워크 데이터를 타고 들어가서 검색하는 것
    private void connectGetData() {
        Log.v("Message", "StylingListRecyclerActivity_connectGetData");
        try {
            // Data Setting
            StylingNetworkTask networkTask = new StylingNetworkTask(StylingListRecyclerActivity.this, urlAddr, "select");
            Object obj = networkTask.execute().get();
            members = (ArrayList<Styling>) obj;

            // 밑줄부터가 Task(JSON 가져옴)행동 끝나면 돌아오는 곳(여기서부터 못 불러오면 Adapter가 문제인 것임)
            Log.v("Message", members.get(0).toString());
            adapter = new StylingRecyclerAdapter(StylingListRecyclerActivity.this, R.layout.list_item_styling_detail_view, members);
            recyclerView.setAdapter(adapter);


        }catch (Exception e){
            e.printStackTrace();
        }
    }



} // ---------------------------------------