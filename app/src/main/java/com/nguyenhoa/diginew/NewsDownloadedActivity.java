package com.nguyenhoa.diginew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nguyenhoa.diginew.adapter.NewsDownloadedAdapter;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.Operation;

import java.util.ArrayList;

public class NewsDownloadedActivity extends AppCompatActivity {
    private ImageView ivBack;
    private RecyclerView rvDownload;
    private LinearLayout layout;
    private NewsDownloadedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_downloaded);

        getSupportActionBar().hide();
        Intent intent = getIntent();
        Operation operation = (Operation) intent.getSerializableExtra("download");
        ArrayList<Operation> lst = MyList.listOperation;
        lst.add(operation);

        ivBack = findViewById(R.id.ivBack);
        rvDownload = findViewById(R.id.rvNewsDownload);
        layout = findViewById(R.id.layoutDownload);
        if(lst.isEmpty()){
            layout.setVisibility(View.VISIBLE);
            rvDownload.setVisibility(View.GONE);
        }else{
            layout.setVisibility(View.GONE);
            rvDownload.setVisibility(View.VISIBLE);
        }

        ArrayList<ArrayList<Operation>> lists = MyList.setListDownload(lst);

        adapter = new NewsDownloadedAdapter(lists, this);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),
                RecyclerView.VERTICAL, false);
        rvDownload.setLayoutManager(manager);
        rvDownload.setAdapter(adapter);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}