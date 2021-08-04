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

import com.facebook.login.widget.ToolTipPopup;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nguyenhoa.diginew.adapter.CategoryFvAdapter;
import com.nguyenhoa.diginew.adapter.CategoryRvChangeAdapter;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.Topic;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CategoryFvActivity extends AppCompatActivity {
    private ImageView ivBack;
    private TextView tvChange;
    private Button btAdd;
    private RecyclerView rvCategory, rvChange;
    private CategoryFvAdapter adapter;
    private CategoryRvChangeAdapter adapter1;

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
                Log.d("OO", "OK");
                String s = tvChange.getText().toString();
                if(s.equals("Thay đổi")){
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
    }

    private void setRV1() {
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        rvCategory.setLayoutManager(manager);
        rvCategory.setAdapter(adapter);
    }

    private void init(){
        ivBack = findViewById(R.id.ivBack);
        tvChange = findViewById(R.id.tvChange);

        btAdd = findViewById(R.id.btAdd);
        rvCategory = findViewById(R.id.rvCategory);
        rvChange = findViewById(R.id.rvCategory1);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = sharedPreferences.getString("listSubjectFV", "");
        MyList.list_Fv = gson.fromJson(json, new TypeToken<ArrayList<Topic>>(){}.getType());

        adapter = new CategoryFvAdapter(this, MyList.list_Fv);
        adapter1 = new CategoryRvChangeAdapter(this, MyList.list_Fv);
    }
}