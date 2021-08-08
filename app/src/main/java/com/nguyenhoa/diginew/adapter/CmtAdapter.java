package com.nguyenhoa.diginew.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.common.MyClass;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.Comment;
import com.nguyenhoa.diginew.model.User;

import java.util.ArrayList;

public class CmtAdapter extends RecyclerView.Adapter<CmtAdapter.CmtViewHolder> {
    private ArrayList<Comment> list;
    private Context context;
    private String cmt;

//    public interface


    public CmtAdapter(ArrayList<Comment> list, Context context) {
        this.list = list;
        this.context = context;
        notifyDataSetChanged();
    }
    public Comment getItem(int position){
        return list.get(position);
    }

    @NonNull
    @Override
    public CmtViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_cmt, parent, false);
        return new CmtAdapter.CmtViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CmtAdapter.CmtViewHolder holder, int position) {
        Comment comment = list.get(position);
        holder.imgAccount.setImageResource(comment.getUser().getImgAccount());
        holder.tvNameUser.setText(comment.getUser().getNameUser());
        holder.tvContentCmt.setText(comment.getContentCmt());
        holder.tvTimeCmt.setText(String.valueOf(comment.getLike()+" "
                +context.getResources().getString(R.string.time)));
        holder.tvLike.setText(String.valueOf(comment.getLike()));



        holder.tvLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyClass.setTVLike(holder.tvLike, holder.itemView.getContext());
            }
        });

        holder.tvAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNewCmt(holder.itemView.getContext(), position, true);
            }
        });
        if(!comment.isLikeCmt() || comment.getLike() == 0){
            holder.tvLike.setTextColor(Color.parseColor("#A2A6BB"));
            holder.tvLike.setCompoundDrawablesWithIntrinsicBounds(null, null,
                    holder.itemView.getContext().getResources().getDrawable(R.drawable.ic_like), null);
        }else{
            holder.tvLike.setTextColor(Color.parseColor("#0C8DFD"));
            holder.tvLike.setCompoundDrawablesWithIntrinsicBounds(null, null,
                    holder.itemView.getContext().getResources().getDrawable(R.drawable.ic_liked), null);
        }

        if(comment.isAnswer()){
            holder.layout.setPadding(100, 0, 0, 0);
        }
    }

    public void displayNewCmt(String s, int position, boolean isAnswer) {
        Log.d("LO", s);
        Comment comment1 = new Comment();
        comment1.setUser(new User( MyList.account.getImg(), MyList.account.getName()));
        comment1.setAnswer(isAnswer);
        comment1.setContentCmt(s);
        comment1.setLike(0);
        comment1.setTime("1");
        comment1.setLikeCmt(false);
        list.add(position, comment1);
        notifyDataSetChanged();
    }

    public void sendNewCmt(Context context, int position, boolean isAnswer){
        final String[] s = {""};
        View viewCmt = LayoutInflater.from(context)
                .inflate(R.layout.layout_send_comment, null);

        TextView tvSend;
        EditText etCmt;
        ImageView ivClose;
        tvSend = viewCmt.findViewById(R.id.tvSend);
        etCmt = viewCmt.findViewById(R.id.etCmt);
        ivClose = viewCmt.findViewById(R.id.ivClose);

        Dialog dialog = new Dialog(context, R.style.MaterialDialogSheet);
        dialog.setContentView(viewCmt);
        dialog.setCancelable(true);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.show();

        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cmt = etCmt.getText().toString();
                if(isAnswer)
                    displayNewCmt(cmt, position+1, isAnswer);
                else
                    displayNewCmt(cmt, position, isAnswer);
                dialog.dismiss();
            }
        });

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CmtViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private ImageView imgAccount;
        private TextView tvNameUser, tvContentCmt, tvTimeCmt, tvAnswer, tvLike;

        public CmtViewHolder(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.layoutCmt);
            imgAccount = itemView.findViewById(R.id.ivCmtAccount);
            tvNameUser = itemView.findViewById(R.id.tvCmtName);
            tvContentCmt = itemView.findViewById(R.id.tvCmtContent);
            tvTimeCmt = itemView.findViewById(R.id.tvCmtTime);
            tvAnswer = itemView.findViewById(R.id.tvAnswer);
            tvLike = itemView.findViewById(R.id.tvCmtLike);
        }
    }
}
