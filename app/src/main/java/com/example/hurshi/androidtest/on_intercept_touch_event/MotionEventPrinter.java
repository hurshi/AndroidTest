package com.example.hurshi.androidtest.on_intercept_touch_event;

import android.view.MotionEvent;

import com.example.hurshi.androidtest.Utils;

public class MotionEventPrinter {
    public static void printEvent(MotionEvent event, String msg) {
        String motionEvent = " motionEvent = ";
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                motionEvent += "DOWN";
                break;
            case MotionEvent.ACTION_UP:
                motionEvent += "UP";
                break;
            case MotionEvent.ACTION_MOVE:
                motionEvent += "MOVE";
                break;
        }
        Utils.log(msg + motionEvent);
    }
}
