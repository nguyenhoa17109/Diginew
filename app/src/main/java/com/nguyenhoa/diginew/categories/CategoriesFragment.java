package com.nguyenhoa.diginew.categories;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.nguyenhoa.diginew.AudioNews;
import com.nguyenhoa.diginew.NewsActivity;
import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.adapter.NewsRCAdapter;
import com.nguyenhoa.diginew.common.MyClass;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.home.NewsAdapter;
import com.nguyenhoa.diginew.model.News;

import java.util.ArrayList;


public class CategoriesFragment extends Fragment implements CategoriesAdapter.CategorClickInterface{
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
        String category = topics[position];

        if(category.equals("Địa phương")){
            insertNestedFragment();
        }
        else {
            Bundle bundle = new Bundle();
            bundle.putString("category", category);
            Fragment childFragment = new ChildFragment();
            childFragment.setArguments(bundle);
            getChildFragmentManager().beginTransaction().replace(R.id.frChildCategory, childFragment).commit();
        }

    }

    public static class ChildFragment extends Fragment implements NewsRCAdapter.ItemNewsRCClickListener{
        private NewsRCAdapter newsRCAdapter;
        private RecyclerView newsRV;
        private ArrayList<News> newsArrayList;
        private String category, province;
        private NewsRCAdapter.ItemNewsRCClickListener itemNewsRCClickListener;

        public static ChildFragment newInstance(String s1, String s2){
            ChildFragment childFragment = new ChildFragment();
            Bundle bundle = new Bundle();
            bundle.putString("category", s1);
            bundle.putString("province", s2);
            childFragment.setArguments(bundle);
            return childFragment;
        }
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null){
                category = getArguments().getString("category");
                province = getArguments().getString("province");
            }
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view =  inflater.inflate(R.layout.fragment_child_category, container, false);

            newsRV = view.findViewById(R.id.rvNews);
            newsArrayList = new ArrayList<>();

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            newsRV.setLayoutManager(linearLayoutManager);

            newsRCAdapter = new NewsRCAdapter(getContext());
            newsRCAdapter.setData(newsArrayList);
            newsRCAdapter.setClickNewsListener(this::onItemClick);
            newsRV.setAdapter(newsRCAdapter);

            if (province == null){
                receiveData(category);
            }
            else{
                searchProvince(province);
            }

            return view;
        }

        private void receiveData(String s) {
            newsArrayList.clear();

            ArrayList<News> list = MyList.listNews;

            for(int i=0; i<list.size(); i++){
                if(list.get(i).getTopic().equals(s)){
                    newsArrayList.add(list.get(i));
                }
            }
            newsRCAdapter.notifyDataSetChanged();
        }

        private void searchProvince(String s){
            newsArrayList.clear();

            s = s.toLowerCase();
            s = s.replace("thành phố", "");
            s = s.replace("tỉnh", "");

            ArrayList<News> list = MyList.listNews;
            String s1, s2;
            for (int i=0; i<list.size(); i++){
                s1 = list.get(i).getTitle().toLowerCase();
                s2 = list.get(i).getContent().toLowerCase();
                if(s1.contains(s.toLowerCase()) || s2.contains(s.toLowerCase())){
                    newsArrayList.add(list.get(i));
                }
            }
            newsRCAdapter.notifyDataSetChanged();
        }

        @Override
        public void onItemClick(View view, int position) {
            News news = (News) newsRCAdapter.getItem(position);
            MyClass.setIntent(news, getActivity());
        }
    }

    private void insertNestedFragment(){
        Fragment provincesFragment = new ProvincesFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.frChildCategory, provincesFragment).commit();
    }
}