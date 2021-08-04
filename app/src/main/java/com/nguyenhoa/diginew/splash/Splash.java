package com.nguyenhoa.diginew.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import com.nguyenhoa.diginew.R;

public class Splash extends AppCompatActivity {
    SharedPreferences preferences;
    boolean isFirst;
    public static final String PREFER_NAME = "Splash";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        preferences = getSharedPreferences(PREFER_NAME, MODE_PRIVATE);
        isFirst = preferences.getBoolean(PREFER_NAME, false);

        if(isFirst) {
            startActivity(new Intent(Splash.this, Splash1.class));
            finish();
        }
        else{
            setContentView(R.layout.activity_splash);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(Splash.this,Splash1.class));
                }
            },2000);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(PREFER_NAME, true);
            editor.commit();
        }

    }
}