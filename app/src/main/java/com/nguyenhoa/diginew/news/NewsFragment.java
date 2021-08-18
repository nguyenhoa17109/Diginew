package com.nguyenhoa.diginew.news;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.adapter.CmtAdapter;
import com.nguyenhoa.diginew.common.MyClass;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.Comment;
import com.nguyenhoa.diginew.model.News;
import com.nguyenhoa.diginew.model.Tag;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {
    private TextView tvSearch1, tvSearch2, tvSearch3, tvSearch21, tvSearch22, tvSearch23,
            tvSearch31, tvSearch32, tvSearch33, tvSearch41, tvSearch42, tvSearch43;
    private TextView tvTitleNews, tvSource, tvTime, tvLikes, tvCmts, tvContent;
    private ImageView ivAccount, ivShare;
    private EditText etCmt;
    private News1Activity activity;
    private News news;
    private SharedPreferences preferences;
    public static final String MyPREFERENCES = "CONTENT";
    private RelativeLayout layout;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
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
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        init(v);
        activity = (News1Activity) getActivity();

        Bundle bundle = getArguments();
        news = (News) bundle.getSerializable("textnews");

        if(news != null){
            setNews(news);
        }else{
            Toast.makeText(getActivity(), "News is empty!!!", Toast.LENGTH_SHORT).show();
        }
        return v;
    }

    private void setNews(News news) {
        activity.isNews = true;
        activity.invalidateOptionsMenu();
//        layoutTag.setVisibility(View.GONE);
        layout.setVisibility(View.VISIBLE);
//        View view = getSupportActionBar().getCustomView();

        tvTitleNews.setText(news.getTitle());
        tvContent.setText(news.getContent());
        tvSource.setText(news.getSource());
//            tvTime.setText(news.getTimes()+" "+getResources().getString(R.string.time));
        tvTime.setText(news.getTimes());
        tvLikes.setText(String.valueOf(news.getLikes()));
        tvCmts.setText(String.valueOf(news.getCmts()));

        int size = setSize(preferences.getInt("size_level", 0));
        int id = preferences.getInt("font", R.font.roboto);
        Typeface typeface = ResourcesCompat.getFont(getActivity(), id);
        tvContent.setTypeface(typeface);
        tvContent.setTextSize(size);
        setClick();
        setTag(news);

    }

    private void setTag(News news) {
        ArrayList<Tag> list = news.getListTag();

        int k = list.size();
        for (int i = 0; i < 12; i++) {
            switch (i) {
                case 0:
                    setEach(list, tvSearch1, i);
                    break;
                case 1:
                    setEach(list, tvSearch2, i);
                    break;
                case 2:
                    setEach(list, tvSearch3, i);
                    break;
                case 3:
                    setEach(list, tvSearch21, i);
                    break;
                case 4:
                    setEach(list, tvSearch22, i);
                    break;
                case 5:
                    setEach(list, tvSearch23, i);
                    break;
                case 6:
                    setEach(list, tvSearch31, i);
                    break;
                case 7:
                    setEach(list, tvSearch32, i);
                    break;
                case 8:
                    setEach(list, tvSearch33, i);
                    break;
                case 9:
                    setEach(list, tvSearch41, i);
                    break;
                case 10:
                    setEach(list, tvSearch42, i);
                    break;
                case 11:
                    setEach(list, tvSearch43, i);
                    break;

            }
        }
    }

    private void setEach(ArrayList<Tag> list, TextView tvSearch1, int i) {
        if(i < list.size()){
            Tag tag = list.get(i);
            tvSearch1.setVisibility(View.VISIBLE);
            tvSearch1.setText(tag.getTag());
            tvSearch1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setListTag(tag);
                }
            });
        }else
            tvSearch1.setVisibility(View.GONE);
    }

    private void setClick() {
        tvCmts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLayoutCmt(view);
            }
        });
        etCmt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                setLayoutCmt(view);
            }
        });

        tvLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyClass.setTVLike(tvLikes, getActivity());
                boolean isLike = MyClass.isLiked(tvLikes, getContext());
                //update to DB
                News news1 = news;
                news1.setLike(isLike);
                news1.setLikes(Integer.parseInt(tvLikes.getText().toString()));
                MyList.sqLite.updateNews(news1);
            }
        });

//        setClickTV(tvSearch1, 0);
//        setClickTV(tvSearch2, 1);
//        setClickTV(tvSearch3, 2);
//        setClickTV(tvSearch21, 3);
//        setClickTV(tvSearch22, 4);
//        setClickTV(tvSearch23, 5);
//        setClickTV(tvSearch31, 6);
//        setClickTV(tvSearch32, 7);
//        setClickTV(tvSearch33, 8);
//        setClickTV(tvSearch41, 9);
//        setClickTV(tvSearch42, 10);
//        setClickTV(tvSearch43, 11);

    }

//    private void setClickTV(TextView tvSearch1, int i) {
//        tvSearch1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String s = tvSearch1.getText().toString();
//                setListTag(s, i);
//            }
//        });
//
//    }

    private void setLayoutCmt(View v){
        View view1 = getLayoutInflater().inflate(R.layout.layout_cmt, null);

        ArrayList<Comment> list = MyList.list_Cmt;
        RecyclerView rv;
        CmtAdapter adapter;
        ImageView ivClose;
        TextView tvSend, tvLike;
        EditText etCmt;

        etCmt = view1.findViewById(R.id.etCmt);
        tvSend = view1.findViewById(R.id.tvSend);
        rv = view1.findViewById(R.id.rvCmt);
        ivClose = view1.findViewById(R.id.ivClose);
        tvLike = view1.findViewById(R.id.tvLike);

        tvLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(!isLiked(tvLikes))
                MyClass.setTVLike(tvLike, getActivity());
            }
        });
        adapter = new CmtAdapter(list, v.getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),
                RecyclerView.VERTICAL, false);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);

        Dialog dialog = new Dialog(v.getContext(), R.style.MaterialDialogSheet);
        dialog.setContentView(view1);
        dialog.setCancelable(true);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.show();

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0; i<list.size(); i++){
                    list.get(i).setPosition(i);
                    MyList.sqLite.updateComment(list.get(i));
                }
                dialog.dismiss();
            }
        });

        etCmt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                adapter.sendNewCmt(v.getContext(), list.size(), false);
            }
        });
        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = etCmt.getText().toString();
                etCmt.setText("");
                adapter.displayNewCmt(s, list.size(), false);
                //update position Cmt in list
                MyClass.updateListCmt(list);
            }
        });
    }

    private int setSize(int progress) {
        switch (progress){
            case 1:
                return 24;
            case 2:
                return 30;
            default:
                return 18;
        }
    }

    public void init(View v){
        preferences = requireActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        tvTitleNews = v.findViewById(R.id.tvTitleNews);
        tvSource = v.findViewById(R.id.tvNewsSource);
        tvTime = v.findViewById(R.id.tvNewsTime);
        tvCmts = v.findViewById(R.id.tvCmt);
        tvLikes = v.findViewById(R.id.tvLike);
        tvContent = v.findViewById(R.id.tvContent);

        etCmt = v.findViewById(R.id.etCmt);

        ivAccount = v.findViewById(R.id.ivAccount);
        ivShare = v.findViewById(R.id.ivShare);

        layout = v.findViewById(R.id.layout);

        tvSearch1 = v.findViewById(R.id.tvSearch1);
        tvSearch2 = v.findViewById(R.id.tvSearch2);
        tvSearch3 = v.findViewById(R.id.tvSearch3);
        tvSearch21 = v.findViewById(R.id.tvSearch21);
        tvSearch22 = v.findViewById(R.id.tvSearch22);
        tvSearch23 = v.findViewById(R.id.tvSearch23);
        tvSearch31 = v.findViewById(R.id.tvSearch31);
        tvSearch32 = v.findViewById(R.id.tvSearch32);
        tvSearch33 = v.findViewById(R.id.tvSearch33);
        tvSearch41 = v.findViewById(R.id.tvSearch41);
        tvSearch42 = v.findViewById(R.id.tvSearch42);
        tvSearch43 = v.findViewById(R.id.tvSearch43);
    }

    private void setListTag(Tag tag) {
        activity = (News1Activity) getActivity();
        if(activity != null) {
            activity.tvTopic.setText(MessageFormat.format("#{0}", tag.getTag()));
            activity.isNews = false;
            activity.invalidateOptionsMenu();
        }
        TagFragment fragment = new TagFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("tag", tag);
        fragment.setArguments(bundle);

        getParentFragmentManager().popBackStack();
        FragmentManager fmgr = getParentFragmentManager();
        FragmentTransaction ft = fmgr.beginTransaction();
        ft.addToBackStack(fragment.getClass().getSimpleName());
    }
}