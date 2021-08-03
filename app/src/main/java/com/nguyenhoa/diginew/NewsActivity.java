package com.nguyenhoa.diginew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nguyenhoa.diginew.adapter.CmtAdapter;
import com.nguyenhoa.diginew.adapter.NewsRCAdapter;
import com.nguyenhoa.diginew.common.MyClass;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.Comment;
import com.nguyenhoa.diginew.model.News;
import com.nguyenhoa.diginew.model.Operation;
import com.nguyenhoa.diginew.model.User;

import java.util.ArrayList;

import static com.nguyenhoa.diginew.R.drawable.background_button;
import static com.nguyenhoa.diginew.R.drawable.background_disable_button;

public class NewsActivity extends AppCompatActivity implements NewsRCAdapter.ItemNewsRCClickListener{
    private TextView tvTitleNews, tvSource, tvTime, tvTopic, tvLikes, tvCmts, tvContent;
    private ImageView ivAccount, ivShare, ivBack;
    private EditText etCmt;
    private RecyclerView recyclerView;
    private NewsRCAdapter adapter;
    private News news;
    private SharedPreferences preferences;
    public static final String MyPREFERENCES = "CONTENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Intent intent = getIntent();
        news = (News) intent.getSerializableExtra("text");
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_news);
        getSupportActionBar().setElevation(0);
        View view = getSupportActionBar().getCustomView();

        init();

        if(news != null){
            tvTopic.setText(news.getTopic());
            tvTitleNews.setText(news.getTitle());
            tvContent.setText(news.getContent());
            tvSource.setText(news.getSource());
//            tvTime.setText(news.getTimes()+" "+getResources().getString(R.string.time));
            tvTime.setText(news.getTimes());
            tvLikes.setText(String.valueOf(news.getLikes()));
            tvCmts.setText(String.valueOf(news.getCmts()));

            int size = setSize(preferences.getInt("size_level", 0));
            int id = preferences.getInt("font", R.font.roboto);
            Typeface typeface = ResourcesCompat.getFont(NewsActivity.this, id);
            tvContent.setTypeface(typeface);
            tvContent.setTextSize(size);

        }else{
            Toast.makeText(this, "News is empty!!!", Toast.LENGTH_SHORT).show();
        }

        setClick();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar_news, menu);
        String s = news.toString();
        for(Operation operation:MyList.listOperation){
            if(operation.getNews().toString().equals(s))
                menu.findItem(R.id.aDownload).setIcon(R.drawable.ic_downloaded);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.aRestore:{
                Toast.makeText(this, "Đã lưu tin", Toast.LENGTH_SHORT).show();
//                Toast.makeText(this, ""+MyList.today, Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.aDownload:{
                Operation operation = new Operation(news, MyList.today, false, true);

                Toast.makeText(this, "Đã tải tin", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewsActivity.this, NewsDownloadedActivity.class);
                intent.putExtra("download", operation);
                startActivity(intent);
                break;
            }
            case R.id.aFormatSize:{
                changeSize();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
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

    private void changeSize() {
        View view = getLayoutInflater().inflate(R.layout.layout_changesize, null);
        SeekBar seekBar = view.findViewById(R.id.seekBar);
        Button bt1, bt2;
        bt1 = view.findViewById(R.id.bt1);
        bt2 = view.findViewById(R.id.bt2);

        final int[] level = {preferences.getInt("size_level", 0)};
        int id = preferences.getInt("font", R.font.lora);
        seekBar.setProgress(level[0]);

        if(id != R.font.lora) {
            bt1.setBackgroundResource(background_button);
            bt2.setBackgroundResource(background_disable_button);
        }else{
            bt2.setBackgroundResource(background_button);
            bt1.setBackgroundResource(background_disable_button);
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

        Dialog dialog = new Dialog(this, R.style.MaterialDialogSheet);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDisplayButton(bt1, bt2, seekBar, R.font.roboto);
                dialog.dismiss();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDisplayButton(bt2, bt1, seekBar, R.font.lora);
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.show();
    }

    private void setDisplayButton(Button bt1, Button bt2, SeekBar seekBar, int font) {
        bt1.setBackgroundResource(background_button);
        bt2.setBackgroundResource(background_disable_button);
        int size = setSize(seekBar.getProgress());

        tvContent.setTextSize(size);
        Typeface typeface;
        if(font == R.font.roboto)
            typeface = ResourcesCompat.getFont(NewsActivity.this, R.font.roboto);
        else
            typeface = ResourcesCompat.getFont(NewsActivity.this, R.font.lora);
        tvContent.setTypeface(typeface);

        updateText(seekBar.getProgress(), font);
    }

    private int setSize(int progress) {
        switch (progress){
            case 1:
                return 24;
            case 2:
                return 30;
            default:
                return 18;
        }
    }

    public void init(){
        preferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        tvTitleNews = findViewById(R.id.tvTitleNews);
        tvSource = findViewById(R.id.tvNewsSource);
        tvTime = findViewById(R.id.tvNewsTime);
        tvCmts = findViewById(R.id.tvCmt);
        tvLikes = findViewById(R.id.tvLike);
        tvContent = findViewById(R.id.tvContent);

        etCmt = findViewById(R.id.etCmt);

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
        MyClass.setIntent(adapter.getItem(position), (Activity) view.getContext());
//        Intent intent = new Intent(this, NewsActivity.class);
//        intent.putExtra("News", adapter.getItem(position));
//        startActivity(intent);
    }

    private void updateText(int size, int id){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("size_level", size);
        editor.putInt("font", id);
        editor.apply();
    }
}