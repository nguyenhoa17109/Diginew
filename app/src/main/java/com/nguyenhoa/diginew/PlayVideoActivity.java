package com.nguyenhoa.diginew;

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

import com.nguyenhoa.diginew.adapter.CmtAdapter;
import com.nguyenhoa.diginew.adapter.NewsRCAdapter;
import com.nguyenhoa.diginew.common.MyClass;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.Comment;
import com.nguyenhoa.diginew.model.News;
import com.nguyenhoa.diginew.news.NewsActivity;

import java.util.ArrayList;

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
        begin();


        setVideo(news);
        tvTopic.setText(news.getTopic());
        tvTitleNews.setText(news.getTitle());
//        tvContent.setText(news.getContent());
        tvSource.setText(news.getSource());
        tvTime.setText(news.getTimes()+" "+getResources().getString(R.string.time));
        tvLikes.setText(String.valueOf(news.getLikes()));
        tvCmts.setText(String.valueOf(news.getCmts()));
        setClick();
    }

    private void begin() {
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
        adapter = new NewsRCAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter.setData(MyList.listNews);
        adapter.setClickNewsListener(this::onItemClick);
        recyclerView.setAdapter(adapter);
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