package com.nguyenhoa.diginew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.common.DownloadImageTask;
import com.nguyenhoa.diginew.common.MyClass;
import com.nguyenhoa.diginew.model.News;

import java.util.ArrayList;

public class NewsRCAdapter extends RecyclerView.Adapter<NewsRCAdapter.NewsViewHolder> {
    private Context context;
    private ArrayList<News> list;
    private ItemNewsRCClickListener itemNewsRCClickListener;


    public NewsRCAdapter(Context context) {
        this.context = context;
    }

    public interface ItemNewsRCClickListener{
        void onItemClick(View view, int position);
    }

    public void setClickNewsListener(ItemNewsRCClickListener itemNewsRCClickListener){
        this.itemNewsRCClickListener = itemNewsRCClickListener;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,
                parent, false);
        return new NewsRCAdapter.NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  NewsRCAdapter.NewsViewHolder holder, int position) {
        News news = list.get(position);
        if(news == null)    return;
        holder.tvSource.setText(list.get(position).getSource());
        holder.tvCmts.setText(String.valueOf(list.get(position).getCmts()));
        holder.tvLikes.setText(String.valueOf(list.get(position).getLikes()));
//        holder.tvTime.setText(list.get(position).getTimes()+" "+context.getResources().getString(R.string.time));
        holder.tvTime.setText(list.get(position).getTimes());
        new DownloadImageTask(holder.ivNews).execute(list.get(position).getImgs());
        holder.tvTitle.setText(list.get(position).getTitle());
        holder.ivDelete.setVisibility(View.GONE);
        if(list.get(position).getType().equals("audio")){
            holder.ivHeadset.setVisibility(View.VISIBLE);
        }
        else{
            holder.ivHeadset.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tvSource, tvTime, tvTitle, tvLikes, tvCmts;
        public ImageView ivNews, ivDelete, ivHeadset;

        public NewsViewHolder(@NonNull View view) {
            super(view);
            tvSource = view.findViewById(R.id.tvSource);
            tvTime = view.findViewById(R.id.tvTimes);
            ivNews = view.findViewById(R.id.ivNews);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvLikes = view.findViewById(R.id.tvLikes);
            tvCmts = view.findViewById(R.id.tvCmts);
            ivDelete = view.findViewById(R.id.ivDelete);
            ivHeadset = view.findViewById(R.id.ivHeadset);

            view.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {
            if(itemNewsRCClickListener != null)
                itemNewsRCClickListener.onItemClick(view, getAdapterPosition());
        }
    }
    public void removeItem(int position){
        list.remove(position);
        notifyDataSetChanged();
    }
}
