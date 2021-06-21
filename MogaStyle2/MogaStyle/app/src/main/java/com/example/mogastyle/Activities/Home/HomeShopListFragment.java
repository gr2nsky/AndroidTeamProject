package com.example.mogastyle.Activities.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.mogastyle.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeShopListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeShopListFragment extends Fragment {

    private String title;
    private int page;

    public static HomeShopListFragment newInstance(int page, String title) {
        HomeShopListFragment fragment = new HomeShopListFragment();
        Bundle args = new Bundle();
        args.putInt("someInt",page);
        args.putString("someTitle" , title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt" , 0);
        title = getArguments().getString("someTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View HomeShopList = inflater.inflate(R.layout.fragment_home_shop_list, container, false);

        return HomeShopList;
    }
}