package com.nguyenhoa.diginew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.model.Topic;

import java.util.ArrayList;

public class AddCategoryAdapter extends RecyclerView.Adapter<AddCategoryAdapter.AddCategoryViewHolder> {
    private Context context;
    private ArrayList<Topic> list;

    public AddCategoryAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Topic> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AddCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_cateogry,
                parent, false);
        return new AddCategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AddCategoryAdapter.AddCategoryViewHolder holder, int position) {
        Topic topic = list.get(position);
        holder.tvTopic.setText(topic.getName());
        holder.btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.btAdd.setBackgroundResource(R.drawable.background_button_add_disable);
                holder.btAdd.setText(R.string.added);
                list.get(position).setSelected(true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AddCategoryViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTopic;
        private Button btAdd;

        public AddCategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTopic = itemView.findViewById(R.id.tvCategory);
            btAdd = itemView.findViewById(R.id.btAdd);
        }
    }
}
