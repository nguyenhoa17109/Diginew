package com.nguyenhoa.diginew.video;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nguyenhoa.diginew.PlayVideoActivity;
import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.adapter.TopicVideoAdapter;
import com.nguyenhoa.diginew.adapter.VideoAdapter;
import com.nguyenhoa.diginew.model.News;
import com.nguyenhoa.diginew.model.Topic;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoFragment extends Fragment {
    private RecyclerView rcTopic, rcVideo;
    private VideoAdapter adapterVideo;
    private TopicVideoAdapter adapterTopic;
    private ArrayList<ArrayList<News>> arrayLists;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VideoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VideoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VideoFragment newInstance(String param1, String param2) {
        VideoFragment fragment = new VideoFragment();
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
        View v = inflater.inflate(R.layout.fragment_video, container, false);
        init(v);
        return v;
    }
    private void init(View v) {
        setData(setListTopic(), setListNews());
        rcTopic = v.findViewById(R.id.rvTopicVideo);
        rcVideo = v.findViewById(R.id.rvVideo);
        adapterTopic = new TopicVideoAdapter(v.getContext(), setListTopic());
        LinearLayoutManager manager = new LinearLayoutManager(v.getContext(),
                RecyclerView.HORIZONTAL, false);
        rcTopic.setLayoutManager(manager);
        rcTopic.setAdapter(adapterTopic);

        adapterVideo= new VideoAdapter(v.getContext(), arrayLists.get(0));
        LinearLayoutManager manager1 = new LinearLayoutManager(v.getContext(),
                RecyclerView.VERTICAL, false);
        rcVideo.setLayoutManager(manager1);
        rcVideo.setAdapter(adapterVideo);

        adapterTopic.setItemTopicVideoRCClickListener(new TopicVideoAdapter.ItemTopicVideoRCClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                adapterVideo.setList(arrayLists.get(position));
                adapterVideo.notifyDataSetChanged();
            }
        });
        adapterVideo.setItemVideoRCClickListener(new VideoAdapter.ItemVideoRCClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), PlayVideoActivity.class);
                intent.putExtra("video", adapterVideo.getItem(position));
                startActivity(intent);
            }
        });
    }

    private void setData(ArrayList<Topic> list2, ArrayList<News> list1){
        arrayLists = new ArrayList<>();
        for(int i=0; i<9; i++){
            ArrayList<News> list = new ArrayList<News>();
            list.add(new News("Vietnamnet", 6, "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 200
                    , 100, R.drawable.sj_confidence,"abc", "text",  list2.get(i).getName(),
                    "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
            arrayLists.add(list);
        }
        for(int i=0; i<list1.size(); i++){
            String s = list1.get(i).getTopic();
            switch (s){
                case "Đời sống":
                    arrayLists.get(0).add(list1.get(i));
                    break;
                case "Kinh tế":
                    arrayLists.get(1).add(list1.get(i));
                    break;
                case "Sức khỏe":
                    arrayLists.get(2).add(list1.get(i));
                    break;
                case "Xã hội":
                    arrayLists.get(3).add(list1.get(i));
                    break;
                case "Khoa học":
                    arrayLists.get(4).add(list1.get(i));
                    break;
                case "Giải trí":
                    arrayLists.get(5).add(list1.get(i));
                    break;
                case "Công nghệ":
                    arrayLists.get(6).add(list1.get(i));
                    break;
                case "Thể thao":
                    arrayLists.get(7).add(list1.get(i));
                    break;
                case "Tâm sự":
                    arrayLists.get(8).add(list1.get(i));
                    break;
            }
        }

    }
    private ArrayList<News> setListNews() {
        ArrayList<News> listNews = new ArrayList<>();
        listNews.add(new News("Vietnamnet", 6, "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 200
                , 100, R.drawable.sj_confidence,"abc", "text",  "Thể thao",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
        listNews.add(new News("Vietnamnet", 6, "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 200
                , 100, R.drawable.sj_science,"abc", "text",  "Kinh tế",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
        listNews.add(new News("Vietnamnet", 6, "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 200
                , 100, R.drawable.sj_confidence,"abc", "text",  "Kinh tế",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
        listNews.add(new News("Vietnamnet", 6, "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 200
                , 100, R.drawable.sj_science,"abc", "text",  "Đời sống",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
        listNews.add(new News("Vietnamnet", 6, "Suc khoe cua chung ta", 200
                , 100, R.drawable.sj_confidence,"abc", "text",  "Sức khỏe",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
        listNews.add(new News("Vietnamnet", 6, "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 200
                , 100, R.drawable.sj_science,"abc", "text",  "Công nghệ",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
        listNews.add(new News("Vietnamnet", 6, "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 200
                , 100, R.drawable.sj_confidence,"abc", "text",  "Giải trí",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
        listNews.add(new News("Vietnamnet", 6, "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 200
                , 100, R.drawable.sj_science,"abc", "text",  "Tâm sự",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
        return listNews;
    }

    private ArrayList<Topic> setListTopic() {
        ArrayList<Topic> list = new ArrayList<>();

        list.add(new Topic("Đời sống", R.drawable.sj_life));
        list.add(new Topic("Kinh tế", R.drawable.sj_business));
        list.add(new Topic("Sức khỏe", R.drawable.sj_health));
        list.add(new Topic("Xã hội", R.drawable.sj_all));
        list.add(new Topic("Khoa học", R.drawable.sj_science));
        list.add(new Topic("Giải trí", R.drawable.sj_entertainment));
        list.add(new Topic("Công nghệ", R.drawable.sj_technology));
        list.add(new Topic("Thể thao", R.drawable.sj_sport));
        list.add(new Topic("Tâm sự", R.drawable.sj_confidence));

        return list;
    }
}