package com.nguyenhoa.diginew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nguyenhoa.diginew.model.News;

public class NewsActivity extends AppCompatActivity {
    private TextView tvTitleNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

//        getSupportActionBar().hide();
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_news);
        getSupportActionBar().setElevation(0);
        View view = getSupportActionBar().getCustomView();

        tvTitleNews = findViewById(R.id.tvTitleNews);
        Intent intent = getIntent();
        News news = (News) intent.getSerializableExtra("News");
        tvTitleNews.setText(news.getTitle());
//        getO
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.ic
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
}