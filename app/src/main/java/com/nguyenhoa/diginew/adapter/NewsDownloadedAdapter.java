package com.nguyenhoa.diginew.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.common.MyClass;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.News;
import com.nguyenhoa.diginew.model.Operation;

import java.util.ArrayList;

public class NewsDownloadedAdapter extends RecyclerView.Adapter<NewsDownloadedAdapter.NewsDownloadViewHolder> {
    private ArrayList<ArrayList<Operation>> list;
    private ArrayList<News> listNews;
    private Context context;

    public NewsDownloadedAdapter(ArrayList<ArrayList<Operation>> list, Context context) {
        this.list = list;
        this.context = context;
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
        String date = list.get(position).get(0).getDate();

        listNews = new ArrayList<>();
        for(Operation operation: list.get(position)){
            listNews.add(operation.getNews());
        }

        if(listNews != null){
            holder.setRV(listNews);
        }

        String today = MyList.today;
        if(date.equals(today))
            date = context.getResources().getString(R.string.today);
        holder.tvDate.setText(date);

        String finalDate = date;
        holder.tvDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("KKO", "ok");
                listNews = new ArrayList<>();
                for(Operation operation: MyList.listOperation){
                    if(operation.getDate().equals(finalDate)){
                        operation.setDownload(false);
                    }
                }
                list.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NewsDownloadViewHolder extends RecyclerView.ViewHolder implements NewsRCAdapter.ItemNewsRCClickListener{
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
            adapter.setData(list);
            LinearLayoutManager manager = new LinearLayoutManager(context.getApplicationContext(),
                    RecyclerView.VERTICAL, false);
            rvDownloadNews.setLayoutManager(manager);
            adapter.setClickNewsListener(this::onItemClick);
            rvDownloadNews.setAdapter(adapter);
        }

        @Override
        public void onItemClick(View view, int position) {
            MyClass.setIntent(adapter.getItem(getAdapterPosition()), (Activity) itemView.getContext());
        }
    }
}
