package com.example.mogastyle.Activities.Hair.Designer;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mogastyle.Bean.Designer;
import com.example.mogastyle.Bean.Styling;
import com.example.mogastyle.R;

public class DesignerFragment extends Fragment {
    Context con;
    Designer DesignerBean = null;
    ListView listView;


    public DesignerFragment(Designer DesignerBean, Context con) {
        this.DesignerBean = DesignerBean;
        this.con = con;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_designer_list,container,false);

        listView = view.findViewById(R.id.lv_designer_list);





        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
} // -----------------------------------