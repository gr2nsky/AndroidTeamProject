package com.example.mogastyle.Activities.Home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mogastyle.Activities.MyPage.MyPageMainActivity;
import com.example.mogastyle.Activities.MyPage.MyPageUpdatePwActivity;
import com.example.mogastyle.Common.LoginedUserInfo;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.NetworkTasks.Home.UserHairTypeUpdate;
import com.example.mogastyle.R;

import org.jetbrains.annotations.NotNull;

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

    //
    String updateResult = "";
    String urlAddr = ShareVar.hostRootAddr ;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEdit;

    public static HomeHairTypeFragment newInstance(int page, String title , String userName) {
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
    View homeHairType =  inflater.inflate(R.layout.fragment_home_hair_type, container, false);

    //==SeekBar
    sb_home_hair_thickness = homeHairType.findViewById(R.id.sb_home_hair_thickness);
    sb_home_hair_sparsely = homeHairType.findViewById(R.id.sb_home_hair_sparsely);
    sb_home_hair_curledness = homeHairType.findViewById(R.id.sb_home_hair_curledness);

//    String uHT = sharedPreferences.getString("uHT" , "1");
//    String uHS = sharedPreferences.getString("uHS" , "1");
//    String uHC = sharedPreferences.getString("uHC" , "1");
//
//    int userHT = Integer.parseInt(uHT);
//    int userHS = Integer.parseInt(uHS);
//    int userHC = Integer.parseInt(uHC);
//
//    sb_home_hair_thickness.setProgress(userHT);
//    sb_home_hair_sparsely.setProgress(userHS);
//    sb_home_hair_curledness.setProgress(userHC);

    sb_home_hair_thickness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { usrHairThickness = seekBar.getProgress();}

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) { usrHairThickness = seekBar.getProgress(); }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            usrHairThickness = seekBar.getProgress();
        }
    });

    sb_home_hair_sparsely.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {usrHairSparsely = seekBar.getProgress(); }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) { usrHairSparsely = seekBar.getProgress();}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            usrHairSparsely = seekBar.getProgress();
        }
    });
    sb_home_hair_curledness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { usrHairCurledness = seekBar.getProgress();}

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) { usrHairCurledness = seekBar.getProgress();}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            usrHairCurledness = seekBar.getProgress();
        }
    });

    //==Button
    btn_home_update_hair_type = homeHairType.findViewById(R.id.btn_home_update_hair_type);
    btn_home_update_hair_type.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String uHT = Integer.toString(usrHairThickness);
            String uHS = Integer.toString(usrHairSparsely);
            String uHC = Integer.toString(usrHairCurledness);
            Toast.makeText(getContext(), uHT + "," + uHS + "," + uHC, Toast.LENGTH_SHORT).show();


            UserHairTypeUpdate userHairTypeUpdate = new UserHairTypeUpdate(getActivity() , urlAddr + "Home/userHairTypeUpdate.jsp" , uHT +uHS +uHC , Integer.toString(LoginedUserInfo.user.getNo()));

            Object object = null;
            try {
                object = userHairTypeUpdate.execute().get();
            }catch(Exception e){
                e.printStackTrace();
                Log.v("SHIT" ,  e.toString());
            }

            updateResult = (String) object;

            if(updateResult.equals("1")){
                Toast.makeText(getContext(), "업데이트 완료!", Toast.LENGTH_SHORT).show();

                sharedPreferencesEdit = sharedPreferences.edit();
                sharedPreferencesEdit.putString("uHT", uHT);
                sharedPreferencesEdit.putString("uHS", uHS);
                sharedPreferencesEdit.putString("uHC", uHC);
                sharedPreferencesEdit.commit();

            }else{
                Toast.makeText(getContext(), "업데이트 오류!", Toast.LENGTH_SHORT).show();
            }


        }
    });



    return homeHairType;
}

}

