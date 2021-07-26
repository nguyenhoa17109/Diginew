package com.nguyenhoa.diginew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nguyenhoa.diginew.adapter.NewsRCAdapter;
import com.nguyenhoa.diginew.model.News;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity implements NewsRCAdapter.ItemNewsRCClickListener{
    private TextView tvTitleNews, tvSource, tvTime, tvTopic, tvLikes, tvCmts, tvContent;
    private ImageView ivAccount, ivShare, ivBack;
    private RecyclerView recyclerView;
    private NewsRCAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_news);
        getSupportActionBar().setElevation(0);
        View view = getSupportActionBar().getCustomView();

        init();

        Intent intent = getIntent();
        News news = (News) intent.getSerializableExtra("News");
        if(news != null){
            tvTopic.setText(news.getTopic());
            tvTitleNews.setText(news.getTitle());
            tvContent.setText(news.getContent());
            tvSource.setText(news.getSource());
            tvTime.setText(news.getTimes()+" "+getResources().getString(R.string.time));
            tvLikes.setText(String.valueOf(news.getLikes()));
            tvCmts.setText(String.valueOf(news.getCmts()));


        }else{
            Toast.makeText(this, "LOLO", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar_news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.aRestore:{
                Toast.makeText(this, "Đã lưu tin", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.aDownload:{
                Toast.makeText(this, "Đã tải tin", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.aFormatSize:{
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    public void init(){
        tvTitleNews = findViewById(R.id.tvTitleNews);
        tvSource = findViewById(R.id.tvNewsSource);
        tvTime = findViewById(R.id.tvNewsTime);
        tvCmts = findViewById(R.id.tvCmt);
        tvLikes = findViewById(R.id.tvLike);
        tvContent = findViewById(R.id.tvContent);

        ivAccount = findViewById(R.id.ivAccount);
        ivShare = findViewById(R.id.ivShare);

        tvTopic = getSupportActionBar().getCustomView().findViewById(R.id.tvNewsTopic);
        ivBack = getSupportActionBar().getCustomView().findViewById(R.id.ivBack);

        recyclerView = findViewById(R.id.rcNews);
        adapter = new NewsRCAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter.setData(setList());
        adapter.setClickNewsListener(this::onItemClick);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<News> setList(){
        ArrayList<News> listNews = new ArrayList<>();
        listNews.add(new News("Thoi su", "text", "Vietnamnet", 6,
                "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 100, 200,
                R.drawable.img_clip, "abc"));
        listNews.add(new News("Thoi su","text", "Vietnamnet", 6,
                "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 100, 200,
                R.drawable.img_clip, "abc"));
        return listNews;
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, NewsActivity.class);
        intent.putExtra("News", adapter.getItem(position));
        startActivity(intent);
    }
}