package com.nguyenhoa.diginew.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhoa.diginew.MainActivity;
import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.model.MyList;
import com.nguyenhoa.diginew.model.Subject;
import com.nguyenhoa.diginew.model.Topic;

import java.util.ArrayList;

public class SubjectsFavorite extends AppCompatActivity {
    Button btToHome;
    RecyclerView recyclerView;
    SubjectsAdapter adapter;
    ArrayList<Topic> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects_favorite);

        getSupportActionBar().hide();
        btToHome = findViewById(R.id.btToHome);
        recyclerView = findViewById(R.id.rvSubjectsFV);

//        arrayList = MyList.list;
        list = new ArrayList<>();
        list.add(new Topic("Đời sống", R.drawable.sj_life));
        list.add(new Topic("Kinh tế", R.drawable.sj_business));
        list.add(new Topic("Sức khỏe", R.drawable.sj_health));
        list.add(new Topic("Xã hội", R.drawable.sj_all));
        list.add(new Topic("Khoa học", R.drawable.sj_science));
        list.add(new Topic("Giải trí", R.drawable.sj_entertainment));
        list.add(new Topic("Công nghệ", R.drawable.sj_technology));
        list.add(new Topic("Thể thao", R.drawable.sj_sport));
        list.add(new Topic("Tâm sự", R.drawable.sj_confidence));

        GridLayoutManager manager = new GridLayoutManager(this, 2);
        adapter = new SubjectsAdapter(SubjectsFavorite.this, list);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);


        btToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SubjectsFavorite.this, MainActivity.class));
            }
        });
    }
}