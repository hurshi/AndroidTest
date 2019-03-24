package com.example.coordinatelayoutbehaviors.themirrordraw

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.coordinatelayoutbehaviors.R

open class TheMirrorDrawBehavior(context: Context, attrs: AttributeSet) :
    CoordinatorLayout.Behavior<View>(context, attrs) {
    private var theMirrorLeftDrawView: DrawView? = null
    private var theMirrorRightDrawView: DrawView? = null

    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        theMirrorLeftDrawView = parent.findViewById(R.id.dLeft)
        theMirrorRightDrawView = parent.findViewById(R.id.dRight)
        return super.layoutDependsOn(parent, child, dependency)
    }

    override fun onInterceptTouchEvent(parent: CoordinatorLayout, child: View, ev: MotionEvent): Boolean {
        return ev.action == MotionEvent.ACTION_DOWN || ev.action == MotionEvent.ACTION_MOVE
    }

    override fun onTouchEvent(parent: CoordinatorLayout, child: View, ev: MotionEvent): Boolean {
//        Log.e(">>>", " left witdh=${theMirrorLeftDrawView?.width} right width=${theMirrorRightDrawView?.width}")
//        theMirrorLeftDrawView?.addEvent(ev)
//        theMirrorRightDrawView?.addEvent(ev)
        return ev.action == MotionEvent.ACTION_DOWN || ev.action == MotionEvent.ACTION_MOVE
    }
}