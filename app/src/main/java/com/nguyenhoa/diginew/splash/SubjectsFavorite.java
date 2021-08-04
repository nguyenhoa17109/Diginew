package com.nguyenhoa.diginew.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.nguyenhoa.diginew.MainActivity;
import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.Topic;

import java.util.ArrayList;

public class SubjectsFavorite extends AppCompatActivity implements SubjectsAdapter.SubjectFVClickInterfacec{
    SharedPreferences preferences;
    boolean isFirst;
    public static final String PREFER_NAME = "SubjectsFavorite";
    Button btToHome;
    RecyclerView recyclerView;
    SubjectsAdapter adapter;
    ArrayList<Topic> list;
    ArrayList<Topic> listChose = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences(PREFER_NAME, MODE_PRIVATE);
        isFirst = preferences.getBoolean(PREFER_NAME, false);

        if(isFirst){
            startActivity(new Intent(SubjectsFavorite.this, MainActivity.class));
            finish();
        }
        else {
            setContentView(R.layout.activity_subjects_favorite);

            getSupportActionBar().hide();
            btToHome = findViewById(R.id.btToHome);
            recyclerView = findViewById(R.id.rvSubjectsFV);

            list = MyList.list;

            GridLayoutManager manager = new GridLayoutManager(this, 2);
            adapter = new SubjectsAdapter(SubjectsFavorite.this, list, this::onClickSubject);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);

            btToHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(listChose);
                    editor.putString("listSubjectFV", json);
                    editor.commit();

                    startActivity(new Intent(SubjectsFavorite.this, MainActivity.class));
                }
            });

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(PREFER_NAME, true);
            editor.commit();
        }


    }

    @Override
    public void onClickSubject(int position) {
        Topic topic = list.get(position);
        String subject = topic.getName();
        if(topic.getSelected()){
            listChose.add(topic);
        }
        else{
            listChose.remove(topic);
        }
    }
}