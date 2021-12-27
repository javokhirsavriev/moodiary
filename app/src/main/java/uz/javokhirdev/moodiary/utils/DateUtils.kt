package uz.javokhirdev.moodiary.utils

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat

object DateUtils {

    fun getToday(format: String): String {
        val fmt = DateTimeFormat.forPattern(format)
        return DateTime().toString(fmt)
    }

    fun getTodayCode() = getDayCodeFromTS(getNowSeconds())

    fun getDateTimeFromCode(dayCode: String): DateTime =
        DateTimeFormat.forPattern(DATE_FORMAT_1).withZone(DateTimeZone.UTC).parseDateTime(dayCode)

    fun getDayCodeFromDateTime(dateTime: DateTime): String = dateTime.toString(DATE_FORMAT_1)

    private fun getDateTimeFromTS(ts: Long) = DateTime(ts * 1000L, DateTimeZone.getDefault())

    private fun getDayCodeFromTS(ts: Long): String {
        val daycode = getDateTimeFromTS(ts).toString(DATE_FORMAT_1)
        return if (daycode.isNotEmpty()) {
            daycode
        } else {
            "0"
        }
    }
}

fun getNowSeconds() = System.currentTimeMillis() / 1000L