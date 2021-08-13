package com.nguyenhoa.diginew.news;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.adapter.NewsDownloadedAdapter;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.Operation;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DownloadedNewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DownloadedNewsFragment extends Fragment {
    private RecyclerView rvDownload;
    private LinearLayout layout;
    private NewsDownloadedAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DownloadedNewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DownloadedNewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DownloadedNewsFragment newInstance(String param1, String param2) {
        DownloadedNewsFragment fragment = new DownloadedNewsFragment();
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
        View v = inflater.inflate(R.layout.fragment_downloaded_news, container, false);
        rvDownload = v.findViewById(R.id.rvNewsDownload);
        layout = v.findViewById(R.id.layoutDownload);

        ArrayList<Operation> lst ;
        Bundle bundle = getArguments();

        Operation operation = (Operation) bundle.getSerializable("download");
        if(operation == null) {
            operation = (Operation) bundle.getSerializable("saved");
            lst = MyList.listSave;
        } else
            lst = MyList.listDownload;

        setListDownload(operation, lst);

        return v;
    }
    private void setListDownload(Operation operation, ArrayList<Operation> lst){

        if(operation != null)
            lst.add(operation);
        if(lst.isEmpty()){
            layout.setVisibility(View.VISIBLE);
            rvDownload.setVisibility(View.GONE);
        }else{
            layout.setVisibility(View.GONE);
            rvDownload.setVisibility(View.VISIBLE);
        }

        ArrayList<ArrayList<Operation>> lists = MyList.setListOp(lst);
        adapter = new NewsDownloadedAdapter(lists, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL, false);
        rvDownload.setLayoutManager(manager);
        rvDownload.setAdapter(adapter);
    }
}