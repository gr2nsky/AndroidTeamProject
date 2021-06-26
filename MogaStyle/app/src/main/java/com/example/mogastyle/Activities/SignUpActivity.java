package com.example.mogastyle.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.NetworkTasks.Login.LoginCheckUserId;
import com.example.mogastyle.NetworkTasks.Login.SignUpInApp;
import com.example.mogastyle.R;
import com.example.mogastyle.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class SignUpActivity extends AppCompatActivity {

    EditText et_sign_up_name ,et_sign_up_userid , et_sign_up_userpw , et_sign_up_phone , et_sign_up_token;

    Button btn_sign_up_check_user_id, btn_sign_up_phone_check ,btn_sign_up_token_check ;

    TextView tv_sign_up_token_check;

    String userName,userId ,userPw , userPhone , userToken , userCheck , joinType;

    //FireBase Phone Auth

    private ActivityMainBinding binding;

    private PhoneAuthProvider.ForceResendingToken forceResendingToken;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks;
    FirebaseAuth firebaseAuth;
    String mVerificationId ;
    // FireBase Phone Auth End --


    String urlAddr = ShareVar.hostRootAddr ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //EditText Binding
        et_sign_up_name = findViewById(R.id.et_sign_up_name);
        et_sign_up_userid = findViewById(R.id.et_sign_up_userid);
        et_sign_up_userpw = findViewById(R.id.et_sign_up_userpw);

        et_sign_up_phone = findViewById(R.id.et_sign_up_phone);
        et_sign_up_token = findViewById(R.id.et_sign_up_token);

        //Button Binding
        btn_sign_up_check_user_id = findViewById(R.id.btn_sign_up_check_user_id);
        btn_sign_up_phone_check = findViewById(R.id.btn_sign_up_phone_check);
        btn_sign_up_token_check = findViewById(R.id.btn_sign_up_token_check);


        //TextView Binding
        tv_sign_up_token_check = findViewById(R.id.tv_sign_up_token_check);


        btn_sign_up_check_user_id.setOnClickListener(onClickListener);
        btn_sign_up_phone_check.setOnClickListener(onClickListener);
        btn_sign_up_token_check.setOnClickListener(onClickListener);


        //정보 가져오기


        //FireBase Phone Auth
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



        //FireBase Phone Auth End --
    }

    //Button ClickListener
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_sign_up_check_user_id:
                    // 아이디 중복 확인
                    userId = et_sign_up_userid.getText().toString();
                    LoginCheckUserId loginCheckUserId = new LoginCheckUserId(SignUpActivity.this, urlAddr + "Home/userSignUpCheckId.jsp",userId);
                    Object userCheckObject = null;
                    String userCheckResult = "0";
                    try{
                        userCheckObject = loginCheckUserId.execute().get();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    userCheckResult = (String) userCheckObject;

                    if ( userCheckResult.equals("2")){
                        Toast.makeText(SignUpActivity.this, userId + "이미 로그인 되어있는 아이디입니다.", Toast.LENGTH_SHORT).show();
                    }else if( userCheckResult.equals("1")) {
                        Toast.makeText(SignUpActivity.this, "사용 가능한 아이디 입니다.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(SignUpActivity.this, "아이디 체크 실패", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.btn_sign_up_phone_check:
                    userPhone = et_sign_up_phone.getText().toString();
                    // 문자 전송
                    if(TextUtils.isEmpty(userPhone)){
                        Toast.makeText(SignUpActivity.this, "핸드폰 번호 입력해주세요!", Toast.LENGTH_SHORT).show();

                    }else{
                        userPhone = et_sign_up_phone.getText().toString();
                        startPhoneNumberVerification(userPhone);
                    }


                    break;


                case R.id.btn_sign_up_token_check:
                    //인증하기
                    userToken = et_sign_up_token.getText().toString();

                    userName = et_sign_up_name.getText().toString();
                    userId = et_sign_up_userid.getText().toString();
                    userPw = et_sign_up_userpw.getText().toString();
                    userPhone = et_sign_up_phone.getText().toString();
                    if(TextUtils.isEmpty(userName)){
                        Toast.makeText(SignUpActivity.this, "이름을 입력해주세요!", Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(userId)){
                        Toast.makeText(SignUpActivity.this, "아이디를 입력해주세요!", Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(userPw)){
                        Toast.makeText(SignUpActivity.this, "비밀번호를 입력해주세요!", Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(userPhone)){
                        Toast.makeText(SignUpActivity.this, "전화번호 입력해주세요!", Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(userToken)){
                        Toast.makeText(SignUpActivity.this, "인증 번호를 입력해주세요!", Toast.LENGTH_SHORT).show();
                    }else{

                            verifyPhoneNumberWithCode(mVerificationId ,userToken);


                    }

                    break;



            }
        }
    };


    // -- Button Click Listener end
    //FireBase Auth

    private void startPhoneNumberVerification(String phone) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phone)
                        .setTimeout(60L , TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(mCallBacks)
                        .build();

        PhoneAuthProvider.verifyPhoneNumber(options);

    }
    /*
        private void resendVerificationCode(String phone , PhoneAuthProvider.ForceResendingToken token){
            PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phone)
                        .setTimeout(60L , TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(mCallBacks)
                        .setForceResendingToken(token)
                        .build();

        PhoneAuthProvider.verifyPhoneNumber(options);

        }

     */

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
                        Toast.makeText(SignUpActivity.this, phone , Toast.LENGTH_SHORT).show();
                        // 회원가입 완료
                        //DB 저~장
                        userName = et_sign_up_name.getText().toString();
                        userId = et_sign_up_userid.getText().toString();
                        userPw = et_sign_up_userpw.getText().toString();
                        userToken = et_sign_up_token.getText().toString();
                        userPhone = et_sign_up_phone.getText().toString();
                        userCheck = "0";
                        joinType = "0";

                        SignUpInApp signUpInApp = new SignUpInApp(SignUpActivity.this , urlAddr + "Home/userSignUpInApp.jsp" , userName,userId , userPw , userPhone ,userCheck,joinType);

                        Object object = null;
                        String result = "0";
                        try{
                            object = signUpInApp.execute().get();
                        }catch (Exception e){
                            e.printStackTrace();

                        }

                        result = (String) object;

                        Log.d("result" , result);

                        if (result.equals("1")){
                            Toast.makeText(SignUpActivity.this, userId + " 님 회원가입 완료!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this , LoginBasicActivity.class);
//                        intent.putExtra("userId",userId);
//                        intent.putExtra("userPw",userPw);
//                        //회원가입 에서 왔어요~
//                        intent.putExtra("login","1");
                            startActivity(intent);

                        }else if(result.equals("2")) {
                            Toast.makeText(SignUpActivity.this, "이미 로그인 되어있는 아이디입니다", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(SignUpActivity.this, "회원가입 실패!", Toast.LENGTH_SHORT).show();
                        }

                        // --
                        startActivity(new Intent(SignUpActivity.this , LoginBasicActivity.class));

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        startActivity(new Intent(SignUpActivity.this , SignUpActivity.class));
                    }
                });


    }


}