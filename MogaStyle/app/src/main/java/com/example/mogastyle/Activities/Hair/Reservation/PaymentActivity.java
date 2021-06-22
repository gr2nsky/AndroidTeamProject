package com.example.mogastyle.Activities.Hair.Reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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
    TextView tv_res_d

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        ////////////layout resource
        cb = findViewById(R.id.cb_payment_auto_input);
        et_booker_name = findViewById(R.id.et_payment_name);
        et_booker_phone = findViewById(R.id.et_payment_phone);
        et_booker_auth_code = findViewById(R.id.et_payment_auth_code);


    }
}