package com.nguyenhoa.diginew.categories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.model.Province;

import java.util.List;

public class ProvincesAdapter extends RecyclerView.Adapter<ProvincesAdapter.MyViewHolder>{
    private List<Province> provinceList;
    private int index = -1;
    IClickListener iClickListener;

    public ProvincesAdapter(List<Province> provinceList, IClickListener iClickListener) {
        this.provinceList = provinceList;
        this.iClickListener = iClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_provinces, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);

        return viewHolder;
    }

    public interface IClickListener{
        public void clickItem(Province province);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Province province = provinceList.get(position);

        holder.textView.setText(province.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = position;
                notifyDataSetChanged();
                iClickListener.clickItem(province);
            }
        });
        if(index == position){
            holder.imageView.setVisibility(View.VISIBLE);
        }
        else{
            holder.imageView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return provinceList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvItemProvinces);
            imageView = itemView.findViewById(R.id.ivItemProvinces);
        }
    }
}
