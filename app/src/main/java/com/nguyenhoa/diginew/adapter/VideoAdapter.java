package com.nguyenhoa.diginew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.model.News;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    private Context context;
    private ArrayList<News> list;
    private ItemVideoRCClickListener itemVideoRCClickListener;

    public VideoAdapter(Context context, ArrayList<News> list) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }

    public void setList(ArrayList<News> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public interface ItemVideoRCClickListener {
        void onItemClick(View view, int position);
    }

    public void setItemVideoRCClickListener(ItemVideoRCClickListener itemVideoRCClickListener) {
        this.itemVideoRCClickListener = itemVideoRCClickListener;
    }

    public News getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video
                , parent, false);
        return new VideoAdapter.VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.VideoViewHolder holder, int position) {
        News news = list.get(position);
        if (news == null) return;
        holder.tvSource.setText(list.get(position).getSource());
        holder.tvCmts.setText(String.valueOf(list.get(position).getCmts()));
        holder.tvLikes.setText(String.valueOf(list.get(position).getLikes()));
        holder.tvTime.setText(list.get(position).getTimes() + " " + context.getResources().getString(R.string.time));
        holder.layout.setBackgroundResource(list.get(position).getImgs());
        holder.tvTitle.setText(list.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvSource, tvTime, tvTitle, tvLikes, tvCmts;
        private LinearLayout layout;

        public VideoViewHolder(@NonNull View view) {
            super(view);
            tvSource = view.findViewById(R.id.tvSource);
            tvTime = view.findViewById(R.id.tvTimes);
            tvTitle = view.findViewById(R.id.tvVideoTiltle);
            tvLikes = view.findViewById(R.id.tvLikes);
            tvCmts = view.findViewById(R.id.tvCmt);
            layout = view.findViewById(R.id.layoutVideo);

            view.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {
            if (itemVideoRCClickListener != null) {
                itemVideoRCClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }
}
