package com.nguyenhoa.diginew.news;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.adapter.CmtAdapter;
import com.nguyenhoa.diginew.common.MyClass;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.Comment;
import com.nguyenhoa.diginew.model.News;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoNewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoNewsFragment extends Fragment {
    private News news;
    private TextView tvTime, tvTopic, tvLikes, tvCmts;
    private ImageView ivAccount, ivShare, ivBack, ivInfo;
    private EditText etCmt;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InfoNewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoNewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoNewsFragment newInstance(String param1, String param2) {
        InfoNewsFragment fragment = new InfoNewsFragment();
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
        View v = inflater.inflate(R.layout.fragment_info_news, container, false);
        init(v);
        Bundle bundle = getArguments();
        news = (News) bundle.getSerializable("info");
        ivInfo.setImageResource(news.getImgs());
        setClick();
        return v;
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
                MyClass.setTVLike(tvLikes, getContext());
            }
        });
    }

    private void setLayoutCmt(View v){
        View view1 = getLayoutInflater().inflate(R.layout.layout_cmt, null);

        ArrayList<Comment> list = MyList.list_Cmt;
        RecyclerView rv;
        CmtAdapter adapter;
        ImageView ivClose;
        TextView tvSend, tvLike, tvCmt;
        EditText etCmt;

        etCmt = view1.findViewById(R.id.etCmt);
        tvSend = view1.findViewById(R.id.tvSend);
        rv = view1.findViewById(R.id.rvCmt);
        ivClose = view1.findViewById(R.id.ivClose);
        tvLike = view1.findViewById(R.id.tvLike);

        tvLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyClass.setTVLike(tvLike, getActivity());
            }
        });

        adapter = new CmtAdapter(list, v.getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),
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
            }
        });
    }

    private void init(View v) {
        tvTime = v.findViewById(R.id.tvNewsTime);
        tvCmts = v.findViewById(R.id.tvCmt);
        tvLikes = v.findViewById(R.id.tvLike);

        etCmt = v.findViewById(R.id.etCmt);

        ivAccount = v.findViewById(R.id.ivAccount);
        ivShare = v.findViewById(R.id.ivShare);
        ivInfo = v.findViewById(R.id.tvNewsInfo);
    }
}