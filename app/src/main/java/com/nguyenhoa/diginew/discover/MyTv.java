package com.nguyenhoa.diginew.discover;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.nguyenhoa.diginew.R;

import java.io.InputStream;

public class MyTv extends AppCompatActivity {
    private ImageView ivBack;
    private Button btOpen;
    private TextView tv;

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tv);

        getSupportActionBar().hide();
        ivBack = findViewById(R.id.ivBack);
        btOpen = findViewById(R.id.btOpen);
        tv = findViewById(R.id.tvDigiMovie);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tv.setMovementMethod(new ScrollingMovementMethod());
        try {
            Resources res = getResources();
            InputStream in_s = res.openRawResource(R.raw.mytv);
            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            tv.setText(new String(b));
        } catch (Exception e) {
            tv.setText("Error: can't show content.");
        }
    }
}