package com.nguyenhoa.diginew.discover;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.OtherApp;

import java.util.ArrayList;

public class DiscoverFragment extends Fragment implements DiscoverAdapter.OnItemClick {

    private RecyclerView rvDiscover;
    private DiscoverAdapter discoverAdapter;
    private ArrayList<OtherApp> list;

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
        discoverAdapter.setOnItemClick(this::ItemClick);
        rvDiscover.setLayoutManager(new LinearLayoutManager(getContext()));
        rvDiscover.setAdapter(discoverAdapter);
        discoverAdapter.notifyDataSetChanged();

        return view;
    }

    @Override
    public void ItemClick(int index) {
//        Topic topic = discoverAdapter.getItem(index);
//        Log.d("LLL", "OK");
        Intent intent = new Intent(getActivity(), DigiActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }
}