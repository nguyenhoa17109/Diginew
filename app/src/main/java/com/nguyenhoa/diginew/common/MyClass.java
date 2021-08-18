package com.nguyenhoa.diginew.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

//import com.nguyenhoa.diginew.old.InfoNewsActivity;
import com.nguyenhoa.diginew.model.Comment;
import com.nguyenhoa.diginew.news.AudioNewsActivity;
import com.nguyenhoa.diginew.news.InforNewsActivity;
import com.nguyenhoa.diginew.news.News1Activity;
import com.nguyenhoa.diginew.video.PlayVideoActivity;
import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.model.News;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class MyClass {
    public static void setIntent(News news, Activity activity){
        String type = news.getType();
        Intent intent = null;

        switch (type){
            case "textnews":
                intent = new Intent(activity, News1Activity.class);
                break;
            case "audio":
                intent = new Intent(activity, AudioNewsActivity.class);
                break;
            case "info":
                intent = new Intent(activity, InforNewsActivity.class);
                break;
            case "video":
                intent = new Intent(activity, PlayVideoActivity.class);
                break;
        }

        assert intent != null;
        intent.putExtra(type,news);
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

    public static void setTVLike(TextView tvLikes, Context activity){
        int i = Integer.parseInt(tvLikes.getText().toString());
        if(tvLikes.getCurrentTextColor() == Color.parseColor("#0C8DFD") && i != 0){
            tvLikes.setTextColor(Color.parseColor("#A2A6BB"));
            tvLikes.setCompoundDrawablesWithIntrinsicBounds(null, null,
                    activity.getResources().getDrawable(R.drawable.ic_like), null);

            tvLikes.setText(String.valueOf(i-1));
        }else {
            tvLikes.setTextColor(Color.parseColor("#0C8DFD"));
            tvLikes.setCompoundDrawablesWithIntrinsicBounds(null, null,
                    activity.getResources().getDrawable(R.drawable.ic_liked), null);

            tvLikes.setText(String.valueOf(i+1));
        }
    }


    public static boolean isLiked(TextView tvLikes, Context context) {
        Drawable[] lst = tvLikes.getCompoundDrawables();
        for(int i=0; i<lst.length; i++){
            if(lst[i] == context.getDrawable(R.drawable.ic_liked))
                return true;
        }
        return false;
    }

    public static ArrayList<Comment> updateListCmt(ArrayList<Comment> list){
        for(int i=0; i<list.size(); i++){
            list.get(i).setPosition(i);
        }
        return list;
    }

    public void addFragment(Fragment fragment, FragmentManager manager, int layout) {
        FragmentManager fmgr = manager;
        FragmentTransaction ft = fmgr.beginTransaction();
        ft.add(layout, fragment);
        ft.addToBackStack(fragment.getClass().getSimpleName());
        ft.commit();
    }

    public static String getNow(){
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(new Date());
    }

    // convert from bitmap to byte array
//    public static byte[] getBytes(Bitmap bitmap) {
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//
//        return stream.toByteArray();
//    }
//
//    // convert from byte array to bitmap
//    public static Bitmap getImage(byte[] image) {
//        return BitmapFactory.decodeByteArray(image, 0, image.length);
//    }
}
