package com.example.mogastyle.Activities.Hair.Designer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mogastyle.Adapters.Hair.Designer.DesignerAdapter;
import com.example.mogastyle.Bean.Designer;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.NetworkTasks.Hair.Designer.DesignerNetworkTask;
import com.example.mogastyle.R;

import java.util.ArrayList;

public class DesignerListActivity extends AppCompatActivity {

    // Field
    ArrayList<Designer> members;
    DesignerAdapter adapter;
    ListView listView;

    // IP
    String urlAddr = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("Message", "DesignerListActivity_onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer_list);

        listView = findViewById(R.id.lv_designer_list);

        urlAddr = ShareVar.hostRootAddr + "Hair/Designer/designer_query_all.jsp";

        // 아래는 내 톰캣 서버 : 위에 있는 Git폴더 경로로 하면 안 뜸
//        urlAddr = "http://192.168.0.105:8080/test/" + "designer_query_all.jsp";

    }

    // onResume
    @Override
    protected void onResume() {
        super.onResume();
        connectGetData();
    }

    // 네트워크 데이터를 타고 들어가서 검색하는 것
    private void connectGetData() {
        Log.v("Message", "DesignerListActivity_connectGetData");
        try {
            DesignerNetworkTask networkTask = new DesignerNetworkTask(DesignerListActivity.this, urlAddr, "select");
            Object obj = networkTask.execute().get();
            members = (ArrayList<Designer>) obj;
            
            // 밑줄부터가 Task(JSON 가져옴)행동 끝나면 돌아오는 곳(여기서부터 못 불러오면 Adapter가 문제인 것임)
            Log.v("Message", members.get(0).toString());
            adapter = new DesignerAdapter(DesignerListActivity.this, R.layout.list_item_desiner_detail_view, members);
            listView.setAdapter(adapter);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.v("Message", "click");
        }
    };

} // -----------------------------------