package com.example.hurshi.androidtest;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.TypedValue;

public class Utils {
    public static void toActivity(Context context, Class<?> cls) {
        context.getApplicationContext().startActivity(new Intent(context, cls));
    }

    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    public static void log(String msg) {
        Log.e(">>>", msg);
    }

    public static Bitmap getAvatar(Resources res, int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeResource(res, R.mipmap.lufei, options);
//        options.inJustDecodeBounds = false;
//        options.outHeight = options.outHeight / options.outWidth * width;
//        options.outWidth = width;
//        options.inDensity = options.outWidth;
//        options.inScaled = true;
//        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(res, R.mipmap.lufei, options);
    }
}
