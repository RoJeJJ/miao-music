package com.roje.miao.music.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roje.miao.music.R;

import okhttp3.OkHttpClient;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int WAIT_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));
            finish();
        },WAIT_TIME);
    }
}
