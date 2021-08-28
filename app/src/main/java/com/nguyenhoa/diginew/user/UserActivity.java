package com.nguyenhoa.diginew.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.digiPack.DigiAdvance;
import com.nguyenhoa.diginew.digiPack.DigiLife;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivSetting, ivBack;
    private TextView tvSeeDetail, tvSeePack, tvSeeLess;
    private TableRow tblMyTV, tblClips, tblHotNews, tblMusic, tblMovie, tblDataRemaining, tblSeeLess, tblSeeDetail;
    private LinearLayout layoutAdvancePack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        init();
        setOnClick();

    }

    public void init() {
        getSupportActionBar().hide();
        ivBack = findViewById(R.id.ivBack);
        ivSetting = findViewById(R.id.ivSetting);
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
    }

    public void setOnClick() {
        ivBack.setOnClickListener(this);
        ivSetting.setOnClickListener(this);
        tvSeeDetail.setOnClickListener(this);
        tvSeePack.setOnClickListener(this);
        tvSeeLess.setOnClickListener(this);
        layoutAdvancePack.setOnClickListener(this);

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
}