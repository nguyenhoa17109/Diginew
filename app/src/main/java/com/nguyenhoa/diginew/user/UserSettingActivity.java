package com.nguyenhoa.diginew.user;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.categories.ProvincesBottomSheet;
import com.nguyenhoa.diginew.model.User;

public class UserSettingActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvEditUser, tvLocalNews;
    private EditText etUser_name, etUser_address, et_User_dob, etUser_phoneNum;
    private int EditUserStatus = 0;
    private String username, servicePack;
    private LinearLayout layoutLocalNews, bottom_sheet_provinces;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);

        init();
        setOnClick();
//        User user = getUsername();
//        loadUserToForm(user);


    }

    public void init() {
        getSupportActionBar().hide();
        tvEditUser = findViewById(R.id.tvEditUser);
        tvLocalNews = findViewById(R.id.tvLocalNews);
        etUser_name = findViewById(R.id.etUser_name);
        etUser_address = findViewById(R.id.etUser_address);
        et_User_dob = findViewById(R.id.et_User_dob);
        etUser_phoneNum = findViewById(R.id.etUser_phoneNum);
        layoutLocalNews = findViewById(R.id.layoutLocalNews);
        bottom_sheet_provinces = findViewById(R.id.bottom_sheet_provinces);
        ivBack = findViewById(R.id.ivBack);
    }

    public void setOnClick() {
        tvEditUser.setOnClickListener(this);
        layoutLocalNews.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    public void AllowEdit(int check) {

        if (check == 0) {
            etUser_name.setEnabled(true);
            etUser_address.setEnabled(true);
            et_User_dob.setEnabled(true);
            etUser_phoneNum.setEnabled(true);
            tvEditUser.setText("Lưu");
            EditUserStatus = 1;
        } else {
            etUser_name.setEnabled(false);
            etUser_address.setEnabled(false);
            et_User_dob.setEnabled(false);
            etUser_phoneNum.setEnabled(false);
            tvEditUser.setText("Sửa");
            EditUserStatus = 0;
        }

        // luu vao csdl ...

    }

    public void addToDatabase(User user) {

    }

    public User getUsername() {
        Bundle extras = getIntent().getExtras();
        username = extras.getString("username");
        //lay ra user tu csdl ...
        //tim den user.username
        User user = new User();
        return user;
    }

    public void loadUserToForm(User user) {

    }

    public void showLocalNews() {
//        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet_provinces);
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == tvEditUser.getId()) {
            AllowEdit(EditUserStatus);
        } else if (v.getId() == layoutLocalNews.getId()) {
            showLocalNews();
        } else if (v.getId() == ivBack.getId()) {
            finish();
        }

    }
}