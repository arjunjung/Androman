package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.android.android_me.R;

import java.util.Objects;

public class SplashActivity extends AppCompatActivity {

    private static final int AUTO_HIDE_DELAY_MILLIS = 2000;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        requestWindowFeature( Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(getSupportActionBar() != null) getSupportActionBar().hide();

        new Handler(  ).postDelayed( new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,AndroidMeActivity.class);
                startActivity(intent);
                finish();
            }
        },1000);
    }
}