package com.nguyenhoa.diginew.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.adapter.NewsRCAdapter;
import com.nguyenhoa.diginew.common.CustomItemAnimator;
import com.nguyenhoa.diginew.common.MyClass;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.common.NewsCallBack;
import com.nguyenhoa.diginew.model.News;

public class User_Free_Activity extends AppCompatActivity implements View.OnClickListener, NewsCallBack {
    private ImageView ivBack, ivSetting;
    private RecyclerView rcRecentNews, rcDowloadedNews, rcLikedNews, rcSavedNews;
    private NewsRCAdapter RecentNews_adapter, DowloadedNews_adapter, LikedNews_adapter, SavedNews_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_free);
        init();
        setOnClick();
    }

    public void setOnClick() {
        ivBack.setOnClickListener(this);
        ivSetting.setOnClickListener(this);
    }

    public void init() {
        getSupportActionBar().hide();
        ivBack = findViewById(R.id.ivBack);
        ivSetting = findViewById(R.id.ivSetting);
        rcRecentNews = findViewById(R.id.rcRecentNews);
        rcDowloadedNews = findViewById(R.id.rcDowloadedNews);
        rcLikedNews = findViewById(R.id.rcLikedNews);
        rcSavedNews = findViewById(R.id.rcSavedNews);
        setRV();
    }

    public void settingOnClick() {
        Intent in = new Intent(this, UserSettingActivity.class);
        startActivity(in);
    }
    private void setRV() {
        RecentNews_adapter = new NewsRCAdapter(this);
        DowloadedNews_adapter = new NewsRCAdapter(this);
        LikedNews_adapter = new NewsRCAdapter(this);
        SavedNews_adapter = new NewsRCAdapter(this);

        LinearLayoutManager manager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        LinearLayoutManager manager2 = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        LinearLayoutManager manager3 = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        LinearLayoutManager manager4 = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        rcRecentNews.setLayoutManager(manager);
        rcDowloadedNews.setLayoutManager(manager2);
        rcLikedNews.setLayoutManager(manager3);
        rcSavedNews.setLayoutManager(manager4);

        RecentNews_adapter.setData(MyList.listNews, this::onNewsItemClick);
        DowloadedNews_adapter.setData(MyList.listNews, this::onNewsItemClick);
        LikedNews_adapter.setData(MyList.listNews, this::onNewsItemClick);
        SavedNews_adapter.setData(MyList.listNews, this::onNewsItemClick);
//        adapter.setClickNewsListener(this::onItemClick);
        rcRecentNews.setAdapter(RecentNews_adapter);
        rcRecentNews.setItemAnimator(new CustomItemAnimator());
        rcDowloadedNews.setAdapter(DowloadedNews_adapter);
        rcDowloadedNews.setItemAnimator(new CustomItemAnimator());
        rcLikedNews.setAdapter(LikedNews_adapter);
        rcLikedNews.setItemAnimator(new CustomItemAnimator());
        rcSavedNews.setAdapter(SavedNews_adapter);
        rcSavedNews.setItemAnimator(new CustomItemAnimator());

    }
    @Override
    public void onClick(View v) {
        if (v.getId() == ivBack.getId()) {
            finish();
        } else if (v.getId() == ivSetting.getId()) {
            settingOnClick();
        }
    }

    @Override
    public void onNewsItemClick(int pos, TextView ivTopic, TextView ivSource, TextView tvTime) {
        News news = RecentNews_adapter.getItem(pos);
        News news2 = DowloadedNews_adapter.getItem(pos);
        News news3 = SavedNews_adapter.getItem(pos);
        News news4 = LikedNews_adapter.getItem(pos);
        MyClass.setIntent(news, this, ivTopic, ivSource, tvTime);
        MyClass.setIntent(news2, this, ivTopic, ivSource, tvTime);
        MyClass.setIntent(news3, this, ivTopic, ivSource, tvTime);
        MyClass.setIntent(news4, this, ivTopic, ivSource, tvTime);
    }
}