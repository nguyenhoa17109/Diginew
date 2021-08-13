package com.nguyenhoa.diginew.old;

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
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.adapter.CmtAdapter;
import com.nguyenhoa.diginew.adapter.NewsRCAdapter;
import com.nguyenhoa.diginew.common.MyClass;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.Comment;
import com.nguyenhoa.diginew.model.News;
import com.nguyenhoa.diginew.model.Operation;

import java.util.ArrayList;

import static com.nguyenhoa.diginew.R.drawable.background_button;
import static com.nguyenhoa.diginew.R.drawable.background_disable_button;

public class NewsActivity extends AppCompatActivity implements NewsRCAdapter.ItemNewsRCClickListener{
    private TextView tvSearch1, tvSearch2, tvSearch3, tvSearch21, tvSearch22, tvSearch23,
            tvSearch31, tvSearch32, tvSearch33, tvSearch41, tvSearch42, tvSearch43;
    private TextView tvTitleNews, tvSource, tvTime, tvTopic, tvLikes, tvCmts, tvContent;
    private ImageView ivAccount, ivShare, ivBack;
    private EditText etCmt;
    private RecyclerView recyclerView, rvTag;
    private NewsRCAdapter adapter;
    private News news;
    private SharedPreferences preferences;
    public static final String MyPREFERENCES = "CONTENT";
    private RelativeLayout layout;
    private LinearLayout layoutTag;
    private boolean isNews;

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
        init();

        if(news != null){
            setNews(news);
        }else{
            Toast.makeText(this, "News is empty!!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setNews(News news) {
        isNews = true;
        invalidateOptionsMenu();
        layoutTag.setVisibility(View.GONE);
        layout.setVisibility(View.VISIBLE);
//        View view = getSupportActionBar().getCustomView();

        tvTopic = getSupportActionBar().getCustomView().findViewById(R.id.tvNewsTopic);
        ivBack = getSupportActionBar().getCustomView().findViewById(R.id.ivBack);

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
        setClick();
        setTag(news);
        setListRelevance(news);
    }

    private void setTag(News news) {
        ArrayList<String> list = news.getListTag();
        int k = list.size();
        for(int i=0; i<12; i++){
            switch (i){
                case 0:
                    if(i < k){
                        tvSearch1.setVisibility(View.VISIBLE);
                        tvSearch1.setText(list.get(i));
                    }else
                        tvSearch1.setVisibility(View.GONE);
                    break;
                case 1:
                    if(i < k){
                        tvSearch2.setVisibility(View.VISIBLE);
                        tvSearch2.setText(list.get(i));
                    }else
                        tvSearch2.setVisibility(View.GONE);
                    break;
                case 2:
                    if(i < k){
                        tvSearch3.setVisibility(View.VISIBLE);
                        tvSearch3.setText(list.get(i));
                    }else
                        tvSearch3.setVisibility(View.GONE);
                    break;
                case 3:
                    if(i < k){
                        tvSearch21.setVisibility(View.VISIBLE);
                        tvSearch21.setText(list.get(i));
                    }else
                        tvSearch21.setVisibility(View.GONE);
                    break;
                case 4:
                    if(i < k){
                        tvSearch22.setVisibility(View.VISIBLE);
                        tvSearch22.setText(list.get(i));
                    }else
                        tvSearch22.setVisibility(View.GONE);
                    break;
                case 5:
                    if(i < k){
                        tvSearch23.setVisibility(View.VISIBLE);
                        tvSearch23.setText(list.get(i));
                    }else
                        tvSearch23.setVisibility(View.GONE);
                    break;
                case 6:
                    if(i < k){
                        tvSearch31.setVisibility(View.VISIBLE);
                        tvSearch31.setText(list.get(i));
                    }else
                        tvSearch31.setVisibility(View.GONE);
                    break;
                case 7:
                    if(i < k){
                        tvSearch32.setVisibility(View.VISIBLE);
                        tvSearch32.setText(list.get(i));
                    }else
                        tvSearch32.setVisibility(View.GONE);
                    break;
                case 8:
                    if(i < k){
                        tvSearch33.setVisibility(View.VISIBLE);
                        tvSearch33.setText(list.get(i));
                    }else
                        tvSearch33.setVisibility(View.GONE);
                    break;
                case 9:
                    if(i < k){
                        tvSearch41.setVisibility(View.VISIBLE);
                        tvSearch41.setText(list.get(i));
                    }else
                        tvSearch41.setVisibility(View.GONE);
                    break;
                case 10:
                    if(i < k){
                        tvSearch42.setVisibility(View.VISIBLE);
                        tvSearch42.setText(list.get(i));
                    }else
                        tvSearch42.setVisibility(View.GONE);
                    break;
                case 11:
                    if(i < k){
                        tvSearch43.setVisibility(View.VISIBLE);
                        tvSearch43.setText(list.get(i));
                    }else
                        tvSearch43.setVisibility(View.GONE);
                    break;
            }
        }

    }

    private void setEach(String s, TextView tvSearch1) {
        if(s != null){
            tvSearch1.setVisibility(View.VISIBLE);
            tvSearch1.setText(s);
        }else
            tvSearch1.setVisibility(View.GONE);
    }

    private void setClick() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check like and restore to db
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

        setClickTV(tvSearch1);
        setClickTV(tvSearch2);
        setClickTV(tvSearch3);
        setClickTV(tvSearch21);
        setClickTV(tvSearch22);
        setClickTV(tvSearch23);
        setClickTV(tvSearch31);
        setClickTV(tvSearch32);
        setClickTV(tvSearch33);
        setClickTV(tvSearch41);
        setClickTV(tvSearch42);
        setClickTV(tvSearch43);

    }

    private void setClickTV(TextView tvSearch1) {
        tvSearch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = tvSearch1.getText().toString();
                tvTopic.setText("#"+s);
                layout.setVisibility(View.GONE);
                layoutTag.setVisibility(View.VISIBLE);
                setListTag(s);
                isNews = false;
                invalidateOptionsMenu();
//                setSearch(s);
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

        if(!isNews){
            menu.findItem(R.id.aDownload).setVisible(false);
            menu.findItem(R.id.aFormatSize).setVisible(false);
            menu.findItem(R.id.aRestore).setVisible(false);
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
                item.setIcon(R.drawable.ic_downloaded);
                boolean isLike = isLiked(tvLikes);

                Operation operation = new Operation(news, MyList.today, false, true, isLike);
                Intent intent = new Intent(NewsActivity.this, NewsDownloadedActivity.class);
                if(check(operation)){
                    intent.putExtra("download", operation);
                    Toast.makeText(this, "Đã tải tin", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(this, "Tin đã tồn tại", Toast.LENGTH_SHORT).show();

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

    private boolean isLiked(TextView tvLikes) {
        Drawable[] lst = tvLikes.getCompoundDrawables();
        for(int i=0; i<lst.length; i++){
            if(lst[i] == getDrawable(R.drawable.ic_liked))
                return true;
        }
        return false;
    }

//    public void addFragment(Fragment fragment) {
//        int size = 30;
//        String s = "ABC";
//        Bundle bundle = new Bundle();
//        bundle.putInt("size", size);
//        bundle.putString("text", s);
//        fragment.setArguments(bundle);
//        FragmentManager fmgr = getSupportFragmentManager();
//        FragmentTransaction ft = fmgr.beginTransaction();
//        ft.add(R.id.fragment, fragment);
//        ft.addToBackStack(fragment.getClass().getSimpleName());
//        ft.commit();
//    }

    private boolean check(Operation operation) {
        for(Operation operation1:MyList.listOperation){
            if(operation.getNews() == operation1.getNews())
                return false;
        }
        return true;
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

        recyclerView = findViewById(R.id.rcNews);
        adapter = new NewsRCAdapter(this);
        rvTag = findViewById(R.id.rvTag);
        layout = findViewById(R.id.layout);
        layoutTag = findViewById(R.id.layoutTag);

        tvSearch1 = findViewById(R.id.tvSearch1);
        tvSearch2 = findViewById(R.id.tvSearch2);
        tvSearch3 = findViewById(R.id.tvSearch3);
        tvSearch21 = findViewById(R.id.tvSearch21);
        tvSearch22 = findViewById(R.id.tvSearch22);
        tvSearch23 = findViewById(R.id.tvSearch23);
        tvSearch31 = findViewById(R.id.tvSearch31);
        tvSearch32 = findViewById(R.id.tvSearch32);
        tvSearch33 = findViewById(R.id.tvSearch33);
        tvSearch41 = findViewById(R.id.tvSearch41);
        tvSearch42 = findViewById(R.id.tvSearch42);
        tvSearch43 = findViewById(R.id.tvSearch43);

    }

    private void setListTag(String tag) {
        LinearLayoutManager manager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        rvTag.setLayoutManager(manager);

        ArrayList<News> list = new ArrayList<>();
        list.add(MyList.listsText.get(0).get(0));
        for(int i=0; i<MyList.listsText.size(); i++){
            for(int j = 0; j< MyList.listsText.get(i).size(); j++){
                News news = MyList.listsText.get(i).get(j);
                if(checkTag(news, tag) && news != null){
                    list.add(news);
                }
            }
        }

        adapter.setData(list);
        adapter.setClickNewsListener(this::onItemClick);
        rvTag.setAdapter(adapter);
    }

    private boolean checkTag(News news, String tag) {
        int k = 0;
        if(news.getListTag() != null) k = news.getListTag().size();
        for(int i=0; i<k; i++){
            String s = news.getListTag().get(i);
            if(s.equals(tag))
                return true;
        }
        return false;
    }

    private void setListRelevance(News news) {
        LinearLayoutManager manager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        ArrayList<News> list = new ArrayList<>();
        for(int i=0; i<MyList.listsText.size(); i++){
            for(int j = 0; j< MyList.listsText.get(i).size(); j++)
                list.add(MyList.listsText.get(i).get(j));
        }

        adapter.setData(list);
        adapter.setClickNewsListener(this::onItemClick);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        news = adapter.getItem(position);
        setNews(news);
//        MyClass.setIntent(adapter.getItem(position), (Activity) view.getContext());

    }

    private void updateText(int size, int id){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("size_level", size);
        editor.putInt("font", id);
        editor.apply();
    }
}