package io.github.hurshi.viewdraw.tag

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

class TagLayout : ViewGroup {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val childRectMaps = mutableMapOf<View, Rect>()


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)

        var widthUsed = 0
        var heightUsed = 0

        var maxHeightInSingleLine = 0
        var maxLineWidth = 0
        var maxHeight = 0
        val matchParentViews = mutableListOf<View>()

        (0 until childCount).forEach { childViewIndex ->
            val v = getChildAt(childViewIndex)
            measureChildWithMargins(v, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)

            val lp = v.layoutParams as MarginLayoutParams

            if (widthMode == MeasureSpec.AT_MOST && lp.width == LayoutParams.MATCH_PARENT) {
                heightUsed += maxHeightInSingleLine
                widthUsed = 0
                matchParentViews.add(v)
            } else {
                var measuredChildWidthWithMargin = v.measuredWidth + lp.leftMargin + lp.rightMargin

                //如果子 View 测量后总宽度超过了本 View 的宽度，则进行换行，然后再次测量
                if ((widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST)
                    && (paddingLeft + paddingRight + widthUsed + measuredChildWidthWithMargin) > widthSize
                ) {
                    heightUsed += maxHeightInSingleLine
                    maxLineWidth = Math.max(maxLineWidth, widthUsed)
                    maxHeightInSingleLine = 0
                    widthUsed = 0
                    measureChildWithMargins(v, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
                }
                maxHeightInSingleLine =
                    Math.max(maxHeightInSingleLine, v.measuredHeight + lp.topMargin + lp.bottomMargin)
                widthUsed += measuredChildWidthWithMargin
            }

            //把 View 的位置等信息记录起来
            childRectMaps[v] = Rect(
                widthUsed - v.measuredWidth - lp.rightMargin + paddingLeft,
                heightUsed + lp.topMargin + paddingTop,
                widthUsed - lp.rightMargin + paddingLeft,
                heightUsed + lp.topMargin + v.measuredHeight + paddingTop
            )
            maxHeight = Math.max(maxHeight, heightUsed + lp.topMargin + lp.bottomMargin + v.measuredHeight + paddingTop)
            if (widthMode == MeasureSpec.AT_MOST && lp.width == LayoutParams.MATCH_PARENT) {
                heightUsed += (v.measuredHeight + lp.topMargin + lp.bottomMargin)
            }
        }

        matchParentViews.forEach { v ->
            val originRect = childRectMaps[v]
            val lp = v.layoutParams as MarginLayoutParams
            if (null != originRect) {
//                measureChildWithMargins(v, widthMeasureSpec, 0, heightMeasureSpec, originRect.top)
                childRectMaps[v] = Rect(
                    paddingLeft + lp.leftMargin,
                    originRect.top,
                    paddingLeft + maxLineWidth - lp.rightMargin,
                    originRect.bottom
                )
            }
        }

        setMeasuredDimension(
            View.resolveSize(maxLineWidth + paddingLeft + paddingRight, widthMeasureSpec),
            View.resolveSize(maxHeight + paddingTop + paddingBottom, heightMeasureSpec)
        )
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        (0 until childCount).forEach { childViewIndex ->
            val v = getChildAt(childViewIndex)
            val rect = childRectMaps[v]
            if (null != rect) {
                v.layout(rect.left, rect.top, rect.right, rect.bottom)
            }
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): MarginLayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}