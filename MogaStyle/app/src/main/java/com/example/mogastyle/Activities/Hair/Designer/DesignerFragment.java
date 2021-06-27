package com.example.mogastyle.Activities.Hair.Designer;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mogastyle.Adapters.Hair.Designer.DesignerAdapter;
import com.example.mogastyle.Adapters.Review.ReviewAdapter;
import com.example.mogastyle.Bean.Designer;
import com.example.mogastyle.Bean.Styling;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.NetworkTasks.Hair.Designer.DesignerNetworkTask;
import com.example.mogastyle.R;

import java.util.ArrayList;

public class DesignerFragment extends Fragment {

    // Field
    ArrayList<Designer> members;
    DesignerAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    // IP
    String urlAddr = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_designer_list,container,false);

        recyclerView = view.findViewById(R.id.lv_designer_list);

        // 밑 2개 세트
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        urlAddr = ShareVar.hostRootAddr + "Hair/Designer/designer_query_all.jsp";

        connectGetData();

        return view; // 맨 마지막

    }

    // 네트워크 데이터를 타고 들어가서 검색하는 것
    private void connectGetData() {
        Log.v("Message", "DesignerListActivity_connectGetData");
        try {
            DesignerNetworkTask networkTask = new DesignerNetworkTask(getContext(), urlAddr, "select");
            Object obj = networkTask.execute().get();
            members = (ArrayList<Designer>) obj;

            // 밑줄부터가 Task(JSON 가져옴)행동 끝나면 돌아오는 곳(여기서부터 못 불러오면 Adapter가 문제인 것임)
            Log.v("Message", members.get(0).print());
            adapter = new DesignerAdapter(getContext(), R.layout.list_item_desiner_detail_view, members);
            recyclerView.setAdapter(adapter);

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }



} // -----------------------------------