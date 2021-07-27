package com.nguyenhoa.diginew.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.discover.DigiClip;
import com.nguyenhoa.diginew.discover.DigiHealth;
import com.nguyenhoa.diginew.discover.DigiMovie;
import com.nguyenhoa.diginew.discover.DigiMusic;
import com.nguyenhoa.diginew.discover.MyTv;
import com.nguyenhoa.diginew.model.News;
import com.nguyenhoa.diginew.model.Topic;

import java.util.ArrayList;


public class TopicVideoAdapter extends RecyclerView.Adapter<TopicVideoAdapter.MyViewHolder> {
    Context context;
    private ArrayList<Topic> list;
    private ItemTopicVideoRCClickListener itemTopicVideoRCClickListener;
    int index = 0;

    public TopicVideoAdapter(Context context, ArrayList<Topic> list) {
        this.context = context;
        this.list = list;
    }

    public interface ItemTopicVideoRCClickListener{
        void onItemClick(View view, int position);
    }

    public void setItemTopicVideoRCClickListener(ItemTopicVideoRCClickListener itemTopicVideoRCClickListener) {
        this.itemTopicVideoRCClickListener = itemTopicVideoRCClickListener;
    }

    public Topic getItem(int position){
        return list.get(position);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_topic_video
                , parent, false);
        return new TopicVideoAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getName());
        holder.layout.setBackgroundResource(list.get(position).getImg());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = position;
                notifyDataSetChanged();
                itemTopicVideoRCClickListener.onItemClick(view, position);
            }
        });

        if(index == position){
            holder.textView.setTextColor(Color.parseColor("#5151FA"));
        }else
            holder.textView.setTextColor(Color.WHITE);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout layout;
        private TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.layout);
            textView = itemView.findViewById(R.id.tvTopic);

        }

    }

}
