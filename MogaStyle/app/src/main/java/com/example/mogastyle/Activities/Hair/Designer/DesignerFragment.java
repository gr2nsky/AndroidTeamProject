package com.example.mogastyle.Activities.Hair.Designer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mogastyle.R;

public class DesignerFragment extends Fragment {

    public DesignerFragment() {
        // Required empty public constructor
    }


    public static DesignerFragment newInstance(String param1, String param2) {
        DesignerFragment fragment = new DesignerFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_designer_list, container, false);
    }
}