package com.example.hurshi.androidtest.scalable;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;

import com.example.hurshi.androidtest.Utils;

public class ScalableImageView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, Runnable {
    private static final float IMAGE_WIDTH = Utils.dp2px(200);
    private static final int OVER_SCROLL_DISTANCE = (int) Utils.dp2px(80);
    private Bitmap bt;
    private Paint paint;
    private GestureDetector detector;

    private float bigScale;
    private float scale;
    private OverScroller scroller;

    private ObjectAnimator scaleAnimator;
    private float offsetX, offsetY;
    private boolean fling = false;

    public ScalableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(Utils.dp2px(10));
        bt = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        float smallScale = Math.min((float) getWidth() / bt.getWidth(), (float) getHeight() / bt.getHeight());
        bigScale = Math.max((float) getWidth() / bt.getWidth(), (float) getHeight() / bt.getHeight()) * 1.5f;
        scale = smallScale;
        detector = new GestureDetector(getContext(), this);
        scaleAnimator = ObjectAnimator.ofFloat(this, "scale", smallScale, bigScale);
        scroller = new OverScroller(getContext());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(offsetX, offsetY);
        canvas.scale(scale, scale, getWidth() / 2.0f, getHeight() / 2.0f);
        canvas.drawBitmap(bt, (getWidth() - bt.getWidth()) / 2.0f, (getHeight() - bt.getHeight()) / 2.0f, paint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        if (scale >= bigScale) {
            scaleAnimator.reverse();
        } else scaleAnimator.start();
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        offsetX -= distanceX;
        offsetY -= distanceY;
        checkBorderAndInvalidate();
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        fling = true;
        float maxOffsetX = Math.abs((bt.getWidth() * scale - getWidth())) / 2.0f;
        float maxOffsetY = Math.abs(bt.getHeight() * scale - getHeight()) / 2.0f;

        scroller.fling((int) offsetX, (int) offsetY,
                (int) velocityX, (int) velocityY,
                -(int) maxOffsetX, (int) maxOffsetX,
                -(int) maxOffsetY, (int) maxOffsetY,
                OVER_SCROLL_DISTANCE, OVER_SCROLL_DISTANCE);
        postOnAnimation(this);
        return false;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
        checkBorderAndInvalidate();
    }

    private void checkBorderAndInvalidate() {
        boolean toValidate = false;
        float maxOffsetX = Math.abs((bt.getWidth() * scale - getWidth())) / 2.0f + (fling ? OVER_SCROLL_DISTANCE : 0);
        float maxOffsetY = Math.abs(bt.getHeight() * scale - getHeight()) / 2.0f + (fling ? OVER_SCROLL_DISTANCE : 0);

        if (offsetX < maxOffsetX && offsetX > -maxOffsetX) {
            toValidate = true;
        } else if (offsetX > maxOffsetX) {
            offsetX = maxOffsetX;
            toValidate = true;
        } else if (offsetX < -maxOffsetX) {
            offsetX = -maxOffsetX;
            toValidate = true;
        }

        if (offsetY < maxOffsetY && offsetY > -maxOffsetY) {
            toValidate = true;
        } else if (offsetY > maxOffsetY) {
            offsetY = maxOffsetY;
            toValidate = true;
        } else if (offsetY < -maxOffsetY) {
            offsetY = -maxOffsetY;
            toValidate = true;
        }
        if (toValidate) {
            invalidate();
        }
    }

    @Override
    public void run() {
        if (scroller.computeScrollOffset()) {
            offsetX = scroller.getCurrX();
            offsetY = scroller.getCurrY();
            checkBorderAndInvalidate();
            postOnAnimation(this);
        } else {
            fling = false;
        }
    }
}
