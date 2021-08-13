package com.nguyenhoa.diginew.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.adapter.NewsInfoRCAdapter;
import com.nguyenhoa.diginew.adapter.NewsRCAdapter;
import com.nguyenhoa.diginew.common.MyClass;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.News;

import java.util.ArrayList;

public class InforNewsActivity extends AppCompatActivity
        implements NewsInfoRCAdapter.ItemNewsInfoRCClickListener {
    private News news;
    public TextView tvTopic;
    public ImageView ivBack;
    private RecyclerView rv;
    private NewsInfoRCAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_news);

        getSupportActionBar().hide();
        init();

        Intent intent = getIntent();
        news = (News) intent.getSerializableExtra("info");
        tvTopic.setText(news.getTopic());
        setClick();
        addFragment(new InfoNewsFragment());
        setListRelevance(news);
    }

    public void addFragment(Fragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("info", news);
        fragment.setArguments(bundle);
        FragmentManager fmgr = getSupportFragmentManager();
        FragmentTransaction ft = fmgr.beginTransaction();
        ft.add(R.id.fragment, fragment);
        ft.addToBackStack(fragment.getClass().getSimpleName());
        ft.commit();
    }

    private void setClick() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void init(){
        ivBack = findViewById(R.id.ivBack);
        tvTopic = findViewById(R.id.tvTopic);
        rv = findViewById(R.id.rcNews);

        adapter = new NewsInfoRCAdapter(this);

    }

    private void setListRelevance(News news) {
        LinearLayoutManager manager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        rv.setLayoutManager(manager);

        ArrayList<News> list = new ArrayList<>();
        for(int i=0; i<MyList.lists_info.size(); i++){
            for(int j = 0; j< MyList.lists_info.get(i).size(); j++){
                News n = MyList.lists_info.get(i).get(j);
                if(news.getProvince() != null && n.getProvince() != null)
                    if(news.getTopic().equals(n.getTopic())
                            || news.getProvince().equals(n.getProvince()))
                        list.add(n);
            }

        }

        adapter.setData(list);
        adapter.setClickNewsListener(this::onItemClickInfo);
        rv.setAdapter(adapter);
    }

    @Override
    public void onItemClickInfo(View view, int position) {
        news = adapter.getItem(position);
        getSupportFragmentManager().popBackStack();
        addFragment(new InfoNewsFragment());
        tvTopic.setText(news.getTopic());
        setListRelevance(news);
    }
}