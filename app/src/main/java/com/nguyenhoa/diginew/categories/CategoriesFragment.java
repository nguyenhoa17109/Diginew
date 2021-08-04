package com.nguyenhoa.diginew.categories;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.adapter.NewsInfoRCAdapter;
import com.nguyenhoa.diginew.adapter.NewsRCAdapter;
import com.nguyenhoa.diginew.adapter.VideoAdapter;
import com.nguyenhoa.diginew.common.MyClass;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.News;
import com.nguyenhoa.diginew.model.Topic;

import java.util.ArrayList;


public class CategoriesFragment extends Fragment implements CategoriesAdapter.CategorClickInterface{
    private CategoriesAdapter categoriesAdapter;
    private RecyclerView categoryRV;
    private ArrayList<String> topics = new ArrayList<>();
//    private String[] topics = {"Địa phương", "Đời sống", "Kinh tế", "Sức khỏe", "Xã hội", "Khoa học", "Giải trí", "Công nghệ", "Thể thao", "Tâm sự"};

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

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        Gson gson = new Gson();
        String json = sharedPreferences.getString("listSubjectFV", "");
        MyList.list_Fv = gson.fromJson(json, new TypeToken<ArrayList<Topic>>(){}.getType());

        topics.add("Địa phương");
        for(int i=0; i< MyList.list_Fv.size(); i++){
            topics.add(MyList.list_Fv.get(i).getName());
        }

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
        String category = topics.get(position);

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

    public static class ChildFragment extends Fragment implements NewsRCAdapter.ItemNewsRCClickListener,
            NewsInfoRCAdapter.ItemNewsInfoRCClickListener, VideoAdapter.ItemVideoRCClickListener{
        private NewsRCAdapter newsRCAdapter;
        private NewsInfoRCAdapter newsInfoRCAdapter;
        private VideoAdapter videoAdapter;
        private RecyclerView newsRV, newsVideoRV, newsInfoRV;
        private ArrayList<News> newsArrayList, newsVideoList, newsInfoList;
        private String category, province;

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
            newsInfoRV = view.findViewById(R.id.rvNewsInfo);
            newsVideoRV = view.findViewById(R.id.rvNewsVideo);

            newsArrayList = new ArrayList<>();
            newsVideoList = new ArrayList<>();
            newsInfoList = new ArrayList<>();

            LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
            newsRV.setLayoutManager(linearLayoutManager1);
            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
            newsInfoRV.setLayoutManager(linearLayoutManager2);
            LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getContext());
            newsVideoRV.setLayoutManager(linearLayoutManager3);

            newsRCAdapter = new NewsRCAdapter(getContext());
            newsInfoRCAdapter = new NewsInfoRCAdapter(getContext());
            videoAdapter = new VideoAdapter(getContext(), newsVideoList);

            newsRCAdapter.setData(newsArrayList);
            newsInfoRCAdapter.setData(newsInfoList);

            newsRCAdapter.setClickNewsListener(this::onItemClick);
            newsInfoRCAdapter.setClickNewsListener(this::onItemClickInfo);
            videoAdapter.setItemVideoRCClickListener(this::onItemClickVideo);

            newsRV.setAdapter(newsRCAdapter);
            newsInfoRV.setAdapter(newsInfoRCAdapter);
            newsVideoRV.setAdapter(videoAdapter);


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
                    if(list.get(i).getType().equals("video")){
                        newsVideoList.add(list.get(i));
                    }
                    if(list.get(i).getType().equals("info")){
                        newsInfoList.add(list.get(i));
                    }
                }
            }
            newsRCAdapter.notifyDataSetChanged();
            videoAdapter.notifyDataSetChanged();
            newsInfoRCAdapter.notifyDataSetChanged();
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

        @Override
        public void onItemClickInfo(View view, int position) {
            News news = (News) newsInfoRCAdapter.getItem(position);
            MyClass.setIntent(news, getActivity());
        }

        @Override
        public void onItemClickVideo(View view, int position) {
            News news = (News) videoAdapter.getItem(position);
            MyClass.setIntent(news, getActivity());
        }
    }

    private void insertNestedFragment(){
        Fragment provincesFragment = new ProvincesFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.frChildCategory, provincesFragment).commit();
    }
}