package com.nguyenhoa.diginew.common;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.nguyenhoa.diginew.InfoNewsActivity;
import com.nguyenhoa.diginew.NewsActivity;
import com.nguyenhoa.diginew.PlayVideoActivity;
import com.nguyenhoa.diginew.model.News;

import java.util.ArrayList;

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

    public static void setRowClick(TextView tv, ArrayList<TextView> list){
        for(TextView t:list){
            if(t == tv){
                if(tv.getVisibility() == View.VISIBLE)
                    tv.setVisibility(View.GONE);
                else
                    tv.setVisibility(View.VISIBLE);
            }else
                t.setVisibility(View.GONE);
        }
    }
}