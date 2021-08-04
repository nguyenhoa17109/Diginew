package com.nguyenhoa.diginew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.SearchView;

import com.nguyenhoa.diginew.adapter.AddCategoryAdapter;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.Topic;

import java.util.ArrayList;

public class AddCategoryFvActivity extends AppCompatActivity implements AddCategoryAdapter.OnClickItem {
    private ImageView ivBack;
    private SearchView searchView;
    private RecyclerView rvCategory;
    private AddCategoryAdapter adapter;
    private ArrayList<Topic> list;
    private ArrayList<Topic> listAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category_fv);

        getSupportActionBar().hide();
        init();
        setClick();
    }

    private void setClick() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                ArrayList<Topic> list1 = new ArrayList<>();
                for(Topic topic:list){
                    if(topic.getName().contains(s)){
                        list1.add(topic);
                    }
                }
                adapter.setData(list1);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    private void init(){
        ivBack = findViewById(R.id.ivBack);
        searchView = findViewById(R.id.etSearch);
        rvCategory = findViewById(R.id.rvAddCategory);

        listAdd = new ArrayList<>();
        adapter = new AddCategoryAdapter(this);
        list = MyList.setListUnFavor(MyList.setListChoseSubjectFV(getApplicationContext()));
        adapter.setData(list);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),
                RecyclerView.VERTICAL, false);
        rvCategory.setLayoutManager(manager);
        rvCategory.setAdapter(adapter);
    }

    @Override
    public void ClickItem(View v, int index) {
        listAdd.add(adapter.getItem(index));
    }
}