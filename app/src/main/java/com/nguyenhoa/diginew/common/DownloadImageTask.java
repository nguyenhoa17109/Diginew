package com.nguyenhoa.diginew.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.InputStream;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    LinearLayout layout;
    TextView textView;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    public DownloadImageTask(LinearLayout layout) {
        this.layout = layout;
    }

    public DownloadImageTask(TextView textView) {
        this.textView = textView;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        if(bmImage != null)
            bmImage.setImageBitmap(result);
        else if(layout != null)
            layout.setBackground(new BitmapDrawable(result));
        else  if(textView != null)
            textView.setBackground(new BitmapDrawable(result));
    }
}