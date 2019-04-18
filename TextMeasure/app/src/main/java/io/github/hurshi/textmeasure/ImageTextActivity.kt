package io.github.hurshi.textmeasure

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_image_text.*

class ImageTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_text)


        imageTextView.setText(getString(R.string.long_str))
        imageTextView.addImage(
            getDrawable(R.mipmap.lufei),
            Rect(100, 100, 500, 500)
        )
        imageTextView.addImage(
            getDrawable(R.mipmap.avatar_luffy2),
            Rect(500, 700, 1100, 1300)
        )
        imageTextView.invalidate()

    }
}
