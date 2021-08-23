package com.nguyenhoa.diginew.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.common.DownloadImageTask;
import com.nguyenhoa.diginew.common.MyClass;
import com.nguyenhoa.diginew.common.NewsCallBack;
import com.nguyenhoa.diginew.model.News;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class NewsSlideAdapter extends SliderViewAdapter<NewsSlideAdapter.SlideHolder> {
    private ArrayList<News> list;
    private ItemSlideNewsClick itemSlideNewsClick;

    public NewsSlideAdapter(ArrayList<News> list) {
        this.list = list;
    }

    public News getItem(int position){
        return list.get(position);
    }

    public interface ItemSlideNewsClick{
        void OnItemClick(View view, int position, TextView tv);
    }

    public void setItemSlideNewsClick(ItemSlideNewsClick itemSlideNewsClick) {
        this.itemSlideNewsClick = itemSlideNewsClick;
    }

    @Override
    public SlideHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_slide
                , parent, false);
        return new NewsSlideAdapter.SlideHolder(view);
    }

    @Override
    public void onBindViewHolder(SlideHolder viewHolder, int position) {
//        new DownloadImageTask(viewHolder.imageView).execute(list.get(position).getImgs());
        viewHolder.tv.setText(list.get(position).getTitle());
        Glide.with(viewHolder.itemView.getContext())
                .load(list.get(position).getImgs())
                .transform(new CenterCrop())
                .into(viewHolder.imageView);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                callBack.onNewsItemClick(position, viewHolder.tv, new TextView(null), new TextView(null));
                itemSlideNewsClick.OnItemClick(view, position, viewHolder.tv);
            }
        });
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public class SlideHolder extends SliderViewAdapter.ViewHolder {
        ImageView imageView;
        TextView tv;

        public SlideHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ivNews);
            tv = itemView.findViewById(R.id.tv);
//            itemView.setOnClickListener(this::onClick);
        }

//        private void onClick(View view) {
//            callBack.onNewsItemClick(ge);
//        }

    }
}
