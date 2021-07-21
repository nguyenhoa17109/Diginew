package com.nguyenhoa.diginew.splash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.nguyenhoa.diginew.MainActivity;
import com.nguyenhoa.diginew.R;

public class Splash2 extends AppCompatActivity {
    TextView tvTerms, tvSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        tvTerms = findViewById(R.id.tvTerms);
        tvSkip = findViewById(R.id.tvSkipLogin);

        SpannableString ss = new SpannableString("Bằng việc đăng nhập, bạn đồng ý với các Điều khoản và Chính sách của DigiNews");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                startActivity(new Intent(Splash2.this, Terms.class));
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.WHITE);
                ds.setUnderlineText(true);
            }
        };
        ss.setSpan(clickableSpan, 40,64, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        tvTerms.setText(ss);
        tvTerms.setMovementMethod(LinkMovementMethod.getInstance());
        tvTerms.setHighlightColor(Color.TRANSPARENT);

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Splash2.this, SubjectsFavorite.class));
            }
        });
    }
}