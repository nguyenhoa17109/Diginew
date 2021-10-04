package com.nguyenhoa.diginew.digiPack;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.user.UserActivity;

public class DigiRegister extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivBack;
    private Button btConfirm, buttonOtp, btBack;
    private RadioButton radioButton, radioButton2, radioButton3;
    private TextView tvPrice;
    private String p1 = "129.000 VNĐ", p2 = "645.000 VNĐ", p3 = "1.290.000 VNĐ";
    private Dialog myDiaglog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_packet_register);
        init();
        setOnClick();
        getSupportActionBar().hide();
    }

    public void init() {
        //popup
        myDiaglog = new Dialog(this);
        myDiaglog.setCanceledOnTouchOutside(true);
        myDiaglog.setContentView(R.layout.layout_popup1);
        buttonOtp = myDiaglog.findViewById(R.id.buttonOtp);

        ivBack = findViewById(R.id.ivBack);
        btConfirm = findViewById(R.id.btConfirm);
        tvPrice = findViewById(R.id.tvPrice);
        radioButton = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
    }

    public void setOnClick() {
        buttonOtp.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        btConfirm.setOnClickListener(this);
        radioButton.setOnClickListener(this);
        radioButton2.setOnClickListener(this);
        radioButton3.setOnClickListener(this);
    }

    public void onClickRadioButton(int check) {
        if (check == 1) {
            tvPrice.setText(p1);
            radioButton2.setChecked(false);
            radioButton3.setChecked(false);

            myDiaglog.setContentView(R.layout.layout_popup1);
            buttonOtp = myDiaglog.findViewById(R.id.buttonOtp);
            buttonOtp.setOnClickListener(this);

        } else if (check == 2) {
            tvPrice.setText(p2);
            radioButton.setChecked(false);
            radioButton3.setChecked(false);

            myDiaglog.setContentView(R.layout.layout_packet_detail2);
            btBack = myDiaglog.findViewById(R.id.btBack);
            btBack.setOnClickListener(this);
        } else {
            tvPrice.setText(p3);
            radioButton.setChecked(false);
            radioButton2.setChecked(false);

            myDiaglog.setContentView(R.layout.layout_packet_detail2);
            btBack = myDiaglog.findViewById(R.id.btBack);
            btBack.setOnClickListener(this);
        }
    }

    public void onClickRegister() {
        myDiaglog.show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == ivBack.getId()) {
            finish();
        } else if (v.getId() == btConfirm.getId()) {
            onClickRegister();
        } else if (v.getId() == radioButton.getId()) {
            onClickRadioButton(1);
        } else if (v.getId() == radioButton2.getId()) {
            onClickRadioButton(2);
        } else if (v.getId() == radioButton3.getId()) {
            onClickRadioButton(3);
        } else if (v.getId() == buttonOtp.getId()) {
            Intent in = new Intent(this, UserActivity.class);
            startActivity(in);
        } else if (v.getId() == btBack.getId()) {
            myDiaglog.dismiss();
        }
    }
}