package com.nguyenhoa.diginew.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhoa.diginew.MainActivity;
import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.model.Subject;

import java.util.ArrayList;

public class SubjectsFavorite extends AppCompatActivity {
    Button btToHome;
    RecyclerView recyclerView;
    SubjectsAdapter adapter;
    ArrayList<Subject> arrayList;

    public SubjectsFavorite(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects_favorite);

        btToHome = findViewById(R.id.btToHome);
        recyclerView = findViewById(R.id.rvSubjectsFV);

        arrayList = new ArrayList<>();
        arrayList.add(new Subject("Đời sống", R.drawable.sj_life));
        arrayList.add(new Subject("Kinh tế", R.drawable.sj_business));
        arrayList.add(new Subject("Sức khỏe", R.drawable.sj_health));
        arrayList.add(new Subject("Xã hội", R.drawable.sj_all));
        arrayList.add(new Subject("Khoa học", R.drawable.sj_science));
        arrayList.add(new Subject("Giải trí", R.drawable.sj_entertainment));
        arrayList.add(new Subject("Công nghệ", R.drawable.sj_technology));
        arrayList.add(new Subject("Thể thao", R.drawable.sj_sport));
        arrayList.add(new Subject("Tâm sự", R.drawable.sj_confidence));


        adapter = new SubjectsAdapter(SubjectsFavorite.this, arrayList);
        recyclerView.setAdapter(adapter);


        btToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SubjectsFavorite.this, MainActivity.class));
            }
        });
    }
}