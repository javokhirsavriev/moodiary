package uz.javokhirdev.moodiary.utils

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

object DateUtils {

    fun getToday(format: String): String {
        val fmt = DateTimeFormat.forPattern(format)
        return DateTime().toString(fmt)
    }
}