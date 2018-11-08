package com.example.hurshi.androidtest.on_intercept_touch_event;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class ParentViewGroup extends RelativeLayout {
    public ParentViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    int a = 0;


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        MotionEventPrinter.printEvent(ev, a + " ParentViewGroup onInterceptTouchEvent");
        a++;
        if (a < 20) {
            return false;
        } else if (a < 90) {
            return true;
        } else return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MotionEventPrinter.printEvent(event, "ParentViewGroup onTouchEvent");
        return super.onTouchEvent(event);
    }
}
