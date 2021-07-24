package com.nguyenhoa.diginew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigation;
    private ViewPager viewPager;
    private NavAdapter adapter;
    //hoa

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = findViewById(R.id.nav);
        viewPager = findViewById(R.id.viewpager);

        adapter = new NavAdapter(getSupportFragmentManager(),
                NavAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mHome: viewPager.setCurrentItem(0);
                        break;
                    case R.id.mVideo: viewPager.setCurrentItem(1);
                        break;
                    case R.id.mDiscover: viewPager.setCurrentItem(2);
                        break;
                    case R.id.mCategories: viewPager.setCurrentItem(3);
                }
                return true;
            }
        });
    }
}