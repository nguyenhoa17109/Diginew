package com.nguyenhoa.diginew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.nguyenhoa.diginew.adapter.NewsDownloadedAdapter;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.Operation;

import java.util.ArrayList;

public class NewsDownloadedActivity extends AppCompatActivity {
    private ImageView ivBack;
    private RecyclerView rvDownload;
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

        ArrayList<ArrayList<Operation>> lists = MyList.setListDownload(lst);
        ivBack = findViewById(R.id.ivBack);
        rvDownload = findViewById(R.id.rvNewsDownload);
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