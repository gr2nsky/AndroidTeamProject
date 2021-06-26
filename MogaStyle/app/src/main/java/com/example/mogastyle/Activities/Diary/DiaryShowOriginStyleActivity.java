package com.example.mogastyle.Activities.Diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mogastyle.Adapters.Diary.DiaryBookAdapter;
import com.example.mogastyle.Bean.Diary.Diary;
import com.example.mogastyle.Common.LoginedUserInfo;
import com.example.mogastyle.Common.ShareVar;
import com.example.mogastyle.NetworkTasks.Diary.DiarySelectDiary;
import com.example.mogastyle.R;

import java.util.ArrayList;

public class DiaryShowOriginStyleActivity extends AppCompatActivity {

    String urlAddr = ShareVar.hostRootAddr;
    ArrayList<Diary> diaries;
    DiaryBookAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_show_origin_style);

        listView = findViewById(R.id.lv_style_book);


    }
    @Override
    protected void onResume() {
        super.onResume();

        connectGetData();

    }

    private void connectGetData() {
        try{
            DiarySelectDiary networkTask = new DiarySelectDiary(DiaryShowOriginStyleActivity.this , urlAddr + "Diary/styleDiaryBookSelectAll.jsp?userNo=" + LoginedUserInfo.user.getNo());
            Object obj = networkTask.execute().get();
            diaries = (ArrayList<Diary>) obj;

            adapter = new DiaryBookAdapter(DiaryShowOriginStyleActivity.this ,R.layout.style_diary_layout ,diaries);

            listView.setAdapter(adapter);
//            수정 혹은 삭제 할때
            listView.setOnItemLongClickListener(onItemLongClickListener);
            listView.setOnItemClickListener(onItemClickListener);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener(){
        Intent intent = null;
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            intent = new Intent(DiaryShowOriginStyleActivity.this , DiaryPageActivity.class);
            intent.putExtra("no" , diaries.get(position).getNo());
            intent.putExtra("image" ,  diaries.get(position).getImage());
            intent.putExtra("title" ,  diaries.get(position).getTitle());
            intent.putExtra("hairLength" ,  diaries.get(position).getHairLength());
            startActivity(intent);
        }
    };

    AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        Intent intent = null;
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(DiaryShowOriginStyleActivity.this, "수정하기 페이지 입니다!", Toast.LENGTH_SHORT).show();
            intent = new Intent(DiaryShowOriginStyleActivity.this , DiaryEditStyleActivity.class);
            intent.putExtra("no" , diaries.get(position).getNo());
            intent.putExtra("image" ,  diaries.get(position).getImage());
            intent.putExtra("title" ,  diaries.get(position).getTitle());
            intent.putExtra("hairLength" ,  diaries.get(position).getHairLength());
            startActivity(intent);

            return true;
        }
    };




}