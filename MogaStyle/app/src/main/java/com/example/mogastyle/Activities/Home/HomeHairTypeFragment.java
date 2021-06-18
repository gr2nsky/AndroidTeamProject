package com.example.mogastyle.Activities.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.mogastyle.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeHairTypeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeHairTypeFragment extends Fragment {

    private String title;
    private int page;


    //==
    Button btn_home_update_hair_type;
    SeekBar sb_home_hair_thickness, sb_home_hair_sparsely ,sb_home_hair_curledness;
    //==

    //==
    int usrHairThickness;
    int usrHairSparsely;
    int usrHairCurledness;

public static HomeHairTypeFragment newInstance(int page, String title) {
    HomeHairTypeFragment fragment = new HomeHairTypeFragment();
    Bundle args = new Bundle();
    args.putInt("someInt", page);
    args.putString("someTitle", title);
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
    View homeReservation =  inflater.inflate(R.layout.fragment_home_hair_type, container, false);

    //==SeekBar
    sb_home_hair_thickness = homeReservation.findViewById(R.id.sb_home_hair_thickness);
    sb_home_hair_sparsely = homeReservation.findViewById(R.id.sb_home_hair_sparsely);
    sb_home_hair_curledness = homeReservation.findViewById(R.id.sb_home_hair_curledness);

    sb_home_hair_thickness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) { }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            usrHairThickness = seekBar.getProgress();
        }
    });

    sb_home_hair_sparsely.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) { }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            usrHairSparsely = seekBar.getProgress();
        }
    });
    sb_home_hair_curledness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) { }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            usrHairCurledness = seekBar.getProgress();
        }
    });

    //==Button
    btn_home_update_hair_type = homeReservation.findViewById(R.id.btn_home_update_hair_type);
    btn_home_update_hair_type.setOnClickListener(onClickListener);



    return homeReservation;
}



    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getContext(), usrHairThickness + "," + usrHairSparsely +","+usrHairCurledness , Toast.LENGTH_SHORT).show();
        }
    };

}