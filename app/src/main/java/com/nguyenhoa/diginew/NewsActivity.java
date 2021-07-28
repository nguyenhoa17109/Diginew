package com.nguyenhoa.diginew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nguyenhoa.diginew.adapter.NewsRCAdapter;
import com.nguyenhoa.diginew.model.MyList;
import com.nguyenhoa.diginew.model.News;

import java.util.ArrayList;

import static com.nguyenhoa.diginew.R.drawable.background_button;
import static com.nguyenhoa.diginew.R.drawable.background_disable_button;

public class NewsActivity extends AppCompatActivity implements NewsRCAdapter.ItemNewsRCClickListener{
    private TextView tvTitleNews, tvSource, tvTime, tvTopic, tvLikes, tvCmts, tvContent;
    private ImageView ivAccount, ivShare, ivBack;
    private RecyclerView recyclerView;
    private NewsRCAdapter adapter;
    private News news;
    private SharedPreferences preferences;
    public static final String MyPREFERENCES = "CONTENT";

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
        news = (News) intent.getSerializableExtra("News");
        if(news != null){
            tvTopic.setText(news.getTopic());
            tvTitleNews.setText(news.getTitle());
            tvContent.setText(news.getContent());
            tvSource.setText(news.getSource());
            tvTime.setText(news.getTimes()+" "+getResources().getString(R.string.time));
            tvLikes.setText(String.valueOf(news.getLikes()));
            tvCmts.setText(String.valueOf(news.getCmts()));

            int size = preferences.getInt("size", 18);
            int id = preferences.getInt("font", R.font.roboto);
            Typeface typeface = ResourcesCompat.getFont(NewsActivity.this, id);
            tvContent.setTypeface(typeface);
            tvContent.setTextSize(size);

        }else{
            Toast.makeText(this, "News is empty!!!", Toast.LENGTH_SHORT).show();
        }

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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
                break;
            }
            case R.id.aDownload:{
                Toast.makeText(this, "Đã tải tin", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.aFormatSize:{
                changeSize();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void changeSize() {
        final int[] level = new int[1];
        View view = getLayoutInflater().inflate(R.layout.layout_changesize, null);
        SeekBar seekBar = view.findViewById(R.id.seekBar);
        Button bt1, bt2;
        bt1 = view.findViewById(R.id.bt1);
        bt2 = view.findViewById(R.id.bt2);
        seekBar.setProgress(0);

        Dialog dialog = new Dialog(this, R.style.MaterialDialogSheet);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.show();

        int size = preferences.getInt("size", 18);
        int id = preferences.getInt("font", R.font.lora);
        switch (size){
            case 18:
                seekBar.setProgress(0);
                break;
            case 24:
                seekBar.setProgress(1);
                break;
            case 30:
                seekBar.setProgress(2);
                break;
        }
        Log.d("LOO", R.font.lora+"//"+id);
        if(id == R.font.lora) {
            bt2.setBackgroundResource(background_button);
            bt1.setBackgroundResource(background_disable_button);
        }else{
            bt1.setBackgroundResource(background_button);
            bt2.setBackgroundResource(background_disable_button);
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                level[0] = seekBar.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        bt2.setBackgroundResource(background_button);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt1.setBackgroundResource(background_button);
                bt2.setBackgroundResource(background_disable_button);

                int size = 18;
                switch (level[0]){
                    case 0:
                        size = 18;
                        break;
                    case 1:
                        size = 24;
                        break;
                    case 2:
                        size = 30;
                        break;
                }
                tvContent.setTextSize(size);
//                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/roboto.ttf");
                Typeface typeface = ResourcesCompat.getFont(NewsActivity.this, R.font.roboto);
                tvContent.setTypeface(typeface);

                updateText(size, R.font.roboto);
                dialog.dismiss();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt2.setBackgroundResource(background_button);
                bt1.setBackgroundResource(background_disable_button);

                int size = 18;
                switch (level[0]){
                    case 0:
                        size = 18;
                        break;
                    case 1:
                        size = 24;
                        break;
                    case 2:
                        size = 30;
                        break;
                }

                tvContent.setTextSize(size);

//                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/lora.ttf");
                Typeface typeface = ResourcesCompat.getFont(NewsActivity.this, R.font.lora);
                tvContent.setTypeface(typeface);

                updateText(size, R.font.roboto);

                dialog.dismiss();
            }
        });
    }

    public void init(){
        preferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

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
        adapter.setData(MyList.listNews);
        adapter.setClickNewsListener(this::onItemClick);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, NewsActivity.class);
        intent.putExtra("News", adapter.getItem(position));
        startActivity(intent);
    }

    private void updateText(int size, int id){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("size", size);
        editor.putInt("font", id);
        editor.apply();
    }
}