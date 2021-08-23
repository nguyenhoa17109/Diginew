package com.nguyenhoa.diginew.news;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.adapter.NewsRCAdapter;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.common.NewsCallBack;
import com.nguyenhoa.diginew.model.News;
import com.nguyenhoa.diginew.model.Tag;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TagFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TagFragment extends Fragment implements NewsCallBack {
    private RecyclerView rvTag;
    private NewsRCAdapter adapter;
    private News1Activity activity;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TagFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TagFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TagFragment newInstance(String param1, String param2) {
        TagFragment fragment = new TagFragment();
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
        View v = inflater.inflate(R.layout.fragment_tag, container, false);

//        activity = (News1Activity) getActivity();
//        activity.isNews = false;
//        activity.ivBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getParentFragmentManager().popBackStack();
//            }
//        });

        Bundle bundle = getArguments();
        Tag tag = (Tag) bundle.getSerializable("tag");
        rvTag = v.findViewById(R.id.rvTag);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),
                RecyclerView.VERTICAL, false);
        rvTag.setLayoutManager(manager);

        ArrayList<News> list = MyList.sqLite.getAllNewsByTag(tag.getId());
//        list.add(MyList.listsText.get(0).get(0));
//        for(int i=0; i<MyList.listsText.size(); i++){
//            for(int j = 0; j< MyList.listsText.get(i).size(); j++){
//                News news = MyList.listsText.get(i).get(j);
//                if(checkTag(news, new Tag(tag)) && news != null){
//                    list.add(news);
//                }
//            }
//        }

        adapter.setData(list, this::onNewsItemClick);
//        adapter.setClickNewsListener(this::onItemClick);
        rvTag.setAdapter(adapter);

        return v;
    }

    private boolean checkTag(News news, Tag tag) {
        int k = 0;
        if(news.getListTag() != null) k = news.getListTag().size();
        for(int i=0; i<k; i++){
            String s = news.getListTag().get(i).getTag();
            if(s.equals(tag.getTag()))
                return true;
        }
        return false;
    }

    @Override
    public void onNewsItemClick(int pos, TextView ivTopic, TextView ivSource, TextView tvTime) {

    }
}