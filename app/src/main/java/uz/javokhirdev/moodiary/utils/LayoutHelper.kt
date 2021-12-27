package uz.javokhirdev.moodiary.utils

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LayoutHelper @Inject constructor(@ApplicationContext private val context: Context) {

    private fun getSize(size: Int): Int {
        return if (size < 0) size else context.px(size)
    }

    /* ViewGroup */
    fun createViewGroup(
        width: Int = WRAP_CONTENT,
        height: Int = WRAP_CONTENT
    ): ViewGroup.LayoutParams {
        return ViewGroup.LayoutParams(
            getSize(width),
            getSize(height)
        )
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

    /* ConstraintLayout */
    fun createConstraint(
        width: Int = WRAP_CONTENT,
        height: Int = WRAP_CONTENT
    ): ConstraintLayout.LayoutParams {
        return ConstraintLayout.LayoutParams(
            getSize(width),
            getSize(height)
        )
    }

    fun createConstraint(
        width: Int = WRAP_CONTENT,
        height: Int = WRAP_CONTENT,
        leftMargin: Int = 0,
        topMargin: Int = 0,
        rightMargin: Int = 0,
        bottomMargin: Int = 0,
    ): ConstraintLayout.LayoutParams {
        val layoutParams = ConstraintLayout.LayoutParams(
            getSize(width),
            getSize(height)
        )

        layoutParams.leftMargin = context.px(leftMargin)
        layoutParams.topMargin = context.px(topMargin)
        layoutParams.rightMargin = context.px(rightMargin)
        layoutParams.bottomMargin = context.px(bottomMargin)
        return layoutParams
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
        leftMargin: Int = 0,
        topMargin: Int = 0,
        rightMargin: Int = 0,
        bottomMargin: Int = 0
    ): LinearLayout.LayoutParams {
        val layoutParams = LinearLayout.LayoutParams(
            getSize(width),
            getSize(height)
        )
        layoutParams.setMargins(
            context.px(leftMargin),
            context.px(topMargin),
            context.px(rightMargin),
            context.px(bottomMargin)
        )
        return layoutParams
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

    /* RelativeLayout */
    fun createRelative(
        width: Int = WRAP_CONTENT,
        height: Int = WRAP_CONTENT
    ): RelativeLayout.LayoutParams {
        return RelativeLayout.LayoutParams(
            getSize(width),
            getSize(height)
        )
    }
}