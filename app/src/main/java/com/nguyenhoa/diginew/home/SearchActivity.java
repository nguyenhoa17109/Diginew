package com.nguyenhoa.diginew.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.adapter.NewsRCAdapter;
import com.nguyenhoa.diginew.common.MyClass;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.News;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class SearchActivity extends AppCompatActivity implements NewsRCAdapter.ItemNewsRCClickListener{
    private TextView tvSearch1, tvSearch2, tvSearch3, tvSearch21, tvSearch22, tvSearch23,
            tvSearch31, tvSearch32, tvSearch33, tvSearch41, tvSearch42, tvSearch43, tvSearch111
            , tvSearch112, tvSearch113, tvSearch121, tvSearch122, tvSearch123,
            tvSearch131, tvSearch132, tvSearch133, tvSearch141, tvSearch142, tvSearch143;
    private ImageView ivBack;
    private Spinner spYear;
    private SearchView etSearch;
    private LinearLayout layout1, layout2;
    private RecyclerView recyclerView;
    private NewsRCAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().hide();
        init();
        setClick();

    }

    private void setClick() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = getIntent();
                finish();
            }
        });

        etSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                setSearch(s);
                spYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        try{
                            String s1 = spYear.getSelectedItem().toString();
                            if(s1.equals("Năm nay"))    s1 = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
                            setListNews(s, s1);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

//        String s = etSearch.getQuery().toString();


        setClickTV(tvSearch1);
        setClickTV(tvSearch2);
        setClickTV(tvSearch3);
        setClickTV(tvSearch21);
        setClickTV(tvSearch22);
        setClickTV(tvSearch23);
        setClickTV(tvSearch31);
        setClickTV(tvSearch32);
        setClickTV(tvSearch33);
        setClickTV(tvSearch41);
        setClickTV(tvSearch42);
        setClickTV(tvSearch43);

        setClickTV(tvSearch111);
        setClickTV(tvSearch112);
        setClickTV(tvSearch113);
        setClickTV(tvSearch121);
        setClickTV(tvSearch122);
        setClickTV(tvSearch123);
        setClickTV(tvSearch131);
        setClickTV(tvSearch132);
        setClickTV(tvSearch133);
        setClickTV(tvSearch141);
        setClickTV(tvSearch142);
        setClickTV(tvSearch143);
    }

    private void setClickTV(TextView tvSearch1) {
        tvSearch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = tvSearch1.getText().toString();
                etSearch.setQuery(s, true);
//                setSearch(s);
            }
        });

    }

    private void setSearch(String s) {
        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.VISIBLE);
        String s2 = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        setListNews(s, s2);
    }

    private void init(){
        tvSearch1 = findViewById(R.id.tvSearch1);
        tvSearch2 = findViewById(R.id.tvSearch2);
        tvSearch3 = findViewById(R.id.tvSearch3);
        tvSearch21 = findViewById(R.id.tvSearch21);
        tvSearch22 = findViewById(R.id.tvSearch22);
        tvSearch23 = findViewById(R.id.tvSearch23);
        tvSearch31 = findViewById(R.id.tvSearch31);
        tvSearch32 = findViewById(R.id.tvSearch32);
        tvSearch33 = findViewById(R.id.tvSearch33);
        tvSearch41 = findViewById(R.id.tvSearch41);
        tvSearch42 = findViewById(R.id.tvSearch42);
        tvSearch43 = findViewById(R.id.tvSearch43);

        tvSearch111 = findViewById(R.id.tvSearch111);
        tvSearch112 = findViewById(R.id.tvSearch112);
        tvSearch113 = findViewById(R.id.tvSearch113);
        tvSearch121 = findViewById(R.id.tvSearch121);
        tvSearch122 = findViewById(R.id.tvSearch122);
        tvSearch123 = findViewById(R.id.tvSearch123);
        tvSearch131 = findViewById(R.id.tvSearch131);
        tvSearch132 = findViewById(R.id.tvSearch132);
        tvSearch133 = findViewById(R.id.tvSearch133);
        tvSearch141 = findViewById(R.id.tvSearch141);
        tvSearch142 = findViewById(R.id.tvSearch142);
        tvSearch143 = findViewById(R.id.tvSearch143);


        ivBack = findViewById(R.id.ivBack);
        spYear = findViewById(R.id.spYear);
        etSearch = findViewById(R.id.etSearch);
        recyclerView = findViewById(R.id.rvNews);

        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);

        layout1.setVisibility(View.VISIBLE);
        layout2.setVisibility(View.GONE);

        //set spinner time
        ArrayAdapter adapter1 = new ArrayAdapter(this, R.layout.item_spinner
                , MyList.spinnerTime);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spYear.setAdapter(adapter1);

    }

    private void setListNews(String s, String s1) {
        adapter = new NewsRCAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter.setData(setList(s, s1));
        adapter.setClickNewsListener(this::onItemClick);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<News> setList(String s, String s2) {
        ArrayList<News> list = new ArrayList<>();
        for(News news:MyList.listNews){
            String s1 = news.toString();
            if(s1.contains(s) && s1.contains(s2)){
                list.add(news);
            }
        }
        Log.d("PPP", s+"  "+s2);
        return list;
    }

    @Override
    public void onItemClick(View view, int position) {
        MyClass.setIntent(adapter.getItem(position), (Activity) view.getContext());
//        Intent intent = new Intent(this, NewsActivity.class);
//        intent.putExtra("News", adapter.getItem(position));
//        startActivity(intent);
    }
}