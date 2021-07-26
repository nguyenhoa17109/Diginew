package com.nguyenhoa.diginew.model;

import android.app.Application;

import com.nguyenhoa.diginew.R;

import java.util.ArrayList;

public class MyList extends Application {
    public static ArrayList<Topic> list;
    public static ArrayList<News> listNews;
    @Override
    public void onCreate() {
        super.onCreate();

        list = new ArrayList<>();
        list.add(new Topic("Đời sống", R.drawable.sj_life));
        list.add(new Topic("Kinh tế", R.drawable.sj_business));
        list.add(new Topic("Sức khỏe", R.drawable.sj_health));
        list.add(new Topic("Xã hội", R.drawable.sj_all));
        list.add(new Topic("Khoa học", R.drawable.sj_science));
        list.add(new Topic("Giải trí", R.drawable.sj_entertainment));
        list.add(new Topic("Công nghệ", R.drawable.sj_technology));
        list.add(new Topic("Thể thao", R.drawable.sj_sport));
        list.add(new Topic("Tâm sự", R.drawable.sj_confidence));

        listNews = new ArrayList<>();
        listNews.add(new News("Sức khỏe", "text", "Vietnamnet", 6,
                "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 100, 200,
                R.drawable.img_clip, "abc"));
        listNews.add(new News("Sức khỏe","text", "Vietnamnet", 6,
                "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 100, 200,
                R.drawable.img_clip, "abc"));
        listNews.add(new News("Sức khỏe","text", "Vietnamnet", 6,
                "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 100, 200,
                R.drawable.img_clip, "abc"));
        listNews.add(new News("Sức khỏe","text", "Vietnamnet", 6,
                "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 100, 200,
                R.drawable.img_clip, "abc"));
    }

}
