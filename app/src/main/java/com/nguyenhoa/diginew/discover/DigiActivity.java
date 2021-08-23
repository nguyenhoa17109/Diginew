package com.nguyenhoa.diginew.discover;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.common.MyClass;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.OtherApp;
import com.nguyenhoa.diginew.model.Topic;
import com.nguyenhoa.diginew.news.InfoNewsFragment;

import java.io.InputStream;

public class DigiActivity extends AppCompatActivity {
    private ImageView ivBack;
    private TextView tvTopic;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digi);

        getSupportActionBar().hide();
        Intent intent = getIntent();
        index = intent.getIntExtra("index", 0);

        ivBack = findViewById(R.id.ivBack);
        tvTopic = findViewById(R.id.tvTopic);

        addFragment(new DigiFragment());
        setTopic(index);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void addFragment(Fragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putInt("digi", index);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.frDigi, fragment)
                .addToBackStack(fragment.getClass().getSimpleName()).commit();
    }

    private void setTopic(int i) {
        OtherApp app = MyList.list_dis.get(i);

        tvTopic.setText(app.getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}