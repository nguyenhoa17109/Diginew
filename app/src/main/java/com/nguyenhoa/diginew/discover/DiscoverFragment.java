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

import java.util.ArrayList;
import java.util.List;

public class DiscoverFragment extends Fragment{

    private RecyclerView rvDiscover;
    private DiscoverAdapter discoverAdapter;
    private String[] names = {"DigiMovie", "DigiClip", "DigiMusic", "DigiHealth", "MyTV"};
    private int[] images = {R.drawable.img_movie, R.drawable.img_clip, R.drawable.img_music, R.drawable.img_health, R.drawable.img_mytv};
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

        discoverAdapter = new DiscoverAdapter(getContext(), names, images);

        rvDiscover.setLayoutManager(new LinearLayoutManager(getContext()));
        rvDiscover.setAdapter(discoverAdapter);

        discoverAdapter.notifyDataSetChanged();

        return view;
    }

}