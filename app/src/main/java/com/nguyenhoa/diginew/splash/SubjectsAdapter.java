package com.nguyenhoa.diginew.splash;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.model.Subject;

import java.util.ArrayList;

public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Subject> data;

    public SubjectsAdapter(Context context, ArrayList<Subject> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_subjects, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Subject subject = data.get(position);

        holder.tvSubject.setText(subject.getName());
        holder.ivSubject.setImageResource(subject.getImg());

        if(subject.getSelected()){
            holder.itemView.setBackgroundColor(Color.BLUE);
        }
        else{
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(subject.getSelected()){
                    holder.itemView.setBackgroundColor(Color.TRANSPARENT);
                    subject.setSelected(false);
                }
                else{
                    holder.itemView.setBackgroundColor(Color.BLUE);
                    subject.setSelected(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvSubject;
        ImageView ivSubject;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubject = itemView.findViewById(R.id.tvItemSubjects);
            ivSubject = itemView.findViewById(R.id.ivItemSubjects);
        }
    }
}
