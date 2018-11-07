package com.example.hurshi.androidtest.on_intercept_touch_event;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ChildView extends View {
    public ChildView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MotionEventPrinter.printEvent(event, "ChildView");
        return true;
    }
}
