package com.nguyenhoa.diginew.categories;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.adapter.NewsInfoRCAdapter;
import com.nguyenhoa.diginew.adapter.NewsRCAdapter;
import com.nguyenhoa.diginew.adapter.VideoAdapter;
import com.nguyenhoa.diginew.common.MyClass;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.common.NewsCallBack;
import com.nguyenhoa.diginew.model.News;
import com.nguyenhoa.diginew.model.Topic;

import java.util.ArrayList;
import java.util.Arrays;


public class CategoriesFragment extends Fragment implements NewsCallBack, CategoriesAdapter.CategorClickInterface,
        NewsInfoRCAdapter.ItemNewsInfoRCClickListener, VideoAdapter.ItemVideoRCClickListener{
    private CategoriesAdapter categoriesAdapter;
    private NewsRCAdapter newsRCAdapter;
    private NewsInfoRCAdapter newsInfoRCAdapter;
    private VideoAdapter videoAdapter;
    private RecyclerView categoryRV, newsRV, newsVideoRV, newsInfoRV;
    private ArrayList<String> topics = new ArrayList<>();
    private ArrayList<News> newsArrayList, newsVideoList, newsInfoList;

    Fragment provincesFragment;
    FragmentManager manager;
    private String p, cate;

    public CategoriesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getChildFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                p = result.getString("bundleKey");
                if(p!=null){
                    hideFragment();
                    searchProvince(p);
                }
            }}
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        provincesFragment = new ProvincesFragment();
        manager = getChildFragmentManager();
        manager.beginTransaction().replace(R.id.frChildCategory, provincesFragment).hide(provincesFragment).commit();

        categoryRV = view.findViewById(R.id.rvCategories);
        newsRV = view.findViewById(R.id.rvNews);
        newsInfoRV = view.findViewById(R.id.rvNewsInfo);
        newsVideoRV = view.findViewById(R.id.rvNewsVideo);

//        topics = new ArrayList<>();
        newsArrayList = new ArrayList<>();
        newsVideoList = new ArrayList<>();
        newsInfoList = new ArrayList<>();

        setTopic();

        if(topics.size() > 1){
            setCate1();
        }

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        newsRV.setLayoutManager(linearLayoutManager1);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        newsInfoRV.setLayoutManager(linearLayoutManager2);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getContext());
        newsVideoRV.setLayoutManager(linearLayoutManager3);

        categoriesAdapter = new CategoriesAdapter(getFragmentManager(), getContext(), this::onCategoryClick);
        newsRCAdapter = new NewsRCAdapter(getContext());
        newsInfoRCAdapter = new NewsInfoRCAdapter(getContext());
        videoAdapter = new VideoAdapter(getContext(), newsVideoList);

        categoriesAdapter.setData(topics);
        newsRCAdapter.setData(newsArrayList, this::onNewsItemClick);
        newsInfoRCAdapter.setData(newsInfoList);

        newsInfoRCAdapter.setClickNewsListener(this::onItemClickInfo);
        videoAdapter.setItemVideoRCClickListener(this::onItemClickVideo);

        categoryRV.setAdapter(categoriesAdapter);
        newsRV.setAdapter(newsRCAdapter);
        newsInfoRV.setAdapter(newsInfoRCAdapter);
        newsVideoRV.setAdapter(videoAdapter);

        hideFragment();

//        if(p!=null){
//            hideFragment();
//            searchProvince(p);
//        }

        return view;
    }

    public void searchProvince(String s){
        topics.clear();
        newsArrayList.clear();
        newsVideoList.clear();
        newsInfoList.clear();

        setVisi();
        setTopic();

        s = s.toLowerCase();
        s = s.replace("thành phố", "");
        s = s.replace("tỉnh", "");

        s = s.trim();

        Log.d("s: ", s);


        ArrayList<News> list = MyList.listNews;

        for(int i=0; i<list.size(); i++) {
            News news = list.get(i);
            if(news.getTitle().toLowerCase().contains(s)){
                if(news.getType().equals("textnews") || news.getType().equals("audio")){
                    newsArrayList.add(news);
                }
                if(news.getType().equals("video")){
                    newsVideoList.add(news);
                }
                if(news.getType().equals("info")){
                    newsInfoList.add(news);
                }
            }
        }
//        categoriesAdapter.setData(topics);
        setDataAdapter();
    }

    @Override
    public void onCategoryClick(int position) {
        String category = topics.get(position);
        cate = category;

        if(category.equals("Địa phương")){
            showFragment();
            newsRV.setVisibility(View.GONE);
            newsVideoRV.setVisibility(View.GONE);
            newsInfoRV.setVisibility(View.GONE);

        }
        else {
            hideFragment();

            setVisi();

            newsArrayList = MyList.sqLite.getAllNewsByTypeAsTopic(category, "textnews");
            ArrayList<News> listAudio = MyList.sqLite.getAllNewsByTypeAsTopic(category,"audio");
            for(int i=0; i< listAudio.size(); i++){
                newsArrayList.add(listAudio.get(i));
            }
            newsVideoList = MyList.sqLite.getAllNewsByTypeAsTopic(category, "video");
            newsInfoList = MyList.sqLite.getAllNewsByTypeAsTopic(category, "info");

            setDataAdapter();

        }
    }

    public void showFragment(){
        manager.beginTransaction().show(provincesFragment).commit();
    }
    public void hideFragment() {
        manager.beginTransaction().hide(provincesFragment).commit();
    }


    @Override
    public void onItemClickInfo(View view, int position) {
        News news = newsInfoRCAdapter.getItem(position);
        MyClass.setIntent(news, getActivity(), null, null, null );
    }

    @Override
    public void onItemClickVideo(View view, int position) {
        News news = videoAdapter.getItem(position);
        MyClass.setIntent(news, getActivity(), null, null, null );
    }

    @Override
    public void onNewsItemClick(int pos, TextView ivTopic, TextView ivSource, TextView tvTime) {
        News news = newsRCAdapter.getItem(pos);
        MyClass.setIntent(news, getActivity(), ivTopic, ivSource, tvTime);
    }

    public void setCate1(){
        newsArrayList = MyList.sqLite.getAllNewsByTypeAsTopic(topics.get(1), "textnews");
        ArrayList<News> listAudio = MyList.sqLite.getAllNewsByTypeAsTopic(topics.get(1),"audio");
        for(int i=0; i< listAudio.size(); i++){
            newsArrayList.add(listAudio.get(i));
        }
        newsVideoList = MyList.sqLite.getAllNewsByTypeAsTopic(topics.get(1), "video");
        newsInfoList = MyList.sqLite.getAllNewsByTypeAsTopic(topics.get(1), "info");
    }

    public void setDataAdapter(){
        newsRCAdapter.setData(newsArrayList, this::onNewsItemClick);
        videoAdapter.setList(newsVideoList);
        newsInfoRCAdapter.setData(newsInfoList);
    }

    public void setTopic(){
//        newsArrayList.clear();
//        newsVideoList.clear();
//        newsInfoList.clear();

        ArrayList<Topic> list1 = MyList.setListChoseSubjectFV(getContext());
        topics.add("Địa phương");
        for(int i=0; i<list1.size(); i++){
            Log.d("list fv: "+i+1, list1.get(i).getName());
            topics.add(list1.get(i).getName());
        }
    }
    public void setVisi(){
        newsRV.setVisibility(View.VISIBLE);
        newsVideoRV.setVisibility(View.VISIBLE);
        newsInfoRV.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStart() {
        super.onStart();

        if(p!=null){

        }
        hideFragment();
        setVisi();
        topics.clear();
        setTopic();
        setCate1();
        categoriesAdapter.setData(topics);
        setDataAdapter();
    }
}