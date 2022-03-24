package com.example.bible;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;

import java.util.Locale;

public class SplashScreenActivity extends AppCompatActivity {

    private int loading = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String codeLocal = SettingPreferences.getCodeLanguage(getBaseContext());
                Configuration conf = getResources().getConfiguration();
                conf.locale = new Locale(codeLocal);
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                Resources resources = new Resources(getAssets(), metrics, conf);

                Intent landing = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(landing);
                finish();
            }
        }, loading);
    }
}