package com.nguyenhoa.diginew.discover;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.nguyenhoa.diginew.R;

public class DigiMusic extends AppCompatActivity {
    private ImageView ivBack;
    private Button btOpen;

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digi_music);

        getSupportActionBar().hide();
        ivBack = findViewById(R.id.ivBack);
        btOpen = findViewById(R.id.btOpen);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}