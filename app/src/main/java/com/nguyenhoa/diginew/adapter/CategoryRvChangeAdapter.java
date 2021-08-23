package com.nguyenhoa.diginew.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.nguyenhoa.diginew.model.Topic;

import java.util.ArrayList;

public class CategoryRvChangeAdapter extends CategoryFvAdapter{
    private CategoryChangeListener categoryChangeListener;

    public CategoryRvChangeAdapter(Context context, ArrayList<Topic> list, CategoryChangeListener categoryChangeListener) {
        super(context, list);
        this.categoryChangeListener = categoryChangeListener;
        notifyDataSetChanged();

    }

    public interface CategoryChangeListener{
        public void onCategoryChangeItem(int position);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryFvAdapter.CategoryFvViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        holder.getIvDelete().setVisibility(View.VISIBLE);
        holder.getIvDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(position);
                notifyItemRemoved(position);
                categoryChangeListener.onCategoryChangeItem(position);
            }
        });
    }
}
