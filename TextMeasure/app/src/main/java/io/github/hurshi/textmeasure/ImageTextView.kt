package io.github.hurshi.textmeasure

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView

class ImageTextView : ViewGroup {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val textView: HollowTextView by lazy {
        HollowTextView(context).also {
            it.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            it.setPadding(0, 0, 0, 0)
//            it.setDebugMode(true)
        }
    }

    private val images = mutableMapOf<ImageView, Rect>()

    init {
        addView(textView)
    }

    fun setText(text: String) {
        textView.setText(text)
    }

    fun addImage(drawable: Drawable, imageRect: Rect) {
        addView(ImageView(context).also {
            it.setImageDrawable(drawable)
            it.scaleType = ImageView.ScaleType.CENTER_CROP
            it.layoutParams = LayoutParams(imageRect.width(), imageRect.height())
            images[it] = imageRect
//            it.alpha = 0.3f
        })
        textView.setHollows(images.map { it.value }.toList())
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        textView.measure(
            MeasureSpec.makeMeasureSpec(measuredWidth, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(measuredHeight, MeasureSpec.EXACTLY)
        )
        images.forEach {
            it.key.measure(
                MeasureSpec.makeMeasureSpec(it.value.width(), MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(it.value.height(), MeasureSpec.EXACTLY)
            )
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        textView.layout(0, 0, textView.measuredWidth, textView.measuredHeight)
        images.forEach {
            it.key.layout(it.value.left, it.value.top, it.value.right, it.value.bottom)
        }
    }
}