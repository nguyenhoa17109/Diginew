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
        list.add(new Topic("Giáo dục", R.drawable.img_clip));
        list.add(new Topic("Xã hội", R.drawable.img_clip));
        list.add(new Topic("Sức khỏe", R.drawable.img_clip));
        list.add(new Topic("Gỉải trí", R.drawable.img_clip));
        list.add(new Topic("Thể thao", R.drawable.img_clip));
        list.add(new Topic("Công nghệ", R.drawable.img_clip));
        list.add(new Topic("Kinh tế", R.drawable.img_clip));

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
