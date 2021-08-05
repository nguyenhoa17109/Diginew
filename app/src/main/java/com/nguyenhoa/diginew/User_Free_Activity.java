package com.nguyenhoa.diginew;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class User_Free_Activity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivBack, ivSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_free);
        init();
        setOnClick();
    }

    public void setOnClick() {
        ivBack.setOnClickListener(this);
        ivSetting.setOnClickListener(this);
    }

    public void init() {
        getSupportActionBar().hide();
        ivBack = findViewById(R.id.ivBack);
        ivSetting = findViewById(R.id.ivSetting);
    }

    public void settingOnClick() {
        Intent in = new Intent(this, UserSettingActivity.class);
        startActivity(in);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == ivBack.getId()) {
            finish();
        } else if (v.getId() == ivSetting.getId()) {
            settingOnClick();
        }
    }
}