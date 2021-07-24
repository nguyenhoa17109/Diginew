package com.nguyenhoa.diginew.splash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nguyenhoa.diginew.R;

public class SubjectsAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private String[] numberSubject;
    private int[] numberImage;

    public SubjectsAdapter(@NonNull Context context, String[] numberSubject, int[] numberImage) {
        this.context = context;
        this.numberImage = numberImage;
        this.numberSubject = numberSubject;
    }

    @Override
    public int getCount() {
        return numberSubject.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_subjects, null);
        }

        TextView tvSubject = convertView.findViewById(R.id.tvItemSubjects);
        ImageView ivSubject = convertView.findViewById(R.id.ivItemSubjects);

        ivSubject.setImageResource(numberImage[position]);
        tvSubject.setText(numberSubject[position]);
        return convertView;
    }

}
