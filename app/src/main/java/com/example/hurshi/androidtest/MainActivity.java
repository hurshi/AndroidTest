package com.example.hurshi.androidtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toOtherActivity(R.id.button1, DisabledViewClickEventActivity.class);
    }

    private void toOtherActivity(int resID, final Class<?> cls) {
        findViewById(resID).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.toActivity(MainActivity.this, cls);
            }
        });
    }
}