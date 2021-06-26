package com.example.mogastyle.Adapters.Review;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mogastyle.Adapters.Hair.Reservation.ResCheckAdapter;
import com.example.mogastyle.Bean.ReservationBean;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.R;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>{

    String TAG = "ReviewAdapter";

    public class ReviewViewHolder extends RecyclerView.ViewHolder{

        TextView tv_menu_name;
        TextView tv_designer_name;
        RatingBar ratingBar;
        TextView tv_rating;
        TextView tv_content;
        ImageView imageView;
        TextView tv_writer;
        TextView tv_submit_date;
        TextView tv_report;

        public ReviewViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tv_menu_name = itemView.findViewById(R.id.tv_review_list_item_menu_name);
            tv_designer_name = itemView.findViewById(R.id.tv_review_list_item_designer_name);
            ratingBar = itemView.findViewById(R.id.tv_review_list_item_rating);
            tv_rating = itemView.findViewById(R.id.tv_review_list_item_rating);
            tv_content = itemView.findViewById(R.id.tv_review_list_item_content);
            imageView = itemView.findViewById(R.id.iv_review_list_item);
            tv_writer = itemView.findViewById(R.id.tv_review_list_item_writer);
            tv_submit_date = itemView.findViewById(R.id.tv_review_list_item_submit_date);
            tv_report = itemView.findViewById(R.id.tv_review_list_item_report);
        }
    }

    Context con = null;
    int layout = 0;
    private LayoutInflater inflater = null;
    ArrayList<ReservationBean> rbList = null;

    public ReviewAdapter(Context con, int layout, ArrayList<ReservationBean> rbList) {
        this.con = con;
        this.layout = layout;
        this.rbList = rbList;
        inflater = (LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_reservation_check, parent, false);
        ReviewAdapter.ReviewViewHolder viewHolder = new ReviewAdapter.ReviewViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        ReservationBean rb = rbList.get(position);
        holder.tv_menu_name.setText(rb.getStylingTitle());
        holder.tv_designer_name.setText(rb.getDesignerName());
        holder.tv_content.setText(rb.getReviewContent());
        holder.tv_writer.setText(rb.getUserName());
        holder.ratingBar.setNumStars(rb.getReviewScore());
        holder.tv_rating.setText(rb.getReviewScore());
        Glide.with(con)
                .load(ShareVar.reviewImgPath+rb.getReviewPhoto())
                .placeholder(R.drawable.ic_no_image)
                .error(R.drawable.ic_no_image)
                .fallback(R.drawable.ic_no_image)
                .into(holder.imageView);
        holder.tv_submit_date.setText(rb.getReservationDate());
        holder.tv_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return rbList.size();
    }
}
