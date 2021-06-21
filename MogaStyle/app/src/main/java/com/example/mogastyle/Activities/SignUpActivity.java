package com.example.mogastyle.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mogastyle.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class SignUpActivity extends AppCompatActivity {

    EditText et_sign_up_userid , et_sign_up_userpw ,et_sign_up_userpw2 ,et_sign_up_phone , et_sign_up_token;

    Button btn_sign_up_check_user_id, btn_sign_up_phone_check ,btn_sign_up_token_check ,btn_sign_up_final;

    TextView tv_sign_up_token_check;

    String userId ,userPw , userPw2 , userPhone , userToken;

    //FireBase Phone Auth
    FirebaseAuth firebaseAuth;
    String verificationId ;
    // FireBase Phone Auth End --

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //EditText Binding
        et_sign_up_userid = findViewById(R.id.et_sign_up_userid);
        et_sign_up_userpw = findViewById(R.id.et_sign_up_userpw);
        et_sign_up_userpw2 = findViewById(R.id.et_sign_up_userpw2);
        et_sign_up_phone = findViewById(R.id.et_sign_up_phone);
        et_sign_up_token = findViewById(R.id.et_sign_up_token);

        //Button Binding
        btn_sign_up_check_user_id = findViewById(R.id.btn_sign_up_check_user_id);
        btn_sign_up_phone_check = findViewById(R.id.btn_sign_up_phone_check);
        btn_sign_up_token_check = findViewById(R.id.btn_sign_up_token_check);
        btn_sign_up_final = findViewById(R.id.btn_sign_up_final);

        //TextView Binding
        tv_sign_up_token_check = findViewById(R.id.tv_sign_up_token_check);


        btn_sign_up_check_user_id.setOnClickListener(onClickListener);
        btn_sign_up_phone_check.setOnClickListener(onClickListener);
        btn_sign_up_token_check.setOnClickListener(onClickListener);
        btn_sign_up_final.setOnClickListener(onClickListener);

        //정보 가져오기
        userId = et_sign_up_userid.getText().toString();
        userPw = et_sign_up_userpw.getText().toString();
        userPw2 = et_sign_up_userpw2.getText().toString();
        userToken = et_sign_up_token.getText().toString();
        userPhone = et_sign_up_phone.getText().toString();

        //FireBase Phone Auth
        firebaseAuth = FirebaseAuth.getInstance();
        //FireBase Phone Auth End --
    }

    //Button ClickListener
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_sign_up_check_user_id:
                    // 아이디 중복 확인


                    //DB CHECK

                    break;


                case R.id.btn_sign_up_phone_check:
                    // 문자 전송
                    if(TextUtils.isEmpty(et_sign_up_phone.getText().toString())){
                        Toast.makeText(SignUpActivity.this, "핸드폰 번호 입력해주세요!", Toast.LENGTH_SHORT).show();

                    }else{
                        userPhone = et_sign_up_phone.getText().toString();
                        sendVerificationCode(userPhone);
                    }


                    break;


                case R.id.btn_sign_up_token_check:
                    //인증하기
                    if(TextUtils.isEmpty(et_sign_up_token.getText().toString())){
                        Toast.makeText(SignUpActivity.this, "인증 번호를 입력해주세요!", Toast.LENGTH_SHORT).show();
                    }else{
                        userToken = et_sign_up_token.getText().toString();
                        verifyCode(userToken);
                    }

                    break;

                case R.id.btn_sign_up_final:
                    //마지막 화원가입 완료



                    break;

            }
        }
    };
    // -- Button Click Listener end

    //FireBase Phone Auth
    private void sendVerificationCode(String userPhone){
        PhoneAuthOptions phoneAuthOptions = PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(userPhone)
                .setTimeout(60L , TimeUnit.SECONDS)
                .setCallbacks(mCallBack)
                .setActivity(this)
                .build();

        PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull @NotNull String s, @NonNull @NotNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull @NotNull PhoneAuthCredential phoneAuthCredential) {
            final String code = phoneAuthCredential.getSmsCode();
            et_sign_up_token.setText(code);
            tv_sign_up_token_check.setVisibility(View.VISIBLE);
            tv_sign_up_token_check.setText("인증번호 일치");
//            verifyCode(code);
        }

        @Override
        public void onVerificationFailed(@NonNull @NotNull FirebaseException e) {
            Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private void verifyCode(String code){
        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verificationId ,code);
        signInWithCredential(phoneAuthCredential);

    }

    private void signInWithCredential(PhoneAuthCredential credential){

        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    // 성공했을 경우 나오는 것
                    // 뭔가 로그인이 완료됬을때 보여주는거 같은데..



                }else{
                    Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //FireBase Phone Auth-- End

}