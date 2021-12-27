package uz.javokhirdev.moodiary.presentation.calendar

import org.joda.time.DateTime
import uz.javokhirdev.moodiary.data.model.DayMonthly
import uz.javokhirdev.moodiary.utils.DAYS_COUNT
import uz.javokhirdev.moodiary.utils.DateUtils
import uz.javokhirdev.moodiary.utils.az
import javax.inject.Inject

class MonthlyCalendarImpl @Inject constructor() {

    private var callback: MonthlyCalendarCallback? = null

    lateinit var targetDateTime: DateTime

    fun updateCalendar(dateTime: DateTime) {
        targetDateTime = dateTime
    }

    fun getDays() {
        val days = ArrayList<DayMonthly>(DAYS_COUNT)
        val currMonthDays = targetDateTime.dayOfMonth().maximumValue
        var firstDayIndex = targetDateTime.withDayOfMonth(1).dayOfWeek
        val isSundayFirst = false

        if (!isSundayFirst) firstDayIndex -= 1

        val prevMonthDays = targetDateTime.minusMonths(1).dayOfMonth().maximumValue

        var isThisMonth = false
        var value = prevMonthDays - firstDayIndex + 1
        var curDay = targetDateTime

        for (i in 0 until DAYS_COUNT) {
            when {
                i < firstDayIndex -> {
                    isThisMonth = false
                    curDay = targetDateTime.withDayOfMonth(1).minusMonths(1)
                }
                i == firstDayIndex -> {
                    value = 1
                    isThisMonth = true
                    curDay = targetDateTime
                }
                value == currMonthDays + 1 -> {
                    value = 1
                    isThisMonth = false
                    curDay = targetDateTime.withDayOfMonth(1).plusMonths(1)
                }
            }

            val newDay = curDay.withDayOfMonth(value)
            val dayCode = DateUtils.getDayCodeFromDateTime(newDay)

            val monthOfYear = newDay.monthOfYear().get()
            val year = newDay.year().get()
            val dayOfMonth = newDay.dayOfMonth().get()
            val date = "${year.az()}-${monthOfYear.az()}-${dayOfMonth.az()}"

            val day = DayMonthly(value, isThisMonth, dayCode, date)

            days.add(day)
            value++
        }

        callback?.updateMonthlyCalendar(days, targetDateTime)
    }

    fun setListener(callback: MonthlyCalendarCallback) {
        this.callback = callback
    }

    interface MonthlyCalendarCallback {

        fun updateMonthlyCalendar(
            days: ArrayList<DayMonthly>,
            currTargetDate: DateTime
        )
    }
}