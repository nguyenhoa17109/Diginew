package com.nguyenhoa.diginew.discover;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.model.MyList;
import com.nguyenhoa.diginew.model.Topic;

import java.util.ArrayList;

public class DiscoverFragment extends Fragment {

    private RecyclerView rvDiscover;
    private DiscoverAdapter discoverAdapter;
    private ArrayList<Topic> list;
    public DiscoverFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        rvDiscover = view.findViewById(R.id.rvListDiscover);

        list = MyList.list_dis;
        discoverAdapter = new DiscoverAdapter(getContext(), list);

        rvDiscover.setLayoutManager(new LinearLayoutManager(getContext()));
        rvDiscover.setAdapter(discoverAdapter);

        discoverAdapter.notifyDataSetChanged();

        return view;
    }
}