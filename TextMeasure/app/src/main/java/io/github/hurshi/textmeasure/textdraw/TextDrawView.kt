package io.github.hurshi.textmeasure.textdraw

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


class TextDrawView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    private val paint: Paint by lazy {
        Paint().also {
            it.textSize = 200f
            it.color = Color.BLACK
        }
    }
    private val pointPoint: Paint by lazy {
        Paint().also {
            it.color = Color.RED
            it.strokeWidth = 10f
        }
    }
    private val rectPaint: Paint by lazy {
        Paint().also {
            it.color = Color.BLACK
            it.strokeWidth = 2f
            it.style = Paint.Style.STROKE
        }
    }

    private val centerPoint = PointF(300f, 300f)
    private val text = "A"


    override fun onDraw(canvas: Canvas?) {
        canvas?.drawFilter = PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
        super.onDraw(canvas)
        canvas?.drawText(text, centerPoint.x, centerPoint.y, paint)
        canvas?.drawPoint(centerPoint.x, centerPoint.y, pointPoint)

//        Rect().also { paint.getTextBounds(text, 0, text.length, it) }
//            .also {
//                it.left += (centerPoint.x).toInt()
//                it.right += (centerPoint.x).toInt()
//                it.top += (centerPoint.y).toInt()
//                it.bottom += (centerPoint.y).toInt()
//                canvas?.drawRect(it, rectPaint)
//            }
    }


}