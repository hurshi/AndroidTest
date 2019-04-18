package io.github.hurshi.textmeasure

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1.setOnClickListener {
            startActivity(Intent(this, HollowTextViewActivity::class.java))
        }
        btn2.setOnClickListener {
            startActivity(Intent(this, ImageTextActivity::class.java))
        }
    }

}
