package com.example.coordinatelayoutbehaviors.themirrordraw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class DrawView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val points = mutableListOf<Float>()
    private val paint = Paint().also {
        it.isAntiAlias = true
        it.color = Color.BLACK
        it.strokeWidth = 2f
    }
    private var bgColor = Color.WHITE

    fun setBgColor(color: Int) {
        bgColor = color
        invalidate()
    }

    fun addEvent(event: MotionEvent?) {
        when (event?.action) {
            MotionEvent.ACTION_MOVE -> {
                addPoint(event.x, event.y)
            }
        }
    }

    private fun addPoint(px: Float, py: Float) {
        Log.e(">>>", " [${px.toInt()},${py.toInt()}] $tag")
        points.add(px)
        points.add(py)
        invalidate()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(bgColor)
        canvas?.drawLines(points.toFloatArray(), paint)
    }
}