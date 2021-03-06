package com.nguyenhoa.diginew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nguyenhoa.diginew.adapter.CategoryFvAdapter;
import com.nguyenhoa.diginew.adapter.CategoryRvChangeAdapter;
import com.nguyenhoa.diginew.common.CustomItemAnimator;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.Topic;

import java.util.ArrayList;

public class CategoryFvActivity extends AppCompatActivity implements CategoryRvChangeAdapter.CategoryChangeListener {
    private ImageView ivBack;
    private TextView tvChange;
    private Button btAdd;
    private RecyclerView rvCategory, rvChange;
    private CategoryFvAdapter adapter;
    private CategoryRvChangeAdapter adapter1;
    private ArrayList<Topic> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_fv);

        getSupportActionBar().hide();
        init();
        setRV1();
        setRV2();
        rvCategory.setVisibility(View.VISIBLE);
        rvChange.setVisibility(View.GONE);
        setClick();
    }

    private void setClick() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryFvActivity.this, AddCategoryFvActivity.class);
                startActivity(intent);
            }
        });

        tvChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = tvChange.getText().toString();
                if(s.equals("Thay ?????i")){
                    tvChange.setText(R.string.done);
                    rvCategory.setVisibility(View.GONE);
                    rvChange.setVisibility(View.VISIBLE);
                }else{
                    tvChange.setText(R.string.change);
                    rvCategory.setVisibility(View.VISIBLE);
                    rvChange.setVisibility(View.GONE);
                }
            }
        });
    }

    private void setRV2() {
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        rvChange.setLayoutManager(manager);
        rvChange.setAdapter(adapter1);
        rvChange.setItemAnimator(new CustomItemAnimator());
    }

    private void setRV1() {
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        rvCategory.setLayoutManager(manager);
        rvCategory.setAdapter(adapter);
        rvCategory.setItemAnimator(new CustomItemAnimator());
    }

    private void init(){
        ivBack = findViewById(R.id.ivBack);
        tvChange = findViewById(R.id.tvChange);

        btAdd = findViewById(R.id.btAdd);
        rvCategory = findViewById(R.id.rvCategory);
        rvChange = findViewById(R.id.rvCategory1);

        list = MyList.setListChoseSubjectFV(getApplicationContext());

        adapter = new CategoryFvAdapter(this, list);
        adapter1 = new CategoryRvChangeAdapter(this, list, this::onCategoryChangeItem);
    }

    @Override
    public void onCategoryChangeItem(int position) {
        adapter1.setData(list);
        adapter.setData(list);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("listSubjectFV", json);
        editor.commit();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        list = MyList.setListChoseSubjectFV(getApplicationContext());
        adapter.setData(list);
        adapter1.setData(list);

    }
}