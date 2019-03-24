package com.example.coordinatelayoutbehaviors

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.coordinatelayoutbehaviors.doublescroll.DoubleScrollActivity
import com.example.coordinatelayoutbehaviors.themirrordraw.TheMirrorDrawActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clickToActivity(b1, ScrollingActivity::class.java)
        clickToActivity(b2, DoubleScrollActivity::class.java)
        clickToActivity(b3, TheMirrorDrawActivity::class.java)
    }


    private fun clickToActivity(v: View, cls: Class<*>) {
        v.setOnClickListener {
            startActivity(Intent(this@MainActivity, cls))
        }
    }
}
