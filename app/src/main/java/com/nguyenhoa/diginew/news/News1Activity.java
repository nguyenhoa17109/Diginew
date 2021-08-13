package com.nguyenhoa.diginew.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.adapter.NewsRCAdapter;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.News;
import com.nguyenhoa.diginew.model.Operation;

import java.util.ArrayList;

import static com.nguyenhoa.diginew.R.drawable.background_button;
import static com.nguyenhoa.diginew.R.drawable.background_disable_button;

public class News1Activity extends AppCompatActivity implements NewsRCAdapter.ItemNewsRCClickListener{
    private News news;
    public TextView tvTopic;
    public ImageView ivBack;
    private RecyclerView recyclerView;
    private NewsRCAdapter adapter;
    public boolean isNews;
    private SharedPreferences preferences;
    public static final String MyPREFERENCES = "CONTENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news1);

        Intent intent = getIntent();
        news = (News) intent.getSerializableExtra("text");
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_news);
        getSupportActionBar().setElevation(0);

        tvTopic = getSupportActionBar().getCustomView().findViewById(R.id.tvNewsTopic);
        ivBack = getSupportActionBar().getCustomView().findViewById(R.id.ivBack);
        preferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        recyclerView = findViewById(R.id.rcNews);
        adapter = new NewsRCAdapter(this);

        tvTopic.setText(news.getTopic());
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check like and restore to db
                finish();
//                getSupportFragmentManager().popBackStack();
            }
        });

        addFragment(new NewsFragment(), true);
    }

    public void addFragment(Fragment fragment, boolean b) {
        recyclerView.setVisibility(View.VISIBLE);
        isNews = b;
        invalidateOptionsMenu();
        Bundle bundle = new Bundle();
        bundle.putSerializable("text", news);
        fragment.setArguments(bundle);
        FragmentManager fmgr = getSupportFragmentManager();
        FragmentTransaction ft = fmgr.beginTransaction();
        ft.add(R.id.fragment, fragment);
        ft.addToBackStack(fragment.getClass().getSimpleName());
        ft.commit();
    }

    private void setOperationFrag(Bundle bundle){
        isNews = false;
        invalidateOptionsMenu();
        DownloadedNewsFragment fragment = new DownloadedNewsFragment();
        fragment.setArguments(bundle);
        FragmentManager fmgr = getSupportFragmentManager();
        FragmentTransaction ft = fmgr.beginTransaction();
        ft.add(R.id.fragment, fragment);
        ft.addToBackStack(fragment.getClass().getSimpleName());
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar_news, menu);

        String s = news.toString();
        for(Operation operation: MyList.listOperation){
            if(operation.getNews().toString().equals(s))
                menu.findItem(R.id.aDownload).setIcon(R.drawable.ic_downloaded);
        }

        if(!isNews){
            menu.findItem(R.id.aDownload).setVisible(false);
            menu.findItem(R.id.aFormatSize).setVisible(false);
            menu.findItem(R.id.aRestore).setVisible(false);
        }

        setListRelevance(news);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.aRestore:{
//                Toast.makeText(this, "Đã lưu tin", Toast.LENGTH_SHORT).show();
                item.setIcon(R.drawable.ic_saved);
//                boolean isLike = isLiked(tvLikes);
                tvTopic.setText("Tin đã lưu");
                recyclerView.setVisibility(View.GONE);
                Operation operation = new Operation(news, MyList.today, false, true, false);
//                Intent intent = new Intent(News1Activity.this, NewsDownloadedActivity.class);

                Bundle bundle = new Bundle();
                if(check(operation)){
                    bundle.putSerializable("saved", operation);
                    Toast.makeText(this, "Đã lưu tin", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(this, "Tin đã tồn tại", Toast.LENGTH_SHORT).show();

                setOperationFrag(bundle);
                break;
            }
            case R.id.aDownload:{
                item.setIcon(R.drawable.ic_downloaded);
//                boolean isLike = isLiked(tvLikes);
                tvTopic.setText(getResources().getString(R.string.news_downloaded));
                recyclerView.setVisibility(View.GONE);
                Operation operation = new Operation(news, MyList.today, false, true, false);
//                Intent intent = new Intent(News1Activity.this, NewsDownloadedActivity.class);

                Bundle bundle = new Bundle();
                if(check(operation)){
                    bundle.putSerializable("download", operation);
                    Toast.makeText(this, "Đã tải tin", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(this, "Tin đã tồn tại", Toast.LENGTH_SHORT).show();

                setOperationFrag(bundle);
//                startActivity(intent);
                break;
            }
            case R.id.aFormatSize:{
                changeSize();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean check(Operation operation) {
        for(Operation operation1: MyList.listOperation){
            if(operation.getNews() == operation1.getNews())
                return false;
        }
        return true;
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
        updateText(seekBar.getProgress(), font);
    }

    private void updateText(int size, int id){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("size_level", size);
        editor.putInt("font", id);
        editor.apply();
        getSupportFragmentManager().popBackStack();
        addFragment(new NewsFragment(), true);
    }

    private void setListRelevance(News news) {
        LinearLayoutManager manager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        ArrayList<News> list = new ArrayList<>();
        for(int i=0; i<MyList.listsText.size(); i++){
            for(int j = 0; j< MyList.listsText.get(i).size(); j++){
                News n = MyList.listsText.get(i).get(j);
                if(news.getProvince() != null && n.getProvince() != null)
                    if(news.getTopic().equals(n.getTopic())
                            || news.getProvince().equals(n.getProvince()))
                        list.add(n);
            }

        }

        adapter.setData(list);
        adapter.setClickNewsListener(this::onItemClick);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        news = adapter.getItem(position);
        getSupportFragmentManager().popBackStack();
        addFragment(new NewsFragment(), true);
        tvTopic.setText(news.getTopic());
    }
}