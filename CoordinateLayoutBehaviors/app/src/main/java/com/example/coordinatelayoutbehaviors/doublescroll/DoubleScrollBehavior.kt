package com.example.coordinatelayoutbehaviors.doublescroll

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.widget.NestedScrollView
import com.example.coordinatelayoutbehaviors.R

class DoubleScrollBehavior(context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<View>(context, attrs) {
    private var scroll1: NestedScrollView? = null
    private var scroll2: NestedScrollView? = null

    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        scroll1 = parent.findViewById(R.id.s1)
        scroll2 = parent.findViewById(R.id.s2)
        return super.layoutDependsOn(parent, child, dependency)
    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return if (axes == ViewCompat.SCROLL_AXIS_VERTICAL) true
        else super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type)
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
        val t = if (target == scroll1) scroll2 else if (target == scroll2) scroll1 else null
        t?.smoothScrollBy(0, dyConsumed)
    }

//    override fun onNestedFling(
//        coordinatorLayout: CoordinatorLayout,
//        child: View,
//        target: View,
//        velocityX: Float,
//        velocityY: Float,
//        consumed: Boolean
//    ): Boolean {
//        val t = if (target == scroll1) scroll2 else if (target == scroll2) scroll1 else null
//        t?.onNestedFling(target, velocityX, velocityY, consumed)
//        return true
//    }

}