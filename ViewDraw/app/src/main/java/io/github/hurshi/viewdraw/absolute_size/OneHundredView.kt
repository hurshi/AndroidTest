package io.github.hurshi.viewdraw.absolute_size

import android.content.Context
import android.util.AttributeSet
import android.view.View

class OneHundredView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    override fun layout(l: Int, t: Int, r: Int, b: Int) {
//        super.layout(l, t, r, b)
        //我不管我不管，我就要显示在 这里
        super.layout(100, 100, 200, 200)
    }
}