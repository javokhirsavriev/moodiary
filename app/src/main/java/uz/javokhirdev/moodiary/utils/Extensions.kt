package uz.javokhirdev.moodiary.utils

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.delay

fun Int.az(): String = if (this >= 10) "" + this else "0$this"

fun Context.color(@ColorRes color: Int) = ContextCompat.getColor(this, color)

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

fun View.backRes(res: Int) = setBackgroundResource(res)

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

fun ViewPager2.onScrollListener(block: SingleBlock<Int>) =
    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            block(position)
        }
    })

fun ViewPager2.autoScroll(lifecycleScope: LifecycleCoroutineScope, interval: Long) {
    lifecycleScope.launchWhenResumed {
        scrollIndefinitely(interval)
    }
}

private suspend fun ViewPager2.scrollIndefinitely(interval: Long) {
    delay(interval)
    val numberOfItems = adapter?.itemCount ?: 0
    val lastIndex = if (numberOfItems > 0) numberOfItems - 1 else 0
    val nextItem = if (currentItem == lastIndex) 0 else currentItem + 1

    setCurrentItem(nextItem, true)

    scrollIndefinitely(interval)
}