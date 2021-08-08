package com.nguyenhoa.diginew.discover;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.categories.CategoriesFragment;
import com.nguyenhoa.diginew.model.Topic;

import java.util.ArrayList;


public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.MyViewHolder> {
    Context context;
    private ArrayList<Topic> list;

    public DiscoverAdapter(Context context, ArrayList<Topic> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_discover, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getName());
        holder.imageView.setImageResource(list.get(position).getImg());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            Intent intent;
            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                        intent = new Intent(context, DigiMovie.class);
                        break;
                    case 1:
                        intent = new Intent(context, DigiClip.class);
                        break;
                    case 2:
                        intent = new Intent(context, DigiMusic.class);
                        break;
                    case 3:
                        intent = new Intent(context, DigiHealth.class);
                        break;
                    case 4:
                        intent = new Intent(context, MyTv.class);
                        break;
                }
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ivItemDiscover);
            textView = itemView.findViewById(R.id.tvItemDiscover);

        }
    }

}
