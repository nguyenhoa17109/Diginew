package com.nguyenhoa.diginew.categories;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.home.NewsAdapter;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.News;

import java.util.ArrayList;


public class CategoriesFragment extends Fragment implements CategoriesAdapter.CategorClickInterface {
    private CategoriesAdapter categoriesAdapter;
    private RecyclerView categoryRV;
    private String[] topics = {"Địa phương", "Đời sống", "Kinh tế", "Sức khỏe", "Xã hội", "Khoa hoc", "Giải trí", "Công nghệ", "Thể thao", "Tâm sự"};

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

        categoryRV = view.findViewById(R.id.rvCategories);
        categoriesAdapter = new CategoriesAdapter(getFragmentManager(), getContext(), topics, this::onCategoryClick);
        categoryRV.setAdapter(categoriesAdapter);

        Bundle bundle = new Bundle();
        bundle.putString("category", "Đời sống");
        Fragment childFragment = new ChildFragment();
        childFragment.setArguments(bundle);
        getChildFragmentManager().beginTransaction().replace(R.id.frChildCategory, childFragment).commit();

        return view;
    }


    @Override
    public void onCategoryClick(int position) {
        String category = MyList.list.get(position).getName();

        if (category.equals("Địa phương")) {
            insertNestedFragment();
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("category", category);
            Fragment childFragment = new ChildFragment();
            childFragment.setArguments(bundle);
            getChildFragmentManager().beginTransaction().replace(R.id.frChildCategory, childFragment).commit();
        }

    }

    public static class ChildFragment extends Fragment {
        private NewsAdapter newsAdapter;
        private ListView newsRV;
        private ArrayList<News> newsArrayList;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_child_category, container, false);

            newsRV = view.findViewById(R.id.rvNews);
            newsArrayList = new ArrayList<>();
            newsAdapter = new NewsAdapter(getContext(), newsArrayList);
            newsRV.setAdapter(newsAdapter);

//            newsAdapter.notifyDataSetChanged();

            String topic = getArguments().getString("category");
            receiveData(topic);

            return view;
        }

        private void receiveData(String s) {
            newsArrayList.clear();

            if (s.equals("Đời sống")) {
                newsArrayList.add(new News("Đời sống", "text", "Vietnamnet", 6, "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 100, 200,
                        R.drawable.img_clip, "abc"));
                newsArrayList.add(new News("Đời sống", "text", "Vietnamnet", 6, "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 100, 200,
                        R.drawable.img_clip, "abc"));
            } else if (s.equals("Kinh tế")) {
                newsArrayList.add(new News("Kinh tế", "text", "Vietnamnet", 6, "Kinh tế nè", 100, 200,
                        R.drawable.img_clip, "abc"));
                newsArrayList.add(new News("Kinh tế", "text", "Vietnamnet", 6, "Kinh tế nè", 100, 200,
                        R.drawable.img_clip, "abc"));
            } else if (s.equals("Sức khỏe")) {
                newsArrayList.add(new News("Sức khỏe", "text", "Vietnamnet", 6, "Sức khỏe nè", 100, 200,
                        R.drawable.img_clip, "abc"));
                newsArrayList.add(new News("Sức khỏe", "text", "Vietnamnet", 6, "Sức khỏe nè", 100, 200,
                        R.drawable.img_clip, "abc"));
            } else if (s.equals("Thành phố Hà Nội")) {
                newsArrayList.add(new News("Địa phương", "text", "Vietnamnet", 6, "Địa phương TP HN", 100, 200,
                        R.drawable.img_clip, "abc"));
                newsArrayList.add(new News("Địa phương", "text", "Vietnamnet", 6, "Địa phương TP HN", 100, 200,
                        R.drawable.img_clip, "abc"));
            } else if (s.equals("Tỉnh Hà Giang")) {
                newsArrayList.add(new News("Địa phương", "text", "Vietnamnet", 6, "Địa phương tỉnh Hà Giang", 100, 200,
                        R.drawable.img_clip, "abc"));
                newsArrayList.add(new News("Địa phương", "text", "Vietnamnet", 6, "Địa phương tỉnh Hà Giang", 100, 200,
                        R.drawable.img_clip, "abc"));
            } else if (s.equals("Tỉnh Cao Bằng")) {
                newsArrayList.add(new News("Địa phương", "text", "Vietnamnet", 6, "Địa phương tỉnh Cao Bằng", 100, 200,
                        R.drawable.img_clip, "abc"));
                newsArrayList.add(new News("Địa phương", "text", "Vietnamnet", 6, "Địa phương tỉnh Cao Bằng", 100, 200,
                        R.drawable.img_clip, "abc"));
            } else {
                newsArrayList.add(new News("Địa phương", "text", "Vietnamnet", 6, "Dữ liệu khác", 100, 200,
                        R.drawable.img_clip, "abc"));
            }
            newsAdapter.notifyDataSetChanged();

        }

    }

    private void insertNestedFragment() {
        Fragment provincesFragment = new ProvincesFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.frChildCategory, provincesFragment).commit();
    }
}