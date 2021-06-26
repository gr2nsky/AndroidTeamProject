package com.example.mogastyle.Adapters.Hair.Styling;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mogastyle.Bean.Styling;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.R;

import java.util.ArrayList;

import static com.example.mogastyle.Common.ShareVar.hostRootAddr;

public class StylingRecyclerAdapter extends RecyclerView.Adapter<StylingRecyclerAdapter.ViewHolder> {

    private Context mContext = null;
    private int layout = 0;
    private ArrayList<Styling> data = null;
    private LayoutInflater inflater = null;
    private Fragment designerDetailPageStylingFragment = null;

    // 생성자(Constructor)
    public StylingRecyclerAdapter(Context mContext, int layout, ArrayList<Styling> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView iv_image;
        public TextView tv_title, tv_price, tv_typeCode_cut, tv_typeCode_perm, tv_typeCode_dyeing;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.iv_styling_list_image);
            tv_title = itemView.findViewById(R.id.tv_styling_list_title);
            tv_price = itemView.findViewById(R.id.tv_styling_list_price);
            tv_typeCode_cut = itemView.findViewById(R.id.tv_styling_list_typeCode_cut);
            tv_typeCode_perm = itemView.findViewById(R.id.tv_styling_list_typeCode_perm);
            tv_typeCode_dyeing = itemView.findViewById(R.id.tv_styling_list_typeCode_dyeing);

        }
    }

    @NonNull
    @Override // 생성자 만들어 줌(초기값 만들어주는 생성자)
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_styling_detail_view, parent, false);
        StylingRecyclerAdapter.ViewHolder viewHolder = new StylingRecyclerAdapter.ViewHolder(v);
        return viewHolder;
    }

    // 화면 구성 메소드 = setText
    @Override
    public void onBindViewHolder(StylingRecyclerAdapter.ViewHolder holder, int position) {

        //이미지 :
        Glide.with(mContext)
                .load(ShareVar.styleImgPath + data.get(position).getImage())
                .placeholder(R.drawable.ic_no_image)
                .error(R.drawable.ic_no_image)
                .fallback(R.drawable.ic_no_image)
                .into(holder.iv_image);

        holder.tv_title.setText(data.get(position).getTitle());
        holder.tv_price.setText("" + data.get(position).getPrice());

        // 해당되는 번째에 typeCode를 불러와서 char배열에 담아준다.
        char [] typeCode = typeSplit(data.get(position).getTypeCode());
        Log.v("Message", "StylingAdapter_char [] : " + data.get(position).getTypeCode());

        int count = 0;
        for(int i=0; i<3; i++){ // 커트,펌,염색
            if(typeCode [i] == '1'){
                Log.v("Message", "typeCode ["+ i +"] : " + typeCode [i]);

                switch (count){
                    case 0 :
                        holder.tv_typeCode_cut.setVisibility(View.VISIBLE);
                        break;
                    case 1 :
                        holder.tv_typeCode_perm.setVisibility(View.VISIBLE);
                        break;
                    case 2 :
                        holder.tv_typeCode_dyeing.setVisibility(View.VISIBLE);
                        break;
                }
            }
            count++;
        }


    }

    // typeCode 배열로 선택한 것만 불러오기
    public char[] typeSplit(String type){
        Log.v("Message", "StylingRecyclerAdapter_typeSplit");
        return type.toCharArray();
    }

    // Data 개수 세기
    @Override
    public int getItemCount() {
        return data.size();
    }


} // ---------------------------------------------
