package com.nguyenhoa.diginew.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import com.nguyenhoa.diginew.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirst = prefs.getBoolean("splash", true);
        if(isFirst){
            setContentView(R.layout.activity_splash);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent splash = new Intent(Splash.this,Splash1.class);
                    startActivity(splash);
                    finish();
                }
            },2000);
        }
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("splash", Boolean.FALSE);
        editor.commit();

        Intent splash = new Intent(Splash.this,Splash1.class);
        startActivity(splash);
    }
}