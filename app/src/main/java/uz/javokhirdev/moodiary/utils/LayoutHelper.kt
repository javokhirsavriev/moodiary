package uz.javokhirdev.moodiary.utils

import android.content.Context
import android.widget.FrameLayout
import android.widget.LinearLayout
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LayoutHelper @Inject constructor(@ApplicationContext private val context: Context) {

    private fun getSize(size: Int): Int {
        return if (size < 0) size else context.px(size)
    }

    /* ViewGroup */
    fun createFrame(
        width: Int = WRAP_CONTENT,
        height: Int = WRAP_CONTENT
    ): FrameLayout.LayoutParams {
        return FrameLayout.LayoutParams(
            getSize(width),
            getSize(height)
        )
    }

    /* LinearLayout */
    fun createLinear(
        width: Int = WRAP_CONTENT,
        height: Int = WRAP_CONTENT
    ): LinearLayout.LayoutParams {
        return LinearLayout.LayoutParams(
            getSize(width),
            getSize(height)
        )
    }

    fun createLinear(
        width: Int = WRAP_CONTENT,
        height: Int = WRAP_CONTENT,
        weight: Float,
        leftMargin: Int = 0,
        topMargin: Int = 0,
        rightMargin: Int = 0,
        bottomMargin: Int = 0
    ): LinearLayout.LayoutParams {
        val layoutParams = LinearLayout.LayoutParams(
            getSize(width),
            getSize(height),
            weight
        )
        layoutParams.setMargins(
            context.px(leftMargin),
            context.px(topMargin),
            context.px(rightMargin),
            context.px(bottomMargin)
        )
        return layoutParams
    }
}