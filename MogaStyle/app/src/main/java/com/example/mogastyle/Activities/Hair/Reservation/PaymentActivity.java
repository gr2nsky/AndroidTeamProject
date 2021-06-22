/*
제작 : 윤재필
제작일시 : 2021 06 22
내용
결제 페이지

 */
package com.example.mogastyle.Activities.Hair.Reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mogastyle.Bean.PaymentBeanStack;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        if(!paymentBeanStackCheck()){
            Toast.makeText(this, "Payment Stack error", Toast.LENGTH_SHORT).show();
        }

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

        //
        //           data + Layout resource
        //

        et_booker_name.setText(LoginedUserInfo.user.getName());
        et_booker_phone.setText(LoginedUserInfo.user.getPhone());


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
