package com.nguyenhoa.diginew.splash;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.nguyenhoa.diginew.R;

import java.io.InputStream;

public class Terms extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tvTerm;

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvTerm = findViewById(R.id.tvTerms);
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