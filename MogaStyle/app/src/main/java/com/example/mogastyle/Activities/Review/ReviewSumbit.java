package com.example.mogastyle.Activities.Review;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.example.mogastyle.R;

public class ReviewSumbit extends Dialog {

    ImageView imageView;
    RatingBar ratingBar;
    EditText et_content;
    Button btn_submit;
    Button btn_cancel;

    public ReviewSumbit(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_submit_review);

        imageView = findViewById(R.id.iv_review_submit);
        ratingBar = findViewById(R.id.rb_review_submit);
        et_content = findViewById(R.id.et_review_submit);
        btn_submit = findViewById(R.id.btn_review_submit_ok);
        btn_cancel = findViewById(R.id.btn_review_submit_cancel);

    }
}
