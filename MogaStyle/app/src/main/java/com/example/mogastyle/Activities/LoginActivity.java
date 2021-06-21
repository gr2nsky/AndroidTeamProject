/*
    <<<<<<<<작성 2021.6.15 윤재필
    LoginActivity
    >>>>>>>>
 */
package com.example.mogastyle.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mogastyle.Common.LoginedUserInfo;
import com.example.mogastyle.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;

import org.jetbrains.annotations.NotNull;

import java.security.MessageDigest;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    Button btn_login_goMain;
    TextView tv_login_another_way_login;

    //KAKAO LOGIN
    private ISessionCallback kakaoSessionCallback;
    //-- KAKAO END

    //GOOGLE LOGIN
    private SignInButton btn_login_google_login;
    private FirebaseAuth auth;
    private GoogleApiClient googleApiClient;
    private static final int REQ_SIGN_GOOGLE = 100;
    //-- GOOGLE END

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login_goMain = findViewById(R.id.btn_login_goMain);
        btn_login_goMain.setOnClickListener(onClickListener);
        tv_login_another_way_login = findViewById(R.id.tv_login_another_way_login);
        tv_login_another_way_login.setOnClickListener(onClickListener);

        //KAKAO LOGIN
        kakaoSessionCallback = new ISessionCallback() {
            @Override
            public void onSessionOpened() {
                // 로그인 요청
                UserManagement.getInstance().me(new MeV2ResponseCallback() {

                    @Override
                    public void onFailure(ErrorResult errorResult) {
                        // 로그인 실패 상황
                        Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSessionClosed(ErrorResult errorResult) {
                        // 세션 닫힘 상황
                        Toast.makeText(LoginActivity.this, "세션 닫힘.. 다시 시도 요망", Toast.LENGTH_SHORT).show();
                        
                    }

                    @Override
                    public void onSuccess(MeV2Response result) {
                        // 로그인 성공 상황
                        intent = new Intent(LoginActivity.this , MainActivity.class);
                        intent.putExtra("userKakaoName" , result.getKakaoAccount().getProfile().getNickname());
                        intent.putExtra("userKakaoImage" , result.getKakaoAccount().getProfile().getProfileImageUrl());
                        intent.putExtra("userKakaoPhone" , result.getKakaoAccount().getPhoneNumber());
                        //User 저장
                        LoginedUserInfo.user.setName(result.getKakaoAccount().getProfile().getNickname());
                        //User 저장 end --
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onSessionOpenFailed(KakaoException exception) {

            }
        };

        Session.getCurrentSession().addCallback(kakaoSessionCallback);
        Session.getCurrentSession().checkAndImplicitOpen();
        //-- KAKAO END

        // GOOGLE LOGIN
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API , googleSignInOptions)
                .build();

        auth = FirebaseAuth.getInstance(); // firebase 인증 객체 초기화

        btn_login_google_login = findViewById(R.id.btn_login_google_login);
        btn_login_google_login.setOnClickListener(onClickListener);
        //--GOOGLE LOGIN END




        // 반영후에는 삭제될 메소드
        getAppKeyHash();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch(v.getId()){
                case R.id.btn_login_goMain:
                    intent = new Intent(LoginActivity.this , MainActivity.class);
                    startActivity(intent);
                    break;

                case R.id.tv_login_another_way_login:
                    intent = new Intent(LoginActivity.this , LoginBasicActivity.class);
                    startActivity(intent);
                    break;

                case R.id.btn_login_google_login:
                    intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                    startActivityForResult(intent , REQ_SIGN_GOOGLE);
            }
        }
    };

    /*
     * TODO
     * 카카오 로그인 시 필요한 해시키를 얻는 메소드.
     *  << 반영 후 에는 삭제될 메소드 >>
     * 각자의 해시키 보고 올려주시면 반영하겠습니다 ~
     *  YJ = "keRXhFxHMBcPgn2fDjT4GdL8aQU="
     */
    private void getAppKeyHash(){
        try{
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for(Signature signature : info.signatures){
                MessageDigest messageDigest;
                messageDigest = MessageDigest.getInstance("SHA");
                messageDigest.update(signature.toByteArray());
                String appKeyHash = new String(Base64.encode(messageDigest.digest() , 0));
                Log.e("Hash key",appKeyHash);
            }


        }catch(Exception e){
            Log.e("name not found",e.toString());
        }

    }

    // GOOGLE LOGIN

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        //Google LOGIN AUTH REQUEST , 결과값을 되돌려 받는곳
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_SIGN_GOOGLE){
            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            // 인증 결과 성공 상황
            if(googleSignInResult.isSuccess()){
                GoogleSignInAccount googleSignInAccount = googleSignInResult.getSignInAccount(); // googleSignInAccount 에 구글 로그인 정보 저장 (닉네임, 프로필 사진 URL, 이메일 주소...)
                resultGoogleLogin(googleSignInAccount);
            }
        }

    }

    private void resultGoogleLogin(GoogleSignInAccount googleSignInAccount) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken() , null);
        auth.signInWithCredential(authCredential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        // 로그인에 실제 성공 했는지 상황
                        if(task.isSuccessful()) {
                            // 로그인 성공 상황
                            Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                            intent = new Intent(getApplicationContext() , MainActivity.class);
                            intent.putExtra("nickName" , googleSignInAccount.getDisplayName());
                            // 이름 저장
                            LoginedUserInfo.user.setName(googleSignInAccount.getDisplayName());
                            // --
                            intent.putExtra("photo" , googleSignInAccount.getPhotoUrl());

                            startActivity(intent);
                        }else{
                            //로그인 실패 상황
                            Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull @NotNull ConnectionResult connectionResult) {

    }
    // -- GOOGLE LOGIN END
}