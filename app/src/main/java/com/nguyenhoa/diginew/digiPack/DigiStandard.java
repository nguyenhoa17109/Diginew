package com.nguyenhoa.diginew.digiPack;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


import com.nguyenhoa.diginew.R;

public class DigiStandard extends AppCompatActivity {
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_packet_detail1);

        ivBack = findViewById(R.id.ivBack);
        getSupportActionBar().hide();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}