package com.nguyenhoa.diginew.home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nguyenhoa.diginew.CategoryFvActivity;
import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.adapter.NewsRCAdapter;
import com.nguyenhoa.diginew.common.CustomItemAnimator;
import com.nguyenhoa.diginew.common.MyClass;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.common.NewsCallBack;
import com.nguyenhoa.diginew.model.News;
import com.nguyenhoa.diginew.news.News1Activity;
import com.smarteist.autoimageslider.SliderView;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements  NewsCallBack {
    private ImageView ivSearch, ivUser;
    private SliderView sliderView;
    private RecyclerView rvNews;
    private NewsRCAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        init(v);
        setSlider();
        setRV();
        setClick();

        return v;
    }

    private void setClick() {
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
        ivUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CategoryFvActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setRV() {
        adapter = new NewsRCAdapter(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL, false);
        rvNews.setLayoutManager(manager);
        adapter.setData(MyList.listNews, this::onNewsItemClick);
//        adapter.setClickNewsListener(this::onItemClick);
        rvNews.setAdapter(adapter);
        rvNews.setItemAnimator(new CustomItemAnimator());
    }

    private void setSlider() {
        NewsSlideAdapter newsSlideAdapter = new NewsSlideAdapter(MyList.listNews);
        sliderView.setSliderAdapter(newsSlideAdapter);
        sliderView.startAutoCycle();
        sliderView.setSliderAnimationDuration(500);
        newsSlideAdapter.setItemSlideNewsClick(new NewsSlideAdapter.ItemSlideNewsClick() {
            @Override
            public void OnItemClick(View view, int position, TextView tv) {
                News news = newsSlideAdapter.getItem(position);
                MyClass.setIntent(news, getActivity(), tv, null, null);
            }
        });
    }

    private void init(View v) {
        sliderView = v.findViewById(R.id.slideSplash1);
        ivSearch = v.findViewById(R.id.ivSearch);
        ivUser = v.findViewById(R.id.ivUser);
        rvNews = v.findViewById(R.id.rcNews);
    }

//    @Override
//    public void onItemClick(View view, int position, TextView tv) {
////        News news = adapter.getItem(position);
////        MyClass.setIntent(news, (Activity) view.getContext(), tv);
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sliderView.stopAutoCycle();
    }

    @Override
    public void onPause() {
        super.onPause();
//        sliderView.
    }

    @Override
    public void onNewsItemClick(int pos, TextView ivTopic, TextView ivSource, TextView tvTime) {
        News news = adapter.getItem(pos);
        MyClass.setIntent(news, getActivity(), ivTopic, ivSource, tvTime);
    }
}