package com.depth.quran.quran_indepth.activity.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.depth.quran.quran_indepth.R;


public class SplashScreenActivity extends AppCompatActivity {

    private final Handler mHandler = new Handler();
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mContext = this;

        mHandler.postDelayed(mPendingLauncherRunnable, 2000);
    }

    private final Runnable mPendingLauncherRunnable = new Runnable() {
        @Override
        public void run() {

            Intent mm = new Intent(SplashScreenActivity.this, ExplorerActivity.class);
            startActivity(mm);
            SplashScreenActivity.this.finish();

        }
    };


}
