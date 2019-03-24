package com.example.coordinatelayoutbehaviors.themirrordraw

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import com.example.coordinatelayoutbehaviors.R
import com.example.coordinatelayoutbehaviors.UiUtil
import kotlinx.android.synthetic.main.activity_the_mirror_draw.*
import java.util.concurrent.TimeUnit

class TheMirrorDrawActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_the_mirror_draw)

        fixLayoutParams()

        dLeft.setBgColor(Color.parseColor("#44ff0000"))
        dRight.setBgColor(Color.parseColor("#4400ff00"))
    }

    private fun fixLayoutParams() {
        val halfScreenWidth = (UiUtil.getScreenW() / 2.0f).toInt()
        val lpLeft = dLeft.layoutParams
        lpLeft.width = halfScreenWidth
        dLeft.layoutParams = lpLeft

        dRight.x = halfScreenWidth.toFloat()
        val lpRight = dRight.layoutParams
        lpRight.width = halfScreenWidth
//        (lpRight as ViewGroup.MarginLayoutParams).marginStart = halfScreenWidth
        dRight.layoutParams = lpRight
    }
}
