package com.nguyenhoa.diginew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyenhoa.diginew.adapter.NewsInfoRCAdapter;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.News;

public class InfoNewsActivity extends AppCompatActivity {
    private ImageView ivBack, ivInfo;
    private TextView tvTopic;
    private RecyclerView rv;
    private NewsInfoRCAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_news);

        getSupportActionBar().hide();
        init();

        Intent intent = getIntent();
        News news = (News) intent.getSerializableExtra("info");
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ivInfo.setImageResource(news.getImgs());
        tvTopic.setText(news.getTopic());
    }

    private void init(){
        ivBack = findViewById(R.id.ivBack);
        ivInfo = findViewById(R.id.tvNewsInfo);
        tvTopic = findViewById(R.id.tvTopic);
        rv = findViewById(R.id.rcNews);

        adapter = new NewsInfoRCAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        rv.setLayoutManager(manager);
        adapter.setData(MyList.listNews);
        rv.setAdapter(adapter);
    }
}