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
        listNews.add(new News("Vietnamnet", 6, "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 200
                , 100, R.drawable.sj_confidence,"abc", "text",  "Thể thao",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
        listNews.add(new News("Vietnamnet", 6, "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 200
                , 100, R.drawable.sj_science,"abc", "text",  "Kinh tế",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
        listNews.add(new News("Vietnamnet", 6, "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 200
                , 100, R.drawable.sj_confidence,"abc", "text",  "Kinh tế",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
        listNews.add(new News("Vietnamnet", 6, "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 200
                , 100, R.drawable.sj_science,"abc", "text",  "Đời sống",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
        listNews.add(new News("Vietnamnet", 6, "Suc khoe cua chung ta", 200
                , 100, R.drawable.sj_confidence,"abc", "text",  "Sức khỏe",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
        listNews.add(new News("Vietnamnet", 6, "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 200
                , 100, R.drawable.sj_science,"abc", "text",  "Công nghệ",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
        listNews.add(new News("Vietnamnet", 6, "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 200
                , 100, R.drawable.sj_confidence,"abc", "text",  "Giải trí",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
        listNews.add(new News("Vietnamnet", 6, "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 200
                , 100, R.drawable.sj_science,"abc", "text",  "Tâm sự",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
    }

}
