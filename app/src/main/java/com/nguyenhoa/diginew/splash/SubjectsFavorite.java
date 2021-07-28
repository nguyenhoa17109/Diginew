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
        list = MyList.list;

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