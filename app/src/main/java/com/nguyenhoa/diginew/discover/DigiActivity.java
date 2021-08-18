package com.nguyenhoa.diginew.discover;

import androidx.appcompat.app.AppCompatActivity;

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

import java.io.InputStream;

public class DigiActivity extends AppCompatActivity {
    private ImageView ivBack, iv;
    private Button btOpen;
    private TextView tv, tvTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digi);

        getSupportActionBar().hide();
        Intent intent = getIntent();
        int index = intent.getIntExtra("index", 0);

        ivBack = findViewById(R.id.ivBack);
        iv = findViewById(R.id.ivDigiMovie);
        btOpen = findViewById(R.id.btOpen);
        tv = findViewById(R.id.tvDigiMovie);
        tvTopic = findViewById(R.id.tvTopic);


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setTopic(index);

    }

    private void setTopic(int index) {
        OtherApp app = MyList.list_dis.get(index);

        tvTopic.setText(app.getName());
        iv.setImageResource(app.getImg());
        tv.setMovementMethod(new ScrollingMovementMethod());
        try {
            Resources res = getResources();
            InputStream in_s = res.openRawResource(app.getContent());
            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            tv.setText(new String(b));
        } catch (Exception e) {
            tv.setText("Error: can't show content.");
        }

        btOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(app.getLink()));
                startActivity(intent);
            }
        });
    }
}