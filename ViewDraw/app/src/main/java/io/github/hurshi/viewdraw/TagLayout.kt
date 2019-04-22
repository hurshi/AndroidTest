package io.github.hurshi.viewdraw

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

class TagLayout : ViewGroup {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)

        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        (0 until childCount).forEach { childViewIndex ->
            val v = getChildAt(childViewIndex)
            val lp = v.layoutParams
            var cWidthSize: Int
            var cWidthMode: Int
            var cHeightSize: Int
            var cHeightMode: Int

            when (lp.width) {
                LayoutParams.WRAP_CONTENT -> {
                    cWidthSize = widthSize
                    cWidthMode = MeasureSpec.AT_MOST
                }
                LayoutParams.MATCH_PARENT -> {
                    cWidthSize = widthSize
                    cWidthMode = MeasureSpec.EXACTLY
                }
                else -> {
                    cWidthSize = lp.width
                    cWidthMode = MeasureSpec.EXACTLY
                }
            }

            when (lp.height) {
                LayoutParams.WRAP_CONTENT -> {
                    cHeightSize = heightSize
                    cHeightMode = MeasureSpec.AT_MOST
                }
                LayoutParams.MATCH_PARENT -> {
                    cHeightSize = heightSize
                    cHeightMode = MeasureSpec.EXACTLY
                }
                else -> {
                    cHeightSize = lp.height
                    cHeightMode = MeasureSpec.EXACTLY
                }
            }
            measureChild(v, cWidthSize, cWidthMode, cHeightSize, cHeightMode)
        }
    }

    private fun measureChild(v: View, widthSize: Int, widthMode: Int, heightSize: Int, heightMode: Int) {
        v.measure(
            MeasureSpec.makeMeasureSpec(widthSize, widthMode),
            MeasureSpec.makeMeasureSpec(heightSize, heightMode)
        )
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        var widthUsed = 0
        var heightUsed = 0
        val allWidth = measuredWidth - paddingLeft - paddingStart - paddingRight - paddingEnd
        var maxHeightInSingleLine = 0
        (0 until childCount).forEach { childViewIndex ->
            val v = getChildAt(childViewIndex)
            val lp = v.layoutParams as MarginLayoutParams
            val layoutChild = {
                v.layout(
                    paddingLeft + widthUsed + lp.leftMargin,
                    paddingTop + heightUsed + lp.topMargin,
                    paddingLeft + widthUsed + lp.leftMargin + v.measuredWidth,
                    paddingTop + heightUsed + lp.topMargin + v.measuredHeight
                )
                widthUsed += (v.measuredWidth + lp.leftMargin + lp.rightMargin)
                maxHeightInSingleLine =
                    Math.max(maxHeightInSingleLine, v.measuredHeight + lp.topMargin + lp.bottomMargin)
            }
            if (v.measuredWidth + lp.leftMargin + lp.rightMargin <= allWidth - widthUsed) {
                layoutChild()
            } else {
                widthUsed = 0
                heightUsed += maxHeightInSingleLine
                maxHeightInSingleLine = 0
                layoutChild()
            }
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): MarginLayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}