package com.example.mogastyle.Activities.MyPage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.example.mogastyle.R;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiarySettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiarySettingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    SwitchMaterial switch_diary_setting;

    LinearLayout linear_layout_diary_setting_radio;

    MaterialRadioButton rb_diary_setting_1,rb_diary_setting_2,rb_diary_setting_3;


    public DiarySettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiarySettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiarySettingFragment newInstance(String param1, String param2) {
        DiarySettingFragment fragment = new DiarySettingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diary_setting, container, false);
        switch_diary_setting = view.findViewById(R.id.switch_diary_setting);
        linear_layout_diary_setting_radio =  view.findViewById(R.id.linear_layout_diary_setting_radio);
        rb_diary_setting_1 = view.findViewById(R.id.rb_diary_setting_1);
        rb_diary_setting_2 = view.findViewById(R.id.rb_diary_setting_2);
        rb_diary_setting_3 = view.findViewById(R.id.rb_diary_setting_3);

        if(!switch_diary_setting.isChecked()){
            linear_layout_diary_setting_radio.setVisibility(View.GONE);
        }

        switch_diary_setting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {

                    linear_layout_diary_setting_radio.setVisibility(View.VISIBLE);
                }else {
                    linear_layout_diary_setting_radio.setVisibility(View.GONE);
                }
            }
        });



        return view;
    }
}