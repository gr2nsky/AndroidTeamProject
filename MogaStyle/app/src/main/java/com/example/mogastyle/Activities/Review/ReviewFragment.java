package com.example.mogastyle.Activities.Review;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mogastyle.Activities.Hair.Reservation.ReservationCheckActivity;
import com.example.mogastyle.Adapters.Hair.Reservation.ResCheckAdapter;
import com.example.mogastyle.Adapters.Review.ReviewAdapter;
import com.example.mogastyle.Bean.Designer;
import com.example.mogastyle.Bean.ReservationBean;
import com.example.mogastyle.Bean.ReservationList;
import com.example.mogastyle.Bean.Shop;
import com.example.mogastyle.Bean.Styling;
import com.example.mogastyle.NetworkTasks.Hair.Reservation.GetDesginerResData;
import com.example.mogastyle.NetworkTasks.Hair.Reservation.RetrofitCall;
import com.example.mogastyle.NetworkTasks.Hair.Reservation.RetrofitService;
import com.example.mogastyle.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewFragment extends Fragment {

    String TAG = "ReviewFragment";
    //-------------
    Shop shopBean = null;
    Designer designerBean = null;
    //-------------
    TextView tv_all_rating;
    RatingBar ratingBar;
    TextView tv_all_count;
    RecyclerView reviewList;
    RecyclerView.LayoutManager layoutManager;
    ReviewAdapter reviewAdapter;
    //-------------
    ArrayList<ReservationBean> rbList;
    //designer : 1, shop : 2
    int checkType = 0;
    //-------------
    private RetrofitService retrofitService;

    public ReviewFragment(Designer designerBean) {
        this.designerBean = designerBean;
        this.checkType = 1;
    }

    public ReviewFragment(Shop shopBean) {
        this.shopBean = shopBean;
        this.checkType = 2;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_review, container, false);

        tv_all_rating = v.findViewById(R.id.tv_review_all_rating);
        ratingBar = v.findViewById(R.id.rating_bar_review_rating);
        tv_all_count = v.findViewById(R.id.tv_review_all_count);
        reviewList = v.findViewById(R.id.recycler_view_review_list);

        if(checkType == 1){
            setLaouyResoureByDesigner();
        } else{
            setLaouyResoureByShop();
        }

        retrofitService = RetrofitCall.reservationService();
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        getReviewList();
    }

    public void getReviewList(){
        rbList = new ArrayList<>();

        if(checkType == 1){
            retrofitService.getListForReviewByDesigner(designerBean.getNo()).enqueue(reviewCallback);
        }else {
            retrofitService.getListForReviewByShop(shopBean.getNo()).enqueue(reviewCallback);
        }
    }

    Callback<ReservationList> reviewCallback = new Callback<ReservationList>(){
        @Override
        public void onResponse(Call<ReservationList> call, Response<ReservationList> response) {
            ReservationList result = response.body();
            rbList = result.getReservationList();

            Log.d(TAG, "onResponse:성공");
            Log.d(TAG, "결과 body: " + response.body());
            Log.d(TAG, "결과 reservationList: " + result.print());

            layoutManager = new LinearLayoutManager(getContext());
            reviewList.setLayoutManager(layoutManager);
            reviewAdapter = new ReviewAdapter(getContext(), R.layout.list_item_review, rbList);
            reviewList.setAdapter(reviewAdapter);
        }
        @Override
        public void onFailure(Call<ReservationList> call, Throwable t) {
            Log.d(TAG, "onResponse:실패");
            Log.d(TAG, "결과 : " + t.toString());
            Toast.makeText(getContext(), "리뷰 로드에 실패했습니다.", Toast.LENGTH_SHORT).show();
        }
    };

    public void setLaouyResoureByShop(){
        tv_all_rating.setText(Double.toString(shopBean.getRating()));
        ratingBar.setNumStars((int)Math.round(shopBean.getRating()));;
        tv_all_count.setText(Integer.toString(shopBean.getCount()));
    }
    public void setLaouyResoureByDesigner(){

    }
}