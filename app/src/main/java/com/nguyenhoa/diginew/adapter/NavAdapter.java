package com.nguyenhoa.diginew.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.nguyenhoa.diginew.categories.CategoriesFragment;
import com.nguyenhoa.diginew.discover.DiscoverFragment;
import com.nguyenhoa.diginew.home.HomeFragment;
import com.nguyenhoa.diginew.video.VideoFragment;

public class NavAdapter extends FragmentStatePagerAdapter {
    private int numPage = 4;
    public NavAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new HomeFragment();
            case 1: return new VideoFragment();
            case 2: return new DiscoverFragment();
            case 3: return new CategoriesFragment();
            default: return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return numPage;
    }


}
