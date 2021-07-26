package com.nguyenhoa.diginew.categories;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.home.NewsAdapter;
import com.nguyenhoa.diginew.model.News;

import java.util.ArrayList;


public class CategoriesFragment extends Fragment implements CategoriesAdapter.CategorClickInterface{
    private CategoriesAdapter categoriesAdapter;
    private NewsAdapter newsAdapter;
    private RecyclerView categoryRV;
    private ListView newsRV;
    private String[] topics = {"Đời sống", "Kinh tế", "Sức khỏe", "Xã hội", "Khoa hoc", "Giải trí", "Công nghệ", "Thể thao", "Tâm sự"};
    private ArrayList<News> newsArrayList;

    public CategoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        newsRV = view.findViewById(R.id.rvNews);
        categoryRV = view.findViewById(R.id.rvCategories);

        newsArrayList = new ArrayList<>();

        newsAdapter = new NewsAdapter(getContext(), newsArrayList);
        categoriesAdapter = new CategoriesAdapter(getContext(), topics, this::onCategoryClick);

        newsRV.setAdapter(newsAdapter);
        categoryRV.setAdapter(categoriesAdapter);

        getDataNews("Đời sống");

        newsAdapter.notifyDataSetChanged();

        return view;
    }


    private void getDataNews(String topic){
        newsArrayList.clear();
        if(topic == "Đời sống"){
            newsArrayList.add(new News("Đời sống", "text", "Vietnamnet", 6,
                    "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 100, 200,
                    R.drawable.img_clip, "abc"));
            newsArrayList.add(new News("Đời sống","text", "Vietnamnet", 6,
                    "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 100, 200,
                    R.drawable.img_clip, "abc"));

            newsAdapter.notifyDataSetChanged();
        }
        else if(topic == "Kinh tế"){
            newsArrayList.add(new News("Kinh tế","text", "Vietnamnet", 6,
                    "Kinh te ne", 100, 200,
                    R.drawable.img_clip, "abc"));
            newsArrayList.add(new News("Kinh tế","text", "Vietnamnet", 6,
                    "Kinh te ne", 100, 200,
                    R.drawable.img_clip, "abc"));
            newsArrayList.add(new News("Kinh tế","text", "Vietnamnet", 6,
                    "Kinh te ne", 100, 200,
                    R.drawable.img_clip, "abc"));

            newsAdapter.notifyDataSetChanged();

        }
        else if(topic == "Sức khỏe"){
            newsArrayList.add(new News("Sức khỏe","text", "Vietnamnet", 6,
                    "Suc khoe ne", 100, 200,
                    R.drawable.img_clip, "abc"));

            newsAdapter.notifyDataSetChanged();
        }
        else{
            newsArrayList.clear();
            newsAdapter.notifyDataSetChanged();
            Toast.makeText(getContext(), "Chưa có dữ liệu", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onCategoryClick(int position) {
        String category = topics[position];
        getDataNews(category);
    }
}