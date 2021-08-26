package com.nguyenhoa.diginew.discover;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.common.MyClass;
import com.nguyenhoa.diginew.model.OtherApp;
import com.nguyenhoa.diginew.model.Topic;

import java.util.ArrayList;


public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.MyViewHolder> {
    Context context;
    private ArrayList<OtherApp> list;
    public OnItemClick onItemClick;

    public interface OnItemClick{
        void ItemClick(int index);
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public DiscoverAdapter(Context context, ArrayList<OtherApp> list) {
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

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            Intent intent;
//            @Override
//            public void onClick(View v) {
//                switch (position){
//                    case 0:
////                        intent = new Intent(context, DigiMovie.class);
//
//                        break;
//                    case 1:
//                        intent = new Intent(context, DigiClip.class);
//                        break;
//                    case 2:
//                        intent = new Intent(context, DigiMusic.class);
//                        break;
//                    case 3:
//                        intent = new Intent(context, DigiHealth.class);
//                        break;
//                    case 4:
//                        intent = new Intent(context, MyTv.class);
//                        break;
//                }
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imageView;
        private TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ivItemDiscover);
            textView = itemView.findViewById(R.id.tvItemDiscover);

            itemView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {
            if(onItemClick != null)
                onItemClick.ItemClick(getAdapterPosition());
        }
    }

    public OtherApp getItem(int index){
        return list.get(index);
    }
}
