package uz.javokhirdev.moodiary.utils

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity

fun Context.color(@ColorRes color: Int) = ContextCompat.getColor(this, color)
fun Context.drawable(@DrawableRes drawable: Int) = ContextCompat.getDrawable(this, drawable)

fun Context.px(dp: Int): Int {
    return (dp * resources.displayMetrics.density).toInt()
}

fun <T> FragmentActivity.start(it: Class<T>) {
    val intent = Intent(this, it)
    startActivity(intent)
}

fun View.onClick(block: SingleBlock<View>) {
    setOnClickListener(ThrottledOnClickListener(block))
}

fun View.backTint(color: Int) {
    backgroundTintList = ContextCompat.getColorStateList(context, color)
}

fun View.backRes(res: Int) = setBackgroundResource(res)

fun View.beVisibleIf(isVisible: Boolean) = if (isVisible) beVisible() else beGone()

fun View.beVisible() {
    isVisible = true
}

fun View.beGone() {
    isGone = true
}

fun ImageView.imageTint(color: Int) {
    imageTintList = ContextCompat.getColorStateList(context, color)
}

fun ImageView.imageRes(res: Int? = null) = res?.let { setImageResource(it) }

fun TextView.textColor(@ColorRes color: Int) = setTextColor(context.color(color))