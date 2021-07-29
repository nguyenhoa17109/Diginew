package com.nguyenhoa.diginew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import com.nguyenhoa.diginew.common.MyClass;

import java.util.ArrayList;

public class PacketAdvanceActivity extends AppCompatActivity {
    private TextView tvDataDetail, tvInDetail, tvOutDetail, tvSMSDetail, tvMyTVDetail, tvClipsDetail
            , tvHotDetail, tvMusicDetail, tvMovieDetail;
    private TableRow row1, row2, row3, row4, row5, row6, row7, row8, row9;
    private Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packet_advance);

        getSupportActionBar().hide();
        initPacket();

        setClick();
    }

    public void initPacket(){
        tvDataDetail = findViewById(R.id.tvDataDetail);
        tvInDetail = findViewById(R.id.tvInDetail);
        tvOutDetail = findViewById(R.id.tvOutDetail);
        tvSMSDetail = findViewById(R.id.tvSMSDetail);
        tvMyTVDetail = findViewById(R.id.tvMyTVDetail);
        tvClipsDetail = findViewById(R.id.tvClipsDetail);
        tvHotDetail = findViewById(R.id.tvHotDetail);
        tvMusicDetail = findViewById(R.id.tvMusicDetail);
        tvMovieDetail = findViewById(R.id.tvMovieDetail);

        row1 = findViewById(R.id.row1);
        row2 = findViewById(R.id.row2);
        row3 = findViewById(R.id.row3);
        row4 = findViewById(R.id.row4);
        row5 = findViewById(R.id.row5);
        row6 = findViewById(R.id.row6);
        row7 = findViewById(R.id.row7);
        row8 = findViewById(R.id.row8);
        row9 = findViewById(R.id.row9);

        btRegister = findViewById(R.id.btRegister);
    }

    public void setClick() {
        ArrayList<TextView> list = new ArrayList<>();
        list.add(tvDataDetail);
        list.add(tvInDetail);
        list.add(tvOutDetail);
        list.add(tvSMSDetail);
        list.add(tvMyTVDetail);
        list.add(tvClipsDetail);
        list.add(tvHotDetail);
        list.add(tvMusicDetail);
        list.add(tvMovieDetail);

        row1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyClass.setRowClick(tvDataDetail, list);
            }
        });
        row2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyClass.setRowClick(tvInDetail, list);
            }
        });
        row3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyClass.setRowClick(tvOutDetail, list);
            }
        });
        row4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyClass.setRowClick(tvSMSDetail, list);
            }
        });
        row5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyClass.setRowClick(tvMyTVDetail, list);
            }
        });
        row6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyClass.setRowClick(tvClipsDetail, list);
            }
        });
        row7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyClass.setRowClick(tvHotDetail, list);
            }
        });
        row8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyClass.setRowClick(tvMusicDetail, list);
            }
        });
        row9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyClass.setRowClick(tvMovieDetail, list);
            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}