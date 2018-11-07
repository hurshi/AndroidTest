package com.example.hurshi.androidtest;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.TypedValue;

public class Utils {
    public static void toActivity(Context context, Class<?> cls) {
        context.getApplicationContext().startActivity(new Intent(context, cls));
    }

    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }
}
