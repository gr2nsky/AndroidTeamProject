package com.example.mogastyle.Activities.Hair.Designer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mogastyle.Adapters.Hair.Styling.StylingRecyclerAdapter;
import com.example.mogastyle.Bean.Styling;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.NetworkTasks.Hair.Styling.StylingNetworkTask;
import com.example.mogastyle.R;

import java.util.ArrayList;

public class DesignerDetailPageStylingFragment extends Fragment {

    // 생성자
    public DesignerDetailPageStylingFragment() {
        Log.v("Message", "Tab1Fragment_Tab1Fragment");
    }

    // Field
    ArrayList<Styling> members;
    StylingRecyclerAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    // IPc
    String urlAddr = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v("Message", "onCreateView_fragment_styling");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_designer_detail_page_styling, container, false);

        recyclerView = view.findViewById(R.id.lv_designer_styling_list);


        urlAddr = ShareVar.hostRootAddr + "Hair/Styling/styling_query_all.jsp";

        // 아래는 내 톰캣 서버 : 위에 있는 Git폴더 경로로 하면 안 뜸
    //    urlAddr = "http://192.168.0.105:8080/test/" + "styling_query_all.jsp";

        connectGetData();

        return view; // 맨 마지막
    }

    // 네트워크 데이터를 타고 들어가서 검색하는 것
    private void connectGetData() {
        Log.v("Message", "DesignerDetailPageStylingFragment_connectGetData");
        try {
            // Data Setting
            StylingNetworkTask networkTask = new StylingNetworkTask(getActivity(), urlAddr, "select");
            Object obj = networkTask.execute().get();
            members = (ArrayList<Styling>) obj;

            // recycler view
            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);

            // 밑줄부터가 Task(JSON 가져옴)행동 끝나면 돌아오는 곳(여기서부터 못 불러오면 Adapter가 문제인 것임)
            Log.v("Message", members.get(0).toString());
            adapter = new StylingRecyclerAdapter(getActivity(), R.layout.list_item_styling_detail_view, members);
            recyclerView.setAdapter(adapter);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

} // ----------------------------------------------