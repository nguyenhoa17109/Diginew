package com.nguyenhoa.diginew.news;

import androidx.appcompat.app.AppCompatActivity;
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
import com.nguyenhoa.diginew.common.NewsCallBack;
import com.nguyenhoa.diginew.model.Comment;
import com.nguyenhoa.diginew.model.News;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AudioNewsActivity extends AppCompatActivity implements NewsCallBack {
    private ImageView ivBack, ivPlayPause, ivAccount, ivShare;
    private EditText etCmt;
    private TextView tvTitleNews, tvSource, tvTime, tvType, tvLikes, tvCmts;
    private RecyclerView recyclerView;
    private ArrayList<News> list1;
    private NewsRCAdapter newsRCAdapter;
    private String url;
    private View thumbView;
    private Dialog dialog;

    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private Handler handler = new Handler();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_news);

        getSupportActionBar().hide();
        init();

        Intent intent = getIntent();
        News news = (News) intent.getSerializableExtra("audio");

        tvType.setText(news.getTopic().getName());
        tvTitleNews.setText(news.getTitle());
        tvSource.setText(news.getSource());
        tvTime.setText(news.getTimes()+" "+getResources().getString(R.string.time));
        tvLikes.setText(String.valueOf(news.getLikes()));
        tvCmts.setText(String.valueOf(news.getCmts()));
        url = news.getUrl(); // your URL here

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        mediaPlayer.setLooping(true);

        prepareMediaPlayer();

        ivPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    handler.removeCallbacks(updater);
                    mediaPlayer.pause();
                    ivPlayPause.setImageResource(R.drawable.ic_play_circle);
                }
                else{
                    mediaPlayer.start();
                    ivPlayPause.setImageResource(R.drawable.ic_pause_circle);
                    updateSeekBar();
                }
            }
        });


        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                SeekBar seekBar = (SeekBar) v;
                int playPostion = (mediaPlayer.getDuration() / 100) * seekBar.getProgress();
                mediaPlayer.seekTo(playPostion);

                seekBar.setThumb(getThumb(milliSecondsToTimer(mediaPlayer.getCurrentPosition()), milliSecondsToTimer(mediaPlayer.getDuration())));

                return false;
            }
        });

//        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
//            @Override
//            public void onBufferingUpdate(MediaPlayer mp, int percent) {
//                seekBar.setSecondaryProgress(percent);
//            }
//        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                seekBar.setProgress(0);
                ivPlayPause.setImageResource(R.drawable.ic_play_circle);
                mediaPlayer.reset();
                prepareMediaPlayer();
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
                finish();

            }
        });

        setClick();
    }

    private void prepareMediaPlayer(){
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            seekBar.setThumb(getThumb("0:00", milliSecondsToTimer(mediaPlayer.getDuration())));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            try {
                if(mediaPlayer.isLooping() || mediaPlayer.isPlaying()){
                    updateSeekBar();
                    long currentDuration = mediaPlayer.getCurrentPosition();
                    seekBar.setThumb(getThumb(milliSecondsToTimer(currentDuration), milliSecondsToTimer(mediaPlayer.getDuration())));
                }
                else{
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }

            } catch (IllegalStateException e) {
                e.printStackTrace();
            }

        }
    };

    private void updateSeekBar(){
        if(mediaPlayer.isPlaying()){
            seekBar.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) *100));
            handler.postDelayed(updater, 500);
        }
    }

    public String milliSecondsToTimer(long milliseconds){
        String finalTimerString = "";
        String secondsString = "";

        int hours = (int)( milliseconds / (1000*60*60));
        int minutes = (int)(milliseconds % (1000*60*60)) / (1000*60);
        int seconds = (int) ((milliseconds % (1000*60*60)) % (1000*60) / 1000);
        if(hours > 0){
            finalTimerString = hours + ":";
        }
        if(seconds < 10){
            secondsString = "0" + seconds;
        }else{
            secondsString = "" + seconds;}
        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        return finalTimerString;
    }


    private void init(){
        thumbView = LayoutInflater.from(AudioNewsActivity.this).inflate(R.layout.seekbar_thumb, null, false);

        ivBack = findViewById(R.id.ivBack);
        ivAccount = findViewById(R.id.ivAccount);
        ivShare = findViewById(R.id.ivShare);
        tvType = findViewById(R.id.tvType);
        tvTitleNews = findViewById(R.id.tvAudioTiltle);
        tvSource = findViewById(R.id.tvSource);
        tvTime = findViewById(R.id.tvTimes);
        tvCmts = findViewById(R.id.tvCmt);
        tvLikes = findViewById(R.id.tvLike);
        etCmt = findViewById(R.id.etCmt);

        ivPlayPause = findViewById(R.id.ivPlayPause);
        seekBar = findViewById(R.id.seekBar);

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
        newsRCAdapter.setData(list1, this::onNewsItemClick);
//        newsRCAdapter.setClickNewsListener(this::onItemClick);

        recyclerView.setAdapter(newsRCAdapter);
    }

    public Drawable getThumb(String t1, String t2) {
        ((TextView) thumbView.findViewById(R.id.tvProgress)).setText(t1 + "/"+ t2);

        thumbView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        Bitmap bitmap = Bitmap.createBitmap(thumbView.getMeasuredWidth(), thumbView.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        thumbView.layout(0, 0, thumbView.getMeasuredWidth(), thumbView.getMeasuredHeight());
        thumbView.draw(canvas);

        return new BitmapDrawable(getResources(), bitmap);
    }

    private void setClick() {

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

        dialog = new Dialog(v.getContext(), R.style.MaterialDialogSheet);
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
    public void onDestroy() {
        super.onDestroy();
        if ( dialog!=null && dialog.isShowing() ){
            dialog.cancel();
        }
    }

//    @Override
//    public void onItemClick(View view, int position, TextView tv) {
//        News news = newsRCAdapter.getItem(position);
//        MyClass.setIntent(news, (Activity) view.getContext(), tv);
//    }

    @Override
    public void onNewsItemClick(int pos, TextView ivTopic, TextView ivSource, TextView tvTime) {
        News news = newsRCAdapter.getItem(pos);
        MyClass.setIntent(news, this, ivTopic, ivSource, tvTime);
    }
}