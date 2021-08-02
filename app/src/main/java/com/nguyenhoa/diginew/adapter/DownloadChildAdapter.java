package com.nguyenhoa.diginew.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DownloadChildAdapter extends NewsRCAdapter {
    public DownloadChildAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRCAdapter.NewsViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.ivDelete.setVisibility(View.VISIBLE);
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(position);
            }
        });
    }
}
