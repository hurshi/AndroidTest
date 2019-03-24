package com.example.coordinatelayoutbehaviors

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.*
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast

object UiUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(dpValue: Float): Int {
        return (dip2pxF(dpValue)).toInt()
    }

    fun dip2pxF(dpValue: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, Resources.getSystem().displayMetrics)
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 sp
     */
    fun px2sp(context: Context, pxValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 sp 的单位 转成为 px
     */
    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    fun sp2px(spValue: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, Resources.getSystem().displayMetrics)
    }

    /**
     * status bar 高度
     *
     * @param activity
     * @return
     */

    private var statusBarH: Int = -1

    fun getStatusBarHeight(window: Window?): Int {
        if (statusBarH <= 0 && null != window) {
            statusBarH = Rect().apply { window.decorView.getWindowVisibleDisplayFrame(this) }.top
        }
        return statusBarH
    }

    /**
     * toolbar 高度
     *
     */
    private var toolbarH: Int = -1

    fun getToolBarHeight(context: Context): Int {
        if (toolbarH <= 0) {
            val attrs = intArrayOf(R.attr.actionBarSize)
            val ta = context.obtainStyledAttributes(attrs)
            toolbarH = ta.getDimensionPixelSize(0, -1)
            ta.recycle()
        }
        return toolbarH
    }

    /**
     * navigation bar 高度
     *
     * @param activity
     * @return
     */
    private var navigationBarH: Int = -1

    fun getNavigationBarHeight(activity: Activity): Int {
        if (navigationBarH <= 0) {
            val resources = activity.resources
            val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
            navigationBarH = resources.getDimensionPixelSize(resourceId)
        }
        return navigationBarH
    }


    /**
     * 是否含有虚拟按键
     *
     * @param c
     * @return
     */
    private var hasSoftKeys: Int = -1

    fun hasSoftKeys(c: Activity): Boolean {
        if (-1 == hasSoftKeys) {
            var hasSoftwareKeys = true
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                val d = c.windowManager.defaultDisplay

                val realDisplayMetrics = DisplayMetrics()
                d.getRealMetrics(realDisplayMetrics)

                val realHeight = realDisplayMetrics.heightPixels
                val realWidth = realDisplayMetrics.widthPixels

                val displayMetrics = DisplayMetrics()
                d.getMetrics(displayMetrics)

                val displayHeight = displayMetrics.heightPixels
                val displayWidth = displayMetrics.widthPixels

                hasSoftwareKeys = realWidth - displayWidth > 0 || realHeight - displayHeight > 0
            } else {
                val hasMenuKey = ViewConfiguration.get(c).hasPermanentMenuKey()
                val hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK)
                hasSoftwareKeys = !hasMenuKey && !hasBackKey
            }
            hasSoftKeys = if (hasSoftwareKeys) 1 else 0
        }
        return hasSoftKeys == 1
    }

    /**
     * 获取屏幕宽度
     */
    fun getScreenW(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    /**
     * 获取屏幕高度
     */
    fun getScreenH(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

    /**
     * 测量控件
     */
    fun measureView(child: View) {
        var p: ViewGroup.LayoutParams? = child.layoutParams
        if (p == null) {
            p = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }

        val childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width)
        val lpHeight = p.height
        val childHeightSpec: Int
        childHeightSpec = if (lpHeight > 0) {
            View.MeasureSpec.makeMeasureSpec(lpHeight, View.MeasureSpec.EXACTLY)
        } else {
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        }
        child.measure(childWidthSpec, childHeightSpec)
    }

    /**
     * 使背景变暗，1完全变暗，0不变暗
     */
    fun setBackgroundDim(popupWindow: PopupWindow, dim: Float) {
        val container: View = if (popupWindow.background == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                popupWindow.contentView.parent as View
            } else {
                popupWindow.contentView
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                popupWindow.contentView.parent.parent as View
            } else {
                popupWindow.contentView.parent as View
            }
        }
        val context = popupWindow.contentView.context
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val layoutParams = container.layoutParams as WindowManager.LayoutParams
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        layoutParams.dimAmount = dim
        windowManager.updateViewLayout(container, layoutParams)
    }

    fun toast(context: Context, msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun clearTextView(vararg views: TextView?) {
        for (view in views) {
            view?.text = ""
        }
    }

    fun fixTextViewLeftAndSetText(textView: TextView?, text: CharSequence?) {
        if (null == textView || null == text) return
        Rect().also { rect ->
            textView.paint.getTextBounds(text.toString(), 0, text.length, rect)
            textView.x = textView.x - rect.left
        }
        textView.text = text
    }

    fun setViewVisible(visible: Int, vararg views: View?) {
        if (visible == View.VISIBLE || visible == View.GONE || visible == View.INVISIBLE) {
            views.forEach {
                it?.visibility = visible
            }
        }
    }
}