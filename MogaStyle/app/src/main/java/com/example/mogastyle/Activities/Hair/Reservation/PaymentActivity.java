/*
제작 : 윤재필
제작일시 : 2021 06 22
내용
결제 페이지

 */
package com.example.mogastyle.Activities.Hair.Reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mogastyle.Bean.PaymentBeanStack;
import com.example.mogastyle.Bean.ResDateData;
import com.example.mogastyle.Bean.TempDesignerBean;
import com.example.mogastyle.Bean.TempShopBean;
import com.example.mogastyle.Bean.TempStyleBean;
import com.example.mogastyle.Bean.User;
import com.example.mogastyle.Common.LoginedUserInfo;
import com.example.mogastyle.R;

public class PaymentActivity extends AppCompatActivity {

    /////////////layout resource
    CheckBox cb;
    EditText et_booker_name;
    EditText et_booker_phone;
    EditText et_booker_auth_code;
    TextView tv_res_date;
    TextView tv_res_time;
    TextView tv_res_shop_name;
    TextView tv_res_designer_name;
    TextView tv_res_total_price;
    Button btn_request_auth_code;
    Button btn_check_auth_code;
    Button btn_submit;

    ///////////data
    TempDesignerBean designerBean = null;
    TempShopBean shopBean = null;
    TempStyleBean styleBean = null;
    ResDateData resDateData = null;
    User booker;
    int resTime = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        if(!paymentBeanStackCheck()){
            Toast.makeText(this, "Payment Stack error", Toast.LENGTH_SHORT).show();
        }
        //
        //          data binding
        //
        designerBean = PaymentBeanStack.stack.getDesignerBean();
        shopBean = PaymentBeanStack.stack.getShopBean();
        styleBean = PaymentBeanStack.stack.getStyleBean();
        resDateData = PaymentBeanStack.stack.getResDateData();
        resTime = PaymentBeanStack.stack.getResTime();
        booker = LoginedUserInfo.user;


        //
        //          layout resource match
        //
        cb = findViewById(R.id.cb_payment_auto_input);
        et_booker_name = findViewById(R.id.et_payment_name);
        et_booker_phone = findViewById(R.id.et_payment_phone);
        et_booker_auth_code = findViewById(R.id.et_payment_auth_code);

        tv_res_date = findViewById(R.id.tv_payment_res_date);
        tv_res_time = findViewById(R.id.tv_payment_res_time);
        tv_res_shop_name = findViewById(R.id.tv_payment_res_shop);
        tv_res_designer_name = findViewById(R.id.tv_payment_res_designer);
        tv_res_total_price = findViewById(R.id.tv_payment_res_total_price);

        btn_request_auth_code = findViewById(R.id.btn_payment_request_auth_code);
        btn_check_auth_code = findViewById(R.id.btn_payment_check_auth_code);
        btn_submit = findViewById(R.id.btn_payment_submit);

        btn_request_auth_code.setOnClickListener(onClickListener);
        btn_check_auth_code.setOnClickListener(onClickListener);
        btn_submit.setOnClickListener(onClickListener);

        //
        //           data + Layout resource
        //
        et_booker_name.setText(booker.getName());
        et_booker_phone.setText(booker.getUserPhone());
        tv_res_date.setText(resDateData.print());
        tv_res_time.setText(Integer.toString(resTime));
        tv_res_shop_name.setText(shopBean.getName());
        tv_res_designer_name.setText(designerBean.getName());
        tv_res_total_price.setText(styleBean.getPrice());
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_payment_request_auth_code:
                    requestAuthCode();
                    break;
                case R.id.btn_payment_check_auth_code:
                    authCodeCheck();
                    break;
                case R.id.btn_payment_submit:

                    break;
            }
        }
    };

    ////////////////////////////////////////////////////////
    //                                                    //
    //              문자인증 추가 해야함                        //
    //                                                    //
    ////////////////////////////////////////////////////////
    public void requestAuthCode(){

    }

    public void authCodeCheck(){

    }


    //PaymentBeanStack.stack이 유효한지 검사
    public boolean paymentBeanStackCheck(){
        if(PaymentBeanStack.stack.getDesignerBean() == null){
            return false;
        }
        if(PaymentBeanStack.stack.getShopBean() == null){
            return false;
        }
        if(PaymentBeanStack.stack.getStyleBean() == null) {
            return false;
        }

        return true;
    }
}
