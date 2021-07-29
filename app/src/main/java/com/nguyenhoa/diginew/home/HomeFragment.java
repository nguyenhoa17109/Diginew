package com.nguyenhoa.diginew.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.nguyenhoa.diginew.InfoNewsActivity;
import com.nguyenhoa.diginew.NewsActivity;
import com.nguyenhoa.diginew.PlayVideoActivity;
import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.SearchActivity;
import com.nguyenhoa.diginew.common.MyClass;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.News;
import com.smarteist.autoimageslider.SliderView;
import com.synnapps.carouselview.ImageListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private ImageView ivSearch, ivUser;
    private SliderView sliderView;
    private ListView listView;
    private int[] sample = {R.drawable.ic_digiclips, R.drawable.ic_digihealth, R.drawable.ic_digimovie};

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
        sliderView = v.findViewById(R.id.slideSplash1);

        NewsSlideAdapter newsSlideAdapter = new NewsSlideAdapter(MyList.listNews);
        sliderView.setSliderAdapter(newsSlideAdapter);
        sliderView.startAutoCycle();
        newsSlideAdapter.setItemSlideNewsClick(new NewsSlideAdapter.ItemSlideNewsClick() {
            @Override
            public void OnItemClick(View view, int position) {
                News news = newsSlideAdapter.getItem(position);
                MyClass.setIntent(news, getActivity());
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
        NewsAdapter adapter = new NewsAdapter(getContext(), MyList.listNews);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                News news = (News) adapterView.getItemAtPosition(i);
                MyClass.setIntent(news, getActivity());
            }
        });
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

}