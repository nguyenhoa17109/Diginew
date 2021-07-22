package com.nguyenhoa.diginew.splash;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.splash.SlideSplashAdapter.Holder;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SlideSplashAdapter extends SliderViewAdapter<Holder> {
    int[] image;
    String[] names;


    public SlideSplashAdapter(int[] image, String[] names) {
        this.image = image;
        this.names = names;
    }

    @Override
    public SlideSplashAdapter.Holder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slideshow_splash1, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(SlideSplashAdapter.Holder viewHolder, int position) {
        viewHolder.imageView.setImageResource(image[position]);
        viewHolder.textView.setText(names[position]);
    }

    @Override
    public int getCount() {
        return names.length;
    }

    public class Holder extends SliderViewAdapter.ViewHolder {
        ImageView imageView;
        TextView textView;

        public Holder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ivItemSplash1);
            textView = itemView.findViewById(R.id.tvItemSplash1);
        }
    }
}
