package com.example.mogastyle.Activities.Hair;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.SyncStateContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mogastyle.R;

import net.daum.android.map.MapView;

public class HairMainFragment extends Fragment {
    Context mContext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.i("TAG", "MapFragment:onCreateView()");
        mContext = getActivity();

        View root = inflater.inflate(R.layout.fragment_hair_main, container, false);
        MapView mapView = new MapView(mContext);
        ViewGroup mapViewContainer = (ViewGroup)root.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
        return root;
    }

}