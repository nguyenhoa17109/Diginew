package com.nguyenhoa.diginew.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.google.gson.Gson;
import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.common.DownloadImageTask;
import com.nguyenhoa.diginew.common.MyClass;
import com.nguyenhoa.diginew.model.News;
import com.nguyenhoa.diginew.model.Topic;

import java.util.ArrayList;

public class CategoryFvAdapter extends RecyclerView.Adapter<CategoryFvAdapter.CategoryFvViewHolder> {
    private Context context;
    private ArrayList<Topic> list;

    public CategoryFvAdapter(Context context, ArrayList<Topic> list) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }

    public void setData(ArrayList<Topic> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryFvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,
                parent, false);
        return new CategoryFvViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryFvAdapter.CategoryFvViewHolder holder, int position) {
        Topic topic = list.get(position);
        holder.tvTopic.setText(topic.getName());
        holder.ivDelete.setVisibility(View.GONE);
        new DownloadImageTask(holder.layout).execute(topic.getImg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CategoryFvViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTopic;
        private ImageView ivDelete;
        private LinearLayout layout;

        public CategoryFvViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTopic = itemView.findViewById(R.id.tvTopic);
            ivDelete = itemView.findViewById(R.id.ivDelete);
            layout = itemView.findViewById(R.id.layout);
        }

        public TextView getTvTopic() {
            return tvTopic;
        }

        public ImageView getIvDelete() {
            return ivDelete;
        }
    }

    public void removeItem(int position){
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public void addItem(Topic topic){
        list.add(topic);
//        notifyItemInserted();
        notifyDataSetChanged();
    }
}
