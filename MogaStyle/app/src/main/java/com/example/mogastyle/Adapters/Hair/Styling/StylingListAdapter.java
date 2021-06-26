package com.example.mogastyle.Adapters.Hair.Styling;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mogastyle.Bean.Styling;
import com.example.mogastyle.R;

import java.util.ArrayList;

public class StylingListAdapter extends BaseAdapter {

    private Context mContext = null;
    private int layout = 0;
    private ArrayList<Styling> data = null;
    private LayoutInflater inflater = null;

    // 생성자(Constructor) : inflater는 ()괄호 안에 들어가면 안 돼!
    public StylingListAdapter(Context mContext, int layout, ArrayList<Styling> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // Data 개수 세기
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position).getTitle();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.v("Message", "StylingAdapter_getView");

        convertView = inflater.inflate(this.layout, parent, false);

        // id값 가져오기
        TextView tv_title = convertView.findViewById(R.id.tv_styling_list_title);
        TextView tv_price = convertView.findViewById(R.id.tv_styling_list_price);
        TextView tv_typeCode_cut = convertView.findViewById(R.id.tv_styling_list_typeCode_cut);
        TextView tv_typeCode_perm = convertView.findViewById(R.id.tv_styling_list_typeCode_perm);
        TextView tv_typeCode_dyeing = convertView.findViewById(R.id.tv_styling_list_typeCode_dyeing);

        // title, price : setText
        tv_title.setText(data.get(position).getTitle());
        tv_price.setText("" + data.get(position).getPrice()); // int는 setText할 때 "" + or Integer.string이 필요하다

        // typeCode : 해당되는 번째에 typeCode를 불러와서 char배열에 담아준다.
        char [] typeCode = typeSplit(data.get(position).getTypeCode());
        Log.v("Message", "StylingAdapter_char [] : " + data.get(position).getTypeCode());
        
        int count = 0;
        for(int i=0; i<3; i++){ // 우리는 커트,펌,염색 이렇게 3개라서 3이하로 쓴 것임!
            if(typeCode [i] == '1'){
                Log.v("Message", "typeCode ["+ i +"] : " + typeCode [i]);
                
                switch (count){
                    case 0 :
                        tv_typeCode_cut.setVisibility(View.VISIBLE);
                        break;
                    case 1 :
                        tv_typeCode_perm.setVisibility(View.VISIBLE);
                        break;
                    case 2 :
                        tv_typeCode_dyeing.setVisibility(View.VISIBLE);
                        break;
                }
            }
            count++;
        }


        return convertView;
    }

    // typeCode 배열로 선택한 것만 불러오기
    public char[] typeSplit(String type){
        Log.v("Message", "StylingAdapter_typeSplit");
        return type.toCharArray();
    }

} // ---------------------------------------------
