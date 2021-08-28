package com.nguyenhoa.diginew.digiPack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


import com.nguyenhoa.diginew.R;

public class DigiAdvance extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivBack;
    private Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_packet_advance);
        init();
        setOnClick();
        getSupportActionBar().hide();
    }

    public void init() {
        ivBack = findViewById(R.id.ivBack);
        btRegister = findViewById(R.id.btRegister);
    }

    public void setOnClick() {
        ivBack.setOnClickListener(this);
        btRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == ivBack.getId()) {
            finish();
        } else if (v.getId() == btRegister.getId()) {
            Intent in = new Intent(this, DigiRegister.class);
            startActivity(in);
        }
    }
}