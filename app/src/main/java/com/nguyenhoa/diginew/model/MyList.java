package com.nguyenhoa.diginew.model;

import android.app.Application;

import com.nguyenhoa.diginew.R;

import java.util.ArrayList;

public class MyList extends Application {
    public static ArrayList<Topic> list;
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
    }

}
