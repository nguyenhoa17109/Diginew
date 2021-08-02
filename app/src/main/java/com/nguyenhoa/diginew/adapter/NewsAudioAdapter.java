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
import com.nguyenhoa.diginew.model.News;

import java.util.ArrayList;

public class NewsAudioAdapter extends RecyclerView.Adapter<NewsAudioAdapter.MyViewHolder>{
    private Context context;
    private ArrayList<News> list;
    private ItemAudioClickListener itemAudioClickListener;

    public NewsAudioAdapter(Context context, ArrayList<News> list, ItemAudioClickListener itemAudioClickListener) {
        this.context = context;
        this.list = list;
        this.itemAudioClickListener = itemAudioClickListener;
    }

    public interface ItemAudioClickListener{
        void onItemClick(View view, int position);
    }
    public News getItem(int position){
        return list.get(position);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_audio,
                parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        News news = list.get(position);
        if(news == null)    return;
        holder.tvSource.setText(list.get(position).getSource());
        holder.tvCmts.setText(String.valueOf(list.get(position).getCmts()));
        holder.tvLikes.setText(String.valueOf(list.get(position).getLikes()));
//        holder.tvTime.setText(list.get(position).getTimes()+" "+context.getResources().getString(R.string.time));
        holder.tvTime.setText(list.get(position).getTimes());
        holder.ivNews.setImageResource(list.get(position).getImgs());
        holder.tvTitle.setText(list.get(position).getTitle());
        holder.ivHeadset.setImageResource(R.drawable.ic_headset);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvSource, tvTime, tvTitle, tvLikes, tvCmts;
        private ImageView ivNews, ivHeadset;

        public MyViewHolder(@NonNull View view) {
            super(view);
            tvSource = view.findViewById(R.id.tvSource);
            tvTime = view.findViewById(R.id.tvTimes);
            ivNews = view.findViewById(R.id.ivNews);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvLikes = view.findViewById(R.id.tvLikes);
            tvCmts = view.findViewById(R.id.tvCmts);
            ivHeadset = view.findViewById(R.id.ivHeadset);
        }

        @Override
        public void onClick(View v) {
            if(itemAudioClickListener != null){
                itemAudioClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
