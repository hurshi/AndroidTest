package io.github.hurshi.viewdraw

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.hurshi.viewdraw.absolute_size.OneHundredActivity
import io.github.hurshi.viewdraw.tag.TagTestActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        toTag.setOnClickListener {
            startActivity(Intent(this, TagTestActivity::class.java))
        }
        toAbsoluteSize.setOnClickListener {
            startActivity(Intent(this, OneHundredActivity::class.java))
        }
    }
}
