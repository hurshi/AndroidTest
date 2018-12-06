package com.example.hurshi.androidtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.hurshi.androidtest.multi.MultiTouchActivity;
import com.example.hurshi.androidtest.on_intercept_touch_event.OnInterceptTouchEventActivity;
import com.example.hurshi.androidtest.scalable.ScalableActivity;
import com.example.hurshi.androidtest.touch_event.DisabledViewClickEventActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toOtherActivity(R.id.button1, DisabledViewClickEventActivity.class);
        toOtherActivity(R.id.button2, OnInterceptTouchEventActivity.class);
        toOtherActivity(R.id.button3, ScalableActivity.class);
        toOtherActivity(R.id.button4, MultiTouchActivity.class, true);


        findViewById(R.id.button1).animate()
    }

    private void toOtherActivity(int resID, final Class<?> cls, boolean... autoClick) {
        findViewById(resID).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.toActivity(MainActivity.this, cls);
            }
        });
        if (autoClick.length > 0 && autoClick[0]) {
            findViewById(resID).performClick();
        }
    }
}
