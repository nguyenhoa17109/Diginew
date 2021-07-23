package com.nguyenhoa.diginew.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.SearchActivity;
import com.nguyenhoa.diginew.model.MyList;
import com.nguyenhoa.diginew.model.News;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private ImageView ivSearch, ivUser;
    private CarouselView carouselView;
    private ListView listView;
    private int[] sample = {R.drawable.img_clip, R.drawable.img_health, R.drawable.img_movie};

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
//        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        TextView tv;
//        tv.setB
        carouselView = v.findViewById(R.id.carouselView);
        carouselView.setPageCount(sample.length);
        carouselView.setImageListener(imageListener);
//        carouselView.setIma
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getActivity(), "Clicked item: "+ position, Toast.LENGTH_SHORT).show();
            }
        });

        ivSearch = v.findViewById(R.id.ivSearch);
        ivUser = v.findViewById(R.id.ivUser);
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

            }
        });

        listView = v.findViewById(R.id.lvNews);
        NewsAdapter adapter = new NewsAdapter(getContext(), setData());
//        Log.d("AL", ""+MyList.listNews.size());
        listView.setAdapter(adapter);
        return v;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sample[position]);

            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            params.width = 80;
            params.height = 90;
            imageView.setLayoutParams(params);
        }
    };

    private ArrayList<News> setData(){
        ArrayList<News> listNews = new ArrayList<>();
        listNews.add(new News("text", "Vietnamnet", 6,
                "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 100, 200,
                R.drawable.img_clip, "abc"));
        listNews.add(new News("text", "Vietnamnet", 6,
                "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 100, 200,
                R.drawable.img_clip, "abc"));
        return listNews;
    }

}