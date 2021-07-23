package com.nguyenhoa.diginew.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.model.News;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {
    private Context context;
    private ArrayList<News> list;

    public NewsAdapter( Context context, ArrayList<News> list) {
        super(context, R.layout.item_news, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_news, parent, false);

        TextView tvSource = view.findViewById(R.id.tvSource);
        TextView tvTime = view.findViewById(R.id.tvTimes);
        ImageView ivNews = view.findViewById(R.id.ivNews);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvLikes = view.findViewById(R.id.tvLikes);
        TextView tvCmts = view.findViewById(R.id.tvCmts);

        String type = list.get(position).getType();

        tvSource.setText(list.get(position).getSource());
        tvCmts.setText(String.valueOf(list.get(position).getCmts()));
        tvLikes.setText(String.valueOf(list.get(position).getLikes()));
        tvTime.setText(list.get(position).getTimes()+" "+view.getResources().getString(R.string.time));
        ivNews.setImageResource(list.get(position).getImgs());
        tvTitle.setText(list.get(position).getTitle());

        return view;

    }
}
