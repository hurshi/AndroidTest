package io.github.hurshi.viewdraw

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        tagLayout.post {
//            LogUtils.e(
//                ">>> tagLayout size= ${tagLayout.measuredWidth},${tagLayout.measuredHeight}\n" +
//                        "left = ${tagLayout.left} top=${tagLayout.top} right=${tagLayout.right} bottom=${tagLayout.bottom}\n" +
//                        "paddingLeft=${tagLayout.paddingLeft} paddingTop=${tagLayout.paddingTop} paddingRight=${tagLayout.paddingRight} paddingBottom=${tagLayout.paddingBottom}"
//            )
//        }

    }
}
