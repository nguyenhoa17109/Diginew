package com.nguyenhoa.diginew.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.nguyenhoa.diginew.MainActivity;
import com.nguyenhoa.diginew.R;

public class SubjectsFavorite extends AppCompatActivity {
    Button btToHome;
    GridView gridView;
    SubjectsAdapter adapter;
    String[] numberSubjects = {"Đời sống", "Kinh tế", "Sức khỏe", "Xã hội", "Khoa hoc", "Giải trí", "Công nghệ", "Thể thao", "Tâm sự"};
    int[] numberImgs = {R.drawable.sj_life, R.drawable.sj_business, R.drawable.sj_health,
            R.drawable.sj_all, R.drawable.sj_science, R.drawable.sj_entertainment,
            R.drawable.sj_technology, R.drawable.sj_sport, R.drawable.sj_confidence};

    public SubjectsFavorite(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects_favorite);

        btToHome = findViewById(R.id.btToHome);
        gridView = findViewById(R.id.gvSubjects);

        adapter = new SubjectsAdapter(SubjectsFavorite.this, numberSubjects, numberImgs);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        btToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SubjectsFavorite.this, MainActivity.class));
            }
        });
    }
}