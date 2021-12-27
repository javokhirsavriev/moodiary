package uz.javokhirdev.moodiary.utils

import android.os.SystemClock
import android.view.View
import java.util.*

class ThrottledOnClickListener(private val onClick: SingleBlock<View>) : View.OnClickListener {

    private val lastClickMap: MutableMap<View, Long> = WeakHashMap()

    override fun onClick(clickedView: View) {
        val previousClickTimestamp = lastClickMap[clickedView]
        val currentTimestamp = SystemClock.uptimeMillis()

        lastClickMap[clickedView] = currentTimestamp

        if (previousClickTimestamp == null || currentTimestamp - previousClickTimestamp.toLong() > 1000L) {
            onClick.invoke(clickedView)
        }
    }
}