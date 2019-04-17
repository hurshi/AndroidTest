package io.github.hurshi.textmeasure

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View

class HollowTextView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var rects: List<Rect>? = null
    private var text: String? = null
    private var paintTop = 0f
    private var textIndex = 0
    private var debugMode = false

    private val paint: Paint by lazy {
        Paint().also {
            it.textSize = 80f
        }
    }
    private val rectPaint: Paint by lazy {
        Paint().also {
            it.color = Color.parseColor("#55ff0000")
        }
    }

    fun setHollows(rects: List<Rect>) {
        this.rects = rects
    }

    fun setText(text: String) {
        this.text = text
    }

    fun setDebugMode(value: Boolean) {
        this.debugMode = value
    }

    override fun onDraw(canvas: Canvas?) {
        while (null != text && textIndex < text!!.length) {
            getAvailableArea(canvas).forEach { pair ->
                val count = paint.breakText(text!!.substring(textIndex), true, (pair.right - pair.left).toFloat(), null)
                if (debugMode) Log.e(">>>", "textCount=${text!!.length} textIndex=$textIndex count=$count")
                canvas?.drawText(
                    text.toString(),
                    textIndex,
                    textIndex + count,
                    pair.left.toFloat(),
                    paintTop + Math.abs(paint.fontMetrics.ascent),
                    paint
                )
                textIndex += count
            }
            paintTop += paint.fontSpacing
        }

        if (debugMode) {
            rectPaint.color = Color.parseColor("#55ff0000")
            rects?.forEach {
                canvas?.drawRect(it, rectPaint)
            }
        }
    }

    private fun getAvailableArea(canvas: Canvas?): List<Pair> {
        var output = mutableListOf<Pair>()
        if (paintTop <= 0) paintTop = paddingTop.toFloat()
        val rect = Rect(paddingLeft, paintTop.toInt(), width - paddingRight, (paintTop + paint.fontSpacing).toInt())
        if (debugMode) Log.e(">>>", "before left=${rect.left} right=${rect.right}")

        rectPaint.color = Color.parseColor("#55${getRandomColor()}")
        if (debugMode) canvas?.drawRect(rect, rectPaint)
        output.add(Pair(rect.left, rect.right))
        rects?.forEach { exceptRect ->
            if (exceptRect.top > rect.bottom || exceptRect.bottom < rect.top) {
            } else
                output = exceptIntersectArea(output, Pair(exceptRect.left, exceptRect.right)).toMutableList()
        }
        if (debugMode) {
            output.forEach {
                Log.e(">>>", "after left=${it.left} right=${it.right}")
            }
        }
        return output
    }

    private fun getRandomColor(): String {
        return "${(Math.random() * 10).toInt()}${(Math.random() * 10).toInt()}${(Math.random() * 10).toInt()}${(Math.random() * 10).toInt()}${(Math.random() * 10).toInt()}${(Math.random() * 10).toInt()}"
    }

    private fun exceptIntersectArea(pairs: List<Pair>, exceptPair: Pair): List<Pair> {
        val output = mutableListOf<Pair>()
        pairs.forEach { p ->
            val exceptLeftPointIn = isPointInPair(exceptPair.left, p)
            val exceptRightPointIn = isPointInPair(exceptPair.right, p)
            if (exceptLeftPointIn && exceptRightPointIn) {
                output.add(Pair(p.left, exceptPair.left))
                output.add(Pair(exceptPair.right, p.right))
            } else if (exceptLeftPointIn) {
                output.add(Pair(p.left, exceptPair.left))
            } else if (exceptRightPointIn) {
                output.add(Pair(exceptPair.right, p.right))
            } else {
                output.add(p)
            }
        }
        return output
    }

    private fun isPointInPair(point: Int, pair: Pair): Boolean {
        return point > pair.left && point < pair.right
    }


    class Pair {
        constructor(left: Int, right: Int) {
            this.left = left
            this.right = right
        }

        var left: Int = 0
        var right: Int = 0
    }

}