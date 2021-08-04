package com.nguyenhoa.diginew.splash;

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

public class Terms extends AppCompatActivity {
    private ImageView ivBack;
    private TextView tvTerm, tvTopic;

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        getSupportActionBar().hide();

        ivBack = findViewById(R.id.ivBack);
        tvTerm = findViewById(R.id.tvTerms);
        tvTopic = findViewById(R.id.tvTopic);

        tvTopic.setText("Điều khoản");
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvTerm.setMovementMethod(new ScrollingMovementMethod());
        try {
            Resources res = getResources();
            InputStream in_s = res.openRawResource(R.raw.term);
            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            tvTerm.setText(new String(b));
        } catch (Exception e) {
            tvTerm.setText("Error: can't show terms.");
        }
    }
}