package com.example.mogastyle.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mogastyle.Common.LoginedUserInfo;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.NetworkTasks.Home.LoginFindId;
import com.example.mogastyle.NetworkTasks.Home.MainGetPhoneUpdate;
import com.example.mogastyle.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FindIdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FindIdFragment extends Fragment {

    EditText et_find_id_check_name , et_find_id_check_phone , et_find_id_check_token;

    Button btn_find_id_send_token , btn_find_id_final;
    private PhoneAuthProvider.ForceResendingToken forceResendingToken;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks;
    FirebaseAuth firebaseAuth;
    String mVerificationId ;

    String urlAddr = ShareVar.hostRootAddr ;

    String userPhone;
    String userName;
    String userToken;

    String findResult;

    public static FindIdFragment newInstance(String param1, String param2) {
        FindIdFragment fragment = new FindIdFragment();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_find_id, container, false);

        et_find_id_check_name = view.findViewById(R.id.et_find_id_check_name);
        et_find_id_check_phone = view.findViewById(R.id.et_find_id_check_phone);
        et_find_id_check_token = view.findViewById(R.id.et_find_id_check_token);


        btn_find_id_send_token = view.findViewById(R.id.btn_find_id_send_token);
        btn_find_id_final = view.findViewById(R.id.btn_find_id_final);

        //
        btn_find_id_send_token.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPhone = et_find_id_check_phone.getText().toString();

                if(TextUtils.isEmpty(userPhone)){
                    Toast.makeText(getActivity(), "번호를 입력해 주세요!", Toast.LENGTH_SHORT).show();
                }else{
                    userPhone = et_find_id_check_phone.getText().toString();
                    startPhoneNumberVerification(userPhone);

                }

            }
        });

        btn_find_id_final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userToken = et_find_id_check_token.getText().toString();
                if(TextUtils.isEmpty(userToken)){
                    Toast.makeText(getContext(), "인증 번호를 입력해주세요!", Toast.LENGTH_SHORT).show();
                }else{

                    verifyPhoneNumberWithCode(mVerificationId ,userToken);
                }

            }
        });
        //

        //firebase

        firebaseAuth = FirebaseAuth.getInstance();

        mCallBacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull @NotNull PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull @NotNull FirebaseException e) {

            }

            @Override
            public void onCodeSent(@NonNull @NotNull String verificationId, @NonNull @NotNull PhoneAuthProvider.ForceResendingToken token) {
                super.onCodeSent(verificationId, forceResendingToken);

                Log.d("TAG" , "onCodeSending" + verificationId);
                mVerificationId = verificationId;
                forceResendingToken = token;


            }
        };

        //

        return view;
    }

    //
    private void startPhoneNumberVerification(String phone) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phone)
                        .setTimeout(60L , TimeUnit.SECONDS)
                        .setActivity(getActivity())
                        .setCallbacks(mCallBacks)
                        .build();

        PhoneAuthProvider.verifyPhoneNumber(options);

    }
    private void verifyPhoneNumberWithCode(String verificationId, String userToken) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId,userToken);
        signInWithPhoneAuthCredential(credential);
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        firebaseAuth.signInWithCredential(credential)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        String phone = firebaseAuth.getCurrentUser().getPhoneNumber();
                        String userName = et_find_id_check_name.getText().toString();
                        // ID 값 들고 오
                        LoginFindId loginFindId = new LoginFindId(getActivity(),urlAddr + "Home/findId.jsp" ,phone,userName);
                        Object object = null;
                        try {
                            object = loginFindId.execute().get();
                        }catch(Exception e){
                            e.printStackTrace();
                        }

                        findResult = (String) object;

                        if(findResult.equals("error")){

                            Toast.makeText(getActivity(), "아이디 찾기 오류 오류!", Toast.LENGTH_SHORT).show();

                        }else{
                            String userId = findResult;
                            Bundle bundle = new Bundle();
                            bundle.putString("userId",userId);
                            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                            FindPwFragment findPwFragment = new FindPwFragment();
                            findPwFragment.setArguments(bundle);
                            fragmentTransaction.replace(R.id.find_login_info_setting_frame , findPwFragment);
                            fragmentTransaction.commit();
                        }
                        // --


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(getContext(), "아이디 찾기 에러!", Toast.LENGTH_SHORT).show();
                    }
                });


    }



    //
}