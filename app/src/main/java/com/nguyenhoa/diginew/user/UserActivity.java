package com.nguyenhoa.diginew.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.adapter.NewsRCAdapter;
import com.nguyenhoa.diginew.common.CustomItemAnimator;
import com.nguyenhoa.diginew.common.MyClass;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.common.NewsCallBack;
import com.nguyenhoa.diginew.common.SQLiteDigi;
import com.nguyenhoa.diginew.digiPack.DigiAdvance;
import com.nguyenhoa.diginew.digiPack.DigiLife;
import com.nguyenhoa.diginew.model.Account;
import com.nguyenhoa.diginew.model.News;
import com.nguyenhoa.diginew.model.Packet;
import com.nguyenhoa.diginew.model.User;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity implements View.OnClickListener, NewsCallBack {
    private ImageView ivSetting, ivBack,ivUser;
    private TextView tvUser_name, tvSeeDetail, tvSeePack, tvSeeLess, tvData2, tvInNet, tvOutNet, tvSMS, tvMyTV, tvClips, tvHotNews, tvMusic, tvMovie, tvDataRemaining;
    private TableRow tblMyTV, tblClips, tblHotNews, tblMusic, tblMovie, tblDataRemaining, tblSeeLess, tblSeeDetail;
    private LinearLayout layoutAdvancePack;
    private ArrayList<Packet> packet;
    public static SQLiteDigi sqLite;
    private RecyclerView rcRecentNews, rcDowloadedNews, rcLikedNews, rcSavedNews;
    private NewsRCAdapter RecentNews_adapter, DowloadedNews_adapter, LikedNews_adapter, SavedNews_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        init();
        setOnClick();
    }

    public void init() {
        getSupportActionBar().hide();
        ivUser = findViewById(R.id.ivUser);
        ivBack = findViewById(R.id.ivBack);
        ivSetting = findViewById(R.id.ivSetting);
        tvUser_name = findViewById(R.id.tvUser_name);
        tvSeeDetail = findViewById(R.id.tvSeeDetail);
        tvSeePack = findViewById(R.id.tvSeePack);
        tvSeeLess = findViewById(R.id.tvSeeLess);
        tblMyTV = findViewById(R.id.tblMyTV);
        tblClips = findViewById(R.id.tblClips);
        tblHotNews = findViewById(R.id.tblHotNews);
        tblMusic = findViewById(R.id.tblMusic);
        tblMovie = findViewById(R.id.tblMovie);
        tblDataRemaining = findViewById(R.id.tblDataRemaining);
        tblSeeLess = findViewById(R.id.tblSeeLess);
        tblSeeDetail = findViewById(R.id.tblSeeDetail);
        layoutAdvancePack = findViewById(R.id.layoutAdvancePack);

        tvData2 = findViewById(R.id.tvData2);
        tvInNet = findViewById(R.id.tvInNet);
        tvOutNet = findViewById(R.id.tvOutNet);
        tvSMS = findViewById(R.id.tvSMS);
        tvMyTV = findViewById(R.id.tvMyTV);
        tvClips = findViewById(R.id.tvClips);
        tvHotNews = findViewById(R.id.tvHotNews);
        tvMusic = findViewById(R.id.tvMusic);
        tvMovie = findViewById(R.id.tvMovie);
        tvDataRemaining = findViewById(R.id.tvDataRemaining);

        rcRecentNews = findViewById(R.id.rcRecentNews);
        rcDowloadedNews = findViewById(R.id.rcDowloadedNews);
        rcLikedNews = findViewById(R.id.rcLikedNews);
        rcSavedNews = findViewById(R.id.rcSavedNews);
        sqLite = new SQLiteDigi(getApplicationContext());
        packet = new ArrayList<Packet>();
        setUser();
        setPacket();

        setRV();
    }

    public void setOnClick() {
        ivBack.setOnClickListener(this);
        ivSetting.setOnClickListener(this);
        tvSeeDetail.setOnClickListener(this);
        tvSeePack.setOnClickListener(this);
        tvSeeLess.setOnClickListener(this);
        layoutAdvancePack.setOnClickListener(this);

    }

    private void setRV() {
        RecentNews_adapter = new NewsRCAdapter(this);
        DowloadedNews_adapter = new NewsRCAdapter(this);
        LikedNews_adapter = new NewsRCAdapter(this);
        SavedNews_adapter = new NewsRCAdapter(this);

        LinearLayoutManager manager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        LinearLayoutManager manager2 = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        LinearLayoutManager manager3 = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        LinearLayoutManager manager4 = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        rcRecentNews.setLayoutManager(manager);
        rcDowloadedNews.setLayoutManager(manager2);
        rcLikedNews.setLayoutManager(manager3);
        rcSavedNews.setLayoutManager(manager4);

        RecentNews_adapter.setData(MyList.listNews, this::onNewsItemClick);
        DowloadedNews_adapter.setData(MyList.listNews, this::onNewsItemClick);
        LikedNews_adapter.setData(MyList.listNews, this::onNewsItemClick);
        SavedNews_adapter.setData(MyList.listNews, this::onNewsItemClick);
//        adapter.setClickNewsListener(this::onItemClick);
        rcRecentNews.setAdapter(RecentNews_adapter);
        rcRecentNews.setItemAnimator(new CustomItemAnimator());
        rcDowloadedNews.setAdapter(DowloadedNews_adapter);
        rcDowloadedNews.setItemAnimator(new CustomItemAnimator());
        rcLikedNews.setAdapter(LikedNews_adapter);
        rcLikedNews.setItemAnimator(new CustomItemAnimator());
        rcSavedNews.setAdapter(SavedNews_adapter);
        rcSavedNews.setItemAnimator(new CustomItemAnimator());

    }
    public void setUser() {
        Account account = new Account();
        account = sqLite.getAccountByID(1);
        String name = "Xin chào, "+account.getName();
        tvUser_name.setText(name);
        new DownloadUserImg((ImageView) findViewById(R.id.ivUser))
                .execute(account.getImg());


    }

    public void setPacket() {
        packet = sqLite.getAllPacket();
        tvData2.setText(packet.get(1).getData());
        tvInNet.setText(packet.get(1).getInNet());
        tvOutNet.setText(packet.get(1).getOnNet());
        tvSMS.setText(packet.get(1).getSms());
        tvMyTV.setText(packet.get(1).getMyTV());
        tvClips.setText(packet.get(1).getClips());
        tvHotNews.setText(packet.get(1).getHotNews());
        tvMusic.setText(packet.get(1).getMusic());
        tvMovie.setText(packet.get(1).getMovie());
        tvDataRemaining.setText(packet.get(1).getDataRemaining());

    }

    public void settingOnClick() {
        Intent in = new Intent(this, UserSettingActivity.class);
        startActivity(in);
    }

    public void seeDetail() {
        tblSeeDetail.setVisibility(View.GONE);
        tblMyTV.setVisibility(View.VISIBLE);
        tblClips.setVisibility(View.VISIBLE);
        tblHotNews.setVisibility(View.VISIBLE);
        tblMusic.setVisibility(View.VISIBLE);
        tblMovie.setVisibility(View.VISIBLE);
        tblDataRemaining.setVisibility(View.VISIBLE);
        tblSeeLess.setVisibility(View.VISIBLE);
    }

    public void seeLess() {
        tblSeeDetail.setVisibility(View.VISIBLE);
        tblMyTV.setVisibility(View.GONE);
        tblClips.setVisibility(View.GONE);
        tblHotNews.setVisibility(View.GONE);
        tblMusic.setVisibility(View.GONE);
        tblMovie.setVisibility(View.GONE);
        tblDataRemaining.setVisibility(View.GONE);
        tblSeeLess.setVisibility(View.GONE);
    }

    public void seePack() {
        Intent in = new Intent(this, DigiLife.class);
        startActivity(in);
    }

    public void advancePack() {
        Intent in = new Intent(this, DigiAdvance.class);
        startActivity(in);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == ivSetting.getId()) {
            settingOnClick();
        } else if (v.getId() == tvSeeDetail.getId()) {
            seeDetail();
        } else if (v.getId() == tvSeeLess.getId()) {
            seeLess();
        } else if (v.getId() == tvSeePack.getId()) {
            seePack();
        } else if (v.getId() == layoutAdvancePack.getId()) {
            advancePack();
        } else if (v.getId() == ivBack.getId()) {
            finish();
        }
    }

    @Override
    public void onNewsItemClick(int pos, TextView ivTopic, TextView ivSource, TextView tvTime) {
        News news = RecentNews_adapter.getItem(pos);
        News news2 = DowloadedNews_adapter.getItem(pos);
        News news3 = SavedNews_adapter.getItem(pos);
        News news4 = LikedNews_adapter.getItem(pos);
        MyClass.setIntent(news, this, ivTopic, ivSource, tvTime);
        MyClass.setIntent(news2, this, ivTopic, ivSource, tvTime);
        MyClass.setIntent(news3, this, ivTopic, ivSource, tvTime);
        MyClass.setIntent(news4, this, ivTopic, ivSource, tvTime);
    }
}