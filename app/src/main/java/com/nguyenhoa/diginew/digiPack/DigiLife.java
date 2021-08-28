package com.nguyenhoa.diginew.digiPack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.digiPack.DigiAdvance;

public class DigiLife extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivBack;
    private Button btAdvance, btVip, bt2k, bt55k, bt549k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_packet_digilife);
        getSupportActionBar().hide();
        init();
        setOnClick();

    }

    public void init() {
        ivBack = findViewById(R.id.ivBack);
        btAdvance = findViewById(R.id.btAdvance);
        btVip = findViewById(R.id.btVip);
        bt2k = findViewById(R.id.bt2k);
        bt55k = findViewById(R.id.bt55k);
        bt549k = findViewById(R.id.bt549k);
    }

    public void setOnClick() {
        ivBack.setOnClickListener(this);
        btAdvance.setOnClickListener(this);
        btVip.setOnClickListener(this);
        bt2k.setOnClickListener(this);
        bt55k.setOnClickListener(this);
        bt549k.setOnClickListener(this);
    }

    public void advancePack() {
        Intent in = new Intent(this, DigiAdvance.class);
        startActivity(in);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == ivBack.getId()) {
            finish();
        } else if (v.getId() == btAdvance.getId()) {
            advancePack();
        } else if (v.getId() == btVip.getId()) {
            advancePack();
        } else if (v.getId() == bt2k.getId()) {
            advancePack();
        } else if (v.getId() == bt55k.getId()) {
            advancePack();
        } else if (v.getId() == bt549k.getId()) {
            advancePack();
        }
    }
}