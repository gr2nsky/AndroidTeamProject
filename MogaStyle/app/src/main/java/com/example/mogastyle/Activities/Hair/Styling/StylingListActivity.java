package com.example.mogastyle.Activities.Hair.Styling;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mogastyle.Adapters.Hair.Styling.StylingListAdapter;
import com.example.mogastyle.Bean.Styling;
import com.example.mogastyle.NetworkTasks.Hair.Styling.StylingNetworkTask;
import com.example.mogastyle.R;

import java.util.ArrayList;

public class StylingListActivity extends AppCompatActivity {

    // Field
    ArrayList<Styling> members;
    StylingListAdapter adapter;
    ListView listView;

    String windowIP; // 메인에 구성 url을 대신에 다른것


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("Message", "StylingListActivity_onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_styling_list);

        listView = findViewById(R.id.lv_styling_list);

        Intent intent = getIntent();
        windowIP = intent.getStringExtra("hostRootAddr");
    }

    // onResume
    @Override
    protected void onResume() {
        super.onResume();
        connectGetData();
    }

    // 네트워크 데이터를 타고 들어가서 검색하는 것
    private void connectGetData() {
        Log.v("Message", "StylingListActivity_connectGetData");
        try {
            StylingNetworkTask networkTask = new StylingNetworkTask(StylingListActivity.this, windowIP, "select");
            Object obj = networkTask.execute().get();
            members = (ArrayList<Styling>) obj;

            // 밑줄부터가 Task(JSON 가져옴)행동 끝나면 돌아오는 곳(여기서부터 못 불러오면 Adapter가 문제인 것임)
            Log.v("Message", members.get(0).toString());
            adapter = new StylingListAdapter(StylingListActivity.this, R.layout.list_item_styling_detail_view, members);
            listView.setAdapter(adapter);


        }catch (Exception e){
            e.printStackTrace();
        }
    }




} // -----------------------------------