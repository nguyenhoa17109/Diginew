package com.nguyenhoa.diginew.categories;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhoa.diginew.R;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHoler> {
    private Context context;
    private String[] names;
    private CategorClickInterface categorClickInterface;
    private int index = -1;
    public CategoriesAdapter(Context context, String[] names, CategorClickInterface categorClickInterface) {
        this.context = context;
        this.names = names;
        this.categorClickInterface = categorClickInterface;
    }

    @NonNull
    @Override
    public MyViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_categories, parent, false);
        MyViewHoler viewHolder = new MyViewHoler(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoler holder, int position) {
        holder.textView.setText(names[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = position;
                notifyDataSetChanged();
                categorClickInterface.onCategoryClick(position);
            }
        });
        if(index == position){
            holder.itemView.setBackgroundColor(Color.BLUE);
            holder.textView.setTextColor(Color.WHITE);
        }
        else{
            holder.itemView.setBackgroundColor(Color.WHITE);
            holder.textView.setTextColor(Color.BLACK);
        }
    }

    public interface CategorClickInterface{
        void onCategoryClick(int position);
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public class MyViewHoler extends RecyclerView.ViewHolder {
        private TextView textView;

        public MyViewHoler(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvItemCategory);
        }
    }
}
