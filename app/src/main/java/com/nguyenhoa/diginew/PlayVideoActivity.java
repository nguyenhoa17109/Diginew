package com.nguyenhoa.diginew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.nguyenhoa.diginew.adapter.NewsRCAdapter;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.News;

public class PlayVideoActivity extends AppCompatActivity implements NewsRCAdapter.ItemNewsRCClickListener {
    private VideoView videoView;
    private MediaController controller;
    private TextView tvTitleNews, tvSource, tvTime, tvTopic, tvLikes, tvCmts, tvContent;
    private ImageView ivAccount, ivShare, ivBack;
    private RecyclerView recyclerView;
    private NewsRCAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        getSupportActionBar().hide();
        Intent intent = getIntent();
        News news = (News) intent.getSerializableExtra("video");
        begin();


        setVideo(news);
        tvTopic.setText(news.getTopic());
        tvTitleNews.setText(news.getTitle());
//        tvContent.setText(news.getContent());
        tvSource.setText(news.getSource());
        tvTime.setText(news.getTimes()+" "+getResources().getString(R.string.time));
        tvLikes.setText(String.valueOf(news.getLikes()));
        tvCmts.setText(String.valueOf(news.getCmts()));

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void begin() {
        videoView = findViewById(R.id.video);
        tvTopic = findViewById(R.id.tvTopic);
        tvTitleNews = findViewById(R.id.tvVideoTiltle);
        tvSource = findViewById(R.id.tvSource);
        tvTime = findViewById(R.id.tvTimes);
        tvCmts = findViewById(R.id.tvCmt);
        tvLikes = findViewById(R.id.tvLike);
//        tvContent = findViewById(R.id.tvContent);

        ivAccount = findViewById(R.id.ivAccount);
        ivShare = findViewById(R.id.ivShare);

        ivBack = findViewById(R.id.ivBack);
        recyclerView = findViewById(R.id.rcNews);
        adapter = new NewsRCAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter.setData(MyList.listNews);
        adapter.setClickNewsListener(this::onItemClick);
        recyclerView.setAdapter(adapter);
    }

    private void setVideo(News news) {
        if (controller == null) {
            controller = new MediaController(PlayVideoActivity.this);
            controller.setAnchorView(videoView);
        }
        videoView.setMediaController(controller);
        videoView.setVideoURI(Uri.parse(news.getUrl()));
        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(), "Thank You...!!!", Toast.LENGTH_LONG).show();
            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(getApplicationContext(),
                        "Oops An Error Occur While Playing Video...!!!", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, NewsActivity.class);
        intent.putExtra("News", adapter.getItem(position));
        startActivity(intent);
    }
}