package com.nguyenhoa.diginew.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.common.CustomItemAnimator;
import com.nguyenhoa.diginew.common.MyClass;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.common.NewsCallBack;
import com.nguyenhoa.diginew.model.News;

import java.util.ArrayList;
import java.util.Arrays;

public class NewsDownloadedAdapter extends RecyclerView.Adapter<NewsDownloadedAdapter.NewsDownloadViewHolder> {
    private ArrayList<ArrayList<News>> list;
    private ArrayList<News> listNews;
    private Context context;
    private int x;

    public NewsDownloadedAdapter(ArrayList<ArrayList<News>> list, Context context, int x) {
        this.list = list;
        this.context = context;
        this.x = x;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsDownloadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_download_child,
                parent, false);
        return new NewsDownloadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsDownloadedAdapter.NewsDownloadViewHolder holder, int position) {
        String date = "";
        if(x == 0){
            date = list.get(position).get(0).getDateLike();
        }else if(x == 1)
            date = list.get(position).get(0).getDateSave();
        else
            date = list.get(position).get(0).getDateDown();

        listNews = list.get(position);

        if(listNews != null){
            holder.setRV(listNews);
        }

        String today = MyList.today;
        if(date.equals(today))
            date = context.getResources().getString(R.string.today);
        holder.tvDate.setText(date);

        holder.tvDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("KKO", "ok");
//                listNews = new ArrayList<>();
                //update DB
                for(News news:list.get(position)) {
                    if (x == 0)
                        news.setDateDown("");
                    else if (x == 1)
                        news.setDateSave("");
                    else
                        news.setDateLike("");
                    MyList.sqLite.updateNews(news);
                }
                list.remove(position);
                if(list.isEmpty()){
                    holder.tvDate.setVisibility(View.GONE);
                    holder.tvDeleteAll.setVisibility(View.GONE);
                }

                notifyDataSetChanged();
            }
        });

        holder.tvDate.setAnimation(AnimationUtils.loadAnimation(context
                , R.anim.fade_transition_animation));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NewsDownloadViewHolder extends RecyclerView.ViewHolder implements NewsCallBack {
        private TextView tvDate, tvDeleteAll;
        private RecyclerView rvDownloadNews;
        private DownloadChildAdapter adapter;

        public NewsDownloadViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvDeleteAll = itemView.findViewById(R.id.tvDelete);
            rvDownloadNews = itemView.findViewById(R.id.rvNewsDownloadChild);
        }

        public void setRV(ArrayList<News> list){
            adapter = new DownloadChildAdapter(context.getApplicationContext());
            adapter.setData(list, this::onNewsItemClick);
            LinearLayoutManager manager = new LinearLayoutManager(context.getApplicationContext(),
                    RecyclerView.VERTICAL, false);
            rvDownloadNews.setLayoutManager(manager);
//            adapter.setClickNewsListener(this::onItemClick);
            rvDownloadNews.setAdapter(adapter);
            rvDownloadNews.setItemAnimator(new CustomItemAnimator());
        }

//        @Override
//        public void onItemClick(View view, int position, TextView tv) {
//            News news = adapter.getItem(getAdapterPosition());
//            MyClass.setIntent(news, (Activity) itemView.getContext(), tv);
//        }

        @Override
        public void onNewsItemClick(int pos, TextView ivTopic, TextView ivSource, TextView tvTime) {
            News news = adapter.getItem(getAdapterPosition());
            MyClass.setIntent(news, (Activity) itemView.getContext(), ivTopic, ivSource, tvTime);
        }
    }
}
