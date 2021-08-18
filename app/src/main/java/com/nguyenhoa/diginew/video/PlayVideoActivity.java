package com.nguyenhoa.diginew.video;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.adapter.CmtAdapter;
import com.nguyenhoa.diginew.adapter.NewsRCAdapter;
import com.nguyenhoa.diginew.common.MyClass;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.Comment;
import com.nguyenhoa.diginew.model.News;

import java.util.ArrayList;

import static com.nguyenhoa.diginew.R.string.text_error_video;
import static com.nguyenhoa.diginew.R.string.text_video_completion;

public class PlayVideoActivity extends AppCompatActivity implements NewsRCAdapter.ItemNewsRCClickListener {
    private VideoView videoView;
    private MediaController controller;
    private TextView tvTitleNews, tvSource, tvTime, tvTopic, tvLikes, tvCmts, tvContent;
    private EditText etCmt;
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
        init();
        setVideo(news);
    }

    private void setListRelevantVideo(News news) {
        ArrayList<News> list = MyList.sqLite.getAllNewsRelevant(news);
//        for(int i=0; i<MyList.lists_video.size(); i++){
//            for(int j = 0; j< MyList.lists_video.get(i).size(); j++)
//                list.add(MyList.lists_video.get(i).get(j));
//        }

        adapter = new NewsRCAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter.setData(list);
        adapter.setClickNewsListener(this::onItemClick);
        recyclerView.setAdapter(adapter);
    }

    private void init() {
        etCmt = findViewById(R.id.etCmt);
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
    }

    private void setClick() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvCmts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLayoutCmt(view);
            }
        });
        etCmt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                setLayoutCmt(view);
            }
        });
        tvLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyClass.setTVLike(tvLikes, getApplicationContext());
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getApplicationContext() != null) {
            finish();
//            dialog = null;
        }
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
                Toast.makeText(getApplicationContext(), text_video_completion
                        , Toast.LENGTH_LONG).show();
            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(getApplicationContext(),
                        text_error_video, Toast.LENGTH_LONG).show();
                return false;
            }
        });

        tvTopic.setText(news.getTopic().getName());
        tvTitleNews.setText(news.getTitle());
//        tvContent.setText(news.getContent());
        tvSource.setText(news.getSource());
//        tvTime.setText(news.getTimes()+" "+getResources().getString(R.string.time));
        tvTime.setText(news.getTimes());
        tvLikes.setText(String.valueOf(news.getLikes()));
        tvCmts.setText(String.valueOf(news.getCmts()));
        setClick();
        setListRelevantVideo(news);
    }

    @Override
    public void onItemClick(View view, int position) {
        News news = adapter.getItem(position);
        setVideo(news);
//        Intent intent = new Intent(this, NewsActivity.class);
//        intent.putExtra("News", adapter.getItem(position));
//        startActivity(intent);
    }

    private void setLayoutCmt(View v){
        View view1 = getLayoutInflater().inflate(R.layout.layout_cmt, null);

        ArrayList<Comment> list = MyList.list_Cmt;
        RecyclerView rv;
        CmtAdapter adapter;
        ImageView ivClose;
        TextView tvSend;
        EditText etCmt;

        etCmt = view1.findViewById(R.id.etCmt);
        tvSend = view1.findViewById(R.id.tvSend);
        rv = view1.findViewById(R.id.rvCmt);
        ivClose = view1.findViewById(R.id.ivClose);
        adapter = new CmtAdapter(list, v.getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),
                RecyclerView.VERTICAL, false);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);

        Dialog dialog = new Dialog(v.getContext(), R.style.MaterialDialogSheet);
        dialog.setContentView(view1);
        dialog.setCancelable(true);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.show();

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        etCmt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                adapter.sendNewCmt(v.getContext(), list.size(), false);
            }
        });
        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = etCmt.getText().toString();
                etCmt.setText("");
                adapter.displayNewCmt(s, list.size(), false);
            }
        });
    }
}