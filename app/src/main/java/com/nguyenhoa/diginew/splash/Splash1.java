package com.nguyenhoa.diginew.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.nguyenhoa.diginew.R;
import com.smarteist.autoimageslider.SliderView;

public class Splash1 extends AppCompatActivity {
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
        setContentView(R.layout.activity_splash1);

        btStart = findViewById(R.id.btStart);
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Splash1.this, Splash2.class);
                startActivity(intent);
            }
        });

        sliderView = findViewById(R.id.slideSplash1);
        SlideSplashAdapter slideSplashAdapter = new SlideSplashAdapter(images, names);
        sliderView.setSliderAdapter(slideSplashAdapter);
        sliderView.startAutoCycle();

//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//        boolean isFirst = prefs.getBoolean("splash1", true);
//        if(isFirst){
//
//        }

//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putBoolean("splash1", Boolean.FALSE);
//        editor.commit();

    }

}