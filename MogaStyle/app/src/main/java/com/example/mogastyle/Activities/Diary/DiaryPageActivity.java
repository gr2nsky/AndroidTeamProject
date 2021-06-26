package com.example.mogastyle.Activities.Diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mogastyle.Adapters.Diary.DiaryAdapter;
import com.example.mogastyle.Bean.Diary.DiaryBookList;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.NetworkTasks.Diary.DiaryMakeDiaryPage;
import com.example.mogastyle.R;

import java.util.ArrayList;

public class DiaryPageActivity extends AppCompatActivity {

    String urlAddr = ShareVar.hostRootAddr;
    String title = null;
    int diary_no = 0;
    String image;
    Button btn_diary_write;
    ImageView iv_diary_page_main_image;
    TextView tv_diary_list_title;
    ArrayList<DiaryBookList> styles;
    DiaryAdapter adapter;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_page);

        iv_diary_page_main_image = findViewById(R.id.iv_diary_page_main_image);

        listView = findViewById(R.id.lv_style_diary);

        btn_diary_write = findViewById(R.id.btn_diary_write);


        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        diary_no = intent.getIntExtra("no" , 0);

        image = intent.getStringExtra("image");


        btn_diary_write.setOnClickListener(onClickListener);

        tv_diary_list_title = findViewById(R.id.tv_diary_list_title);
        tv_diary_list_title.setText(title);

        Glide.with(this)
                .load(ShareVar.diaryImgPath + image)
                .into(iv_diary_page_main_image);

    }
    protected void onResume() {
        super.onResume();

        connectGetData();

    }
    private void connectGetData() {
        try{
            DiaryMakeDiaryPage networkTaskDiary = new DiaryMakeDiaryPage(DiaryPageActivity.this , urlAddr + "Diary/diarySelectAll.jsp?diaryNo="+diary_no , "select");
            Object obj = networkTaskDiary.execute().get();
            styles = (ArrayList<DiaryBookList>) obj;

            adapter = new DiaryAdapter(DiaryPageActivity.this ,R.layout.style_diary_list_layout ,styles);

            listView.setAdapter(adapter);


            // 자세히 보기 -> update & delete
            listView.setOnItemClickListener(onItemClickListener);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(DiaryPageActivity.this , DiaryWriteActivity.class);
            intent.putExtra("diary_no" , diary_no);
            startActivity(intent);
        }
    };

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(DiaryPageActivity.this , DiaryListDetailActivity.class);
            intent.putExtra("no" , styles.get(position).getNo());
            intent.putExtra("date" , styles.get(position).getStyleDate());
            intent.putExtra("image" ,  styles.get(position).getImage());
            intent.putExtra("hairShop" ,  styles.get(position).getHairShop());
            intent.putExtra("designer" ,  styles.get(position).getDesignerName());
            intent.putExtra("comments" , styles.get(position).getContents());
            startActivity(intent);

        }
    };

}