package com.nguyenhoa.diginew.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.nguyenhoa.diginew.R;
import com.smarteist.autoimageslider.SliderView;

public class Splash1 extends AppCompatActivity {
    SharedPreferences preferences;
    boolean isFirst;
    public static final String PREFER_NAME = "Splash1";
    private Button btStart;
    SliderView sliderView;
    int[] images = {R.drawable.img_start, R.drawable.sj_all, R.drawable.sj_technology};
    String[] names = {"DigiNews là ứng dụng đọc báo chính thức của " +
            "\nVNPT - trang tổng hợp tin tức tự động hàng đầu " +
            "\nViệt Nam. " +
            "\nSử dụng công nghệ máy tính thông minh độc quyền " +
            "\ncủa DigiNews, ứng dụng cho phép bạn đọc nhanh " +
            "\ncác tin tức nóng và mới nhất trong ngày được chọn " +
            "\nlọc từ hơn 150 báo điện tử tại Việt Nam."
            , "slide show 2"
            , "slide show 3"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        preferences = getSharedPreferences(PREFER_NAME, MODE_PRIVATE);
        isFirst = preferences.getBoolean(PREFER_NAME, false);

        if(isFirst){
            startActivity(new Intent(Splash1.this, Splash2.class));
            finish();
        }
        else{
            setContentView(R.layout.activity_splash1);

            btStart = findViewById(R.id.btStart);
            btStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Splash1.this, Splash2.class));
                }
            });

            sliderView = findViewById(R.id.slideSplash1);
            SlideSplashAdapter slideSplashAdapter = new SlideSplashAdapter(images, names);
            sliderView.setSliderAdapter(slideSplashAdapter);
            sliderView.startAutoCycle();

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(PREFER_NAME, true);
            editor.commit();
        }


    }

}