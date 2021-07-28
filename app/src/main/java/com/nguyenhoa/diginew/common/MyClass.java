package com.nguyenhoa.diginew.common;

import android.app.Activity;
import android.content.Intent;

import com.nguyenhoa.diginew.InfoNewsActivity;
import com.nguyenhoa.diginew.NewsActivity;
import com.nguyenhoa.diginew.PlayVideoActivity;
import com.nguyenhoa.diginew.model.News;

public class MyClass {
    public static void setIntent(News news, Activity activity){
        String type = news.getType();
        Intent intent = null;

        switch (type){
            case "text":
                intent = new Intent(activity, NewsActivity.class);
                break;
            case "audio":
                intent = new Intent(activity, NewsActivity.class);
                break;
            case "info":
                intent = new Intent(activity, InfoNewsActivity.class);
                break;
            case "video":
                intent = new Intent(activity, PlayVideoActivity.class);
                break;
        }

        intent.putExtra(type, news);
        activity.startActivity(intent);
    }
}
