package com.nguyenhoa.diginew.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.adapter.CmtAdapter;
import com.nguyenhoa.diginew.adapter.NewsRCAdapter;
import com.nguyenhoa.diginew.common.MyClass;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.Comment;
import com.nguyenhoa.diginew.model.News;

import java.io.IOException;
import java.util.ArrayList;

public class AudioNewsActivity extends AppCompatActivity implements NewsRCAdapter.ItemNewsRCClickListener {
    private ImageView ivBack, ivPlayPause, ivAccount, ivShare;
    private EditText etCmt;
    private TextView tvType, tvLikes, tvCmts;
    private RecyclerView recyclerView;
    private ArrayList<News> list1;
    private News news;
    private NewsRCAdapter newsRCAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_news);

        getSupportActionBar().hide();
        init();

        Intent intent = getIntent();
        news = (News) intent.getSerializableExtra("audio");

        addFragment(new AudioNewsFragment());

        tvType.setText(news.getTopic());

        tvLikes.setText(String.valueOf(news.getLikes()));
        tvCmts.setText(String.valueOf(news.getCmts()));


        setClick();
    }


    public void addFragment(Fragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("audio", news);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.frAudioNews, fragment).commit();
    }

    private void init(){

        ivBack = findViewById(R.id.ivBack);
        ivAccount = findViewById(R.id.ivAccount);
        ivShare = findViewById(R.id.ivShare);
        tvType = findViewById(R.id.tvType);

        tvCmts = findViewById(R.id.tvCmt);
        tvLikes = findViewById(R.id.tvLike);
        etCmt = findViewById(R.id.etCmt);

        recyclerView = findViewById(R.id.rcNews);

        ArrayList<News> list = MyList.listNews;

        list1 = new ArrayList<>();
        for (int i=0; i<list.size(); i++){
            if(list.get(i).getType().equals("audio")){
                list1.add(list.get(i));
            }
        }
        newsRCAdapter = new NewsRCAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        newsRCAdapter.setData(list1);
        newsRCAdapter.setClickNewsListener(this::onItemClick);

        recyclerView.setAdapter(newsRCAdapter);
    }



    private void setClick() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mediaPlayer.stop();
//                mediaPlayer.release();
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

    @Override
    public void onItemClick(View view, int position) {
        MyClass.setIntent(newsRCAdapter.getItem(position), (Activity) view.getContext());
    }
}