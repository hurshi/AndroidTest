package com.example.hurshi.androidtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class DisabledViewClickEventActivity extends Activity {
    View v1, v2, v3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disabled_view_click_event);
        v1 = (View) findViewById(R.id.v1);
        v2 = (View) findViewById(R.id.v2);
        v3 = (View) findViewById(R.id.v3);

        setClick(v1, "view 1");
        setClick(v2, "view 2");
        setClick(v3, "view 3");


        v2.setClickable(false);

    }


    private void setClick(View v, final String msg) {
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(">>>", "view clicked " + msg);
            }
        });
    }
}
