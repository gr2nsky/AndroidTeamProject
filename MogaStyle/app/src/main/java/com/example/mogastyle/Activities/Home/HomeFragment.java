package com.example.mogastyle.Activities.Home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mogastyle.Activities.Hair.Reservation.ReservationCheckActivity;
import com.example.mogastyle.Activities.MainActivity;
import com.example.mogastyle.Common.LoginedUserInfo;
import com.example.mogastyle.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private String title , userName;
    private int page;


    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(int page, String title , String userName) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        args.putString("UserName" , userName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt" , 0);
        title = getArguments().getString("someTitle");
        userName = getArguments().getString("UserName");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View Home =  inflater.inflate(R.layout.fragment_home, container, false);

        //--환영 인사
        TextView tv_home_login_status = Home.findViewById(R.id.tv_home_login_status);
        tv_home_login_status.setText(LoginedUserInfo.user.getName() + " 님 환영합니다!");
        //--환영 인사 end

        Button btn_home_goReservation = Home.findViewById(R.id.btn_home_goReservation);
        btn_home_goReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReservationCheckActivity.class);
                startActivity(intent);
            }
        });

        return Home;
    }
}