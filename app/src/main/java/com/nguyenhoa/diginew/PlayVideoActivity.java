package com.nguyenhoa.diginew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.nguyenhoa.diginew.adapter.NewsRCAdapter;
import com.nguyenhoa.diginew.model.News;

import java.util.ArrayList;
import java.util.Arrays;

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
        adapter.setData(setList());
        adapter.setClickNewsListener(this::onItemClick);
        recyclerView.setAdapter(adapter);
    }
    int index = 0;
    private void setVideo(News news) {

        if (controller == null) {
            // create an object of media controller class
            controller = new MediaController(PlayVideoActivity.this);
            controller.setAnchorView(videoView);
        }
        // set the media controller for video view
        videoView.setMediaController(controller);
        // set the uri for the video view
        videoView.setVideoURI(Uri.parse(news.getUrl()));
        // start a video
        videoView.start();

        // implement on completion listener on video view
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(), "Thank You...!!!", Toast.LENGTH_LONG).show(); // display a toast when an video is completed
            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(getApplicationContext(), "Oops An Error Occur While Playing Video...!!!", Toast.LENGTH_LONG).show(); // display a toast when an error is occured while playing an video
                return false;
            }
        });
    }

    private ArrayList<News> setList(){
        ArrayList<News> listNews = new ArrayList<>();
        listNews.add(new News("Tin tuc", "text", "Vietnamnet", 6,
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