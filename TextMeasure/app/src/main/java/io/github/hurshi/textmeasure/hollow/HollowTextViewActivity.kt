package io.github.hurshi.textmeasure.hollow

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.hurshi.textmeasure.R
import kotlinx.android.synthetic.main.activity_hollow_text_view.*

class HollowTextViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hollow_text_view)


        hollowTextView.setDebugMode(true)
        hollowTextView.setText(getString(R.string.long_str))
        hollowTextView.setHollows(getRects())
        hollowTextView.invalidate()
    }

    private fun getRects(): List<Rect> {
        val rects = mutableListOf<Rect>()
        rects.add(Rect(10, 10, 300, 300))
        rects.add(Rect(500, 540, 750, 745))
        rects.add(Rect(800, 1340, 1200, 1900))

        return rects
    }
}
