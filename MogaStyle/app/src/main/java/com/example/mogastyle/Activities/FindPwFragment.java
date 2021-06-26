package com.example.mogastyle.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mogastyle.Activities.Home.HomeFragment;
import com.example.mogastyle.Activities.MyPage.MyPageMainActivity;
import com.example.mogastyle.Activities.MyPage.MyPageUpdatePwActivity;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.NetworkTasks.Home.FindPwUpdatePw;
import com.example.mogastyle.NetworkTasks.MyPage.MyPageUpdatePw;
import com.example.mogastyle.R;


public class FindPwFragment extends Fragment {
    TextView tv_find_pw_get_id ,tv_reset_pw_check;

    EditText et_find_pw_update_pw1,et_find_pw_update_pw2;

    Button btn_find_pw_update_pw;

    private String userId;

    String urlAddr = ShareVar.hostRootAddr;
    public static FindPwFragment newInstance(String userId) {
        FindPwFragment fragment = new FindPwFragment();
        Bundle args = new Bundle();
        args.putString("userId" , userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userId = getArguments().getString("userId");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_find_pw, container, false);

        userId = getArguments().getString("userId");

        tv_find_pw_get_id = view.findViewById(R.id.tv_find_pw_get_id);
        tv_find_pw_get_id.setText(getArguments().getString("userId"));

        tv_reset_pw_check = view.findViewById(R.id.tv_reset_pw_check);

        et_find_pw_update_pw1 = view.findViewById(R.id.et_find_pw_update_pw1);
        et_find_pw_update_pw2 = view.findViewById(R.id.et_find_pw_update_pw2);

        btn_find_pw_update_pw = view.findViewById(R.id.btn_find_pw_update_pw);

        btn_find_pw_update_pw.setEnabled(false);


        et_find_pw_update_pw2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(et_find_pw_update_pw1.getText().toString().equals(et_find_pw_update_pw2.getText().toString()) ){
                    tv_reset_pw_check.setText("Correct");
                    tv_reset_pw_check.setTextColor(Color.parseColor("#00ff00"));
                }else{
                    tv_reset_pw_check.setText("InCorrect!");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(tv_reset_pw_check.getText().equals("Correct")){
                    btn_find_pw_update_pw.setEnabled(true);
                }
            }
        });


        btn_find_pw_update_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pw1 = et_find_pw_update_pw1.getText().toString();

                FindPwUpdatePw findPwUpdatePw = new FindPwUpdatePw(getActivity() ,urlAddr+"Home/FindPwUpdatePw.jsp" , pw1 , userId );

                Object object = null;
                try {
                    object = findPwUpdatePw.execute().get();
                }catch(Exception e){
                    e.printStackTrace();
                }

                String updateResult = (String) object;

                if(updateResult.equals("1")){
                    Toast.makeText(getActivity(), "업데이트 완료! 로그인창으로 이동!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent = new Intent(getActivity() , LoginBasicActivity.class);
                    // 업데이트 완료시 sharedPreference 지움


                    //
                    startActivity(intent);

                }else{
                    Toast.makeText(getActivity(), "업데이트 오류!", Toast.LENGTH_SHORT).show();
                }


            }
        });


        return view;
    }
}