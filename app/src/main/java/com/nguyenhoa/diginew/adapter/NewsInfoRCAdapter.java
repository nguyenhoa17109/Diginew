package com.nguyenhoa.diginew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.common.DownloadImageTask;
import com.nguyenhoa.diginew.common.MyClass;
import com.nguyenhoa.diginew.model.News;

import java.util.ArrayList;

public class NewsInfoRCAdapter extends RecyclerView.Adapter<NewsInfoRCAdapter.NewsViewHolder> {
    private Context context;
    private ArrayList<News> list;
    private ItemNewsInfoRCClickListener itemNewsInfoRCClickListener;


    public NewsInfoRCAdapter(Context context) {
        this.context = context;
    }

    public interface ItemNewsInfoRCClickListener{
        void onItemClickInfo(View view, int position);
    }

    public void setClickNewsListener(ItemNewsInfoRCClickListener itemNewsInfoRCClickListener){
        this.itemNewsInfoRCClickListener = itemNewsInfoRCClickListener;
    }

    public void setData(ArrayList<News> list){
        this.list = list;
        notifyDataSetChanged();
    }
    public News getItem(int position){
        return list.get(position);
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_info,
                parent, false);
        return new NewsInfoRCAdapter.NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  NewsInfoRCAdapter.NewsViewHolder holder, int position) {
        News news = list.get(position);
        if(news == null)    return;
        new DownloadImageTask(holder.ivNews).execute(list.get(position).getImgs());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView ivNews;

        public NewsViewHolder(@NonNull View view) {
            super(view);
            ivNews = view.findViewById(R.id.ivNews);

            view.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {
            if(itemNewsInfoRCClickListener != null)
                itemNewsInfoRCClickListener.onItemClickInfo(view, getAdapterPosition());
        }
    }
}
