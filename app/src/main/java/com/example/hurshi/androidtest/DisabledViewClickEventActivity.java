package com.example.hurshi.androidtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class DisabledViewClickEventActivity extends Activity {
    View v1, v2, v3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disabled_view_click_event);
        v1 = (View) findViewById(R.id.v1);
        v2 = (View) findViewById(R.id.v2);
        v3 = (View) findViewById(R.id.v3);

        setClick(v1, "最 大 的View被点击了");
        setClick(v2, "中间的View被点击了");
        setClick(v3, "最 小 的View被点击了");


        v2.setClickable(false);


    }


    private void setClick(View v, final String msg) {
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(">>>", "view clicked " + msg);
                Toast.makeText(DisabledViewClickEventActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
