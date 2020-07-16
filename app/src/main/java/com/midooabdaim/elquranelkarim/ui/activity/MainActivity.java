package com.midooabdaim.elquranelkarim.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.midooabdaim.elquranelkarim.R;
import com.midooabdaim.elquranelkarim.ui.fragment.homeCycle.ContinerFragment;

import static com.midooabdaim.elquranelkarim.helper.HelperMethod.replaceFragment;

public class MainActivity extends BaseActivity {
    private static final long SPLASH_DISPLAY_LENGTH = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      /*  new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                *//* Create an Intent that will start the Menu-Activity. *//*
                replaceFragment(getSupportFragmentManager(), R.id.main_activity_frame_layout_id, new ContinerFragment());
            }
        }, SPLASH_DISPLAY_LENGTH);*/
        replaceFragment(getSupportFragmentManager(), R.id.main_activity_frame_layout_id, new ContinerFragment());

    }
}