package com.example.mogastyle.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.app.ActivityCompat;

import com.example.mogastyle.Activities.MyPage.MyPageMainActivity;
import com.example.mogastyle.Activities.MyPage.MyPageUpdatePwActivity;
import com.example.mogastyle.Common.LoginedUserInfo;
import com.example.mogastyle.Common.ShareVar;
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

public class MainGetPhoneDialog extends AppCompatDialogFragment {

    EditText et_main_get_user_phone,et_main_get_user_token;

    Button btn_main_get_user_phone,btn_main_get_user_token_check,btn_main_get_user_phone_cancel;

    private PhoneAuthProvider.ForceResendingToken forceResendingToken;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks;
    FirebaseAuth firebaseAuth;
    String mVerificationId ;

    String userPhone , userToken, updateResult;
    Intent intent;
    String urlAddr = ShareVar.hostRootAddr ;
    @NonNull
    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {



        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());



        LayoutInflater inflater  = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_main_get_user_phone , null);

        builder.setView(view)
                .setCancelable(false);

        et_main_get_user_phone = view.findViewById(R.id.et_main_get_user_phone);
        et_main_get_user_token = view.findViewById(R.id.et_main_get_user_token);

        btn_main_get_user_phone = view.findViewById(R.id.btn_main_get_user_phone);
        btn_main_get_user_token_check = view.findViewById(R.id.btn_main_get_user_token_check);

        btn_main_get_user_phone_cancel= view.findViewById(R.id.btn_main_get_user_phone_cancel);

        btn_main_get_user_phone.setOnClickListener(onClickListener);
        btn_main_get_user_token_check.setOnClickListener(onClickListener);
        btn_main_get_user_phone_cancel.setOnClickListener(onClickListener);

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


        return builder.create();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_main_get_user_phone:
                    userPhone = et_main_get_user_phone.getText().toString();
                    // 문자 전송
                    if(TextUtils.isEmpty(userPhone)){
                        Toast.makeText(getContext(), "핸드폰 번호 입력해주세요!", Toast.LENGTH_SHORT).show();

                    }else{
                        userPhone = et_main_get_user_phone.getText().toString();
                        startPhoneNumberVerification(userPhone);
                    }

                    break;
                case R.id.btn_main_get_user_token_check:
                    userToken = et_main_get_user_token.getText().toString();
                    if(TextUtils.isEmpty(userToken)){
                        Toast.makeText(getContext(), "인증 번호를 입력해주세요!", Toast.LENGTH_SHORT).show();
                    }else{

                        verifyPhoneNumberWithCode(mVerificationId ,userToken);
                    }

                    break;
                case R.id.btn_main_get_user_phone_cancel:
                    ActivityCompat.finishAffinity(MainGetPhoneDialog.super.getActivity());

                    break;
            }
        }
    };

    private void startPhoneNumberVerification(String phone) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phone)
                        .setTimeout(60L , TimeUnit.SECONDS)
                        .setActivity(MainGetPhoneDialog.super.getActivity())
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

                        // 핸드폰 번호 업데이트
                        MainGetPhoneUpdate mainGetPhoneUpdate = new MainGetPhoneUpdate(MainGetPhoneDialog.super.getActivity(),urlAddr + "Home/MainGetPhoneUpdate.jsp" ,phone ,Integer.toString(LoginedUserInfo.user.getNo()));
                        Object object = null;
                        try {
                            object = mainGetPhoneUpdate.execute().get();
                        }catch(Exception e){
                            e.printStackTrace();
                        }

                        updateResult = (String) object;

                        if(updateResult.equals("1")){
                            LoginedUserInfo.user.setUserPhone(phone);
                            dismiss();

                        }else{
                            Toast.makeText(MainGetPhoneDialog.super.getActivity(), "업데이트 오류!", Toast.LENGTH_SHORT).show();
                        }
                        // --


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        startActivity(new Intent(MainGetPhoneDialog.super.getContext() , SignUpActivity.class));
                    }
                });


    }
}//--
