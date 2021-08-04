package com.nguyenhoa.diginew.categories;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.discover.DiscoverAdapter;
import com.nguyenhoa.diginew.model.Topic;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHoler> {
    private Context context;
    private ArrayList<String> names;
    private CategorClickInterface categorClickInterface;
    private int index = 1;
    FragmentManager fragmentManager;

    public CategoriesAdapter(FragmentManager fragmentManager, Context context, ArrayList<String> names, CategorClickInterface categorClickInterface) {
        this.fragmentManager = fragmentManager;
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
        holder.textView.setText(names.get(position));

        if(index == 1){
            holder.textView.setTextColor(Color.WHITE);
            holder.textView.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_fill));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = position;
                notifyDataSetChanged();
                categorClickInterface.onCategoryClick(position);
            }
        });
        if(index == position){
            holder.textView.setTextColor(Color.WHITE);
            holder.textView.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_fill));
        }
        else{
            holder.textView.setTextColor(Color.parseColor("#B1BAD0"));
            holder.textView.setBackground(null);
        }
    }

    public interface CategorClickInterface{
        void onCategoryClick(int position);
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class MyViewHoler extends RecyclerView.ViewHolder {
        private TextView textView;

        public MyViewHoler(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvItemCategory);
        }
    }
}
