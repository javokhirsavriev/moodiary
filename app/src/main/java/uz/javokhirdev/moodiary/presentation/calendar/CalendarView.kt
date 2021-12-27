package uz.javokhirdev.moodiary.presentation.calendar

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.joda.time.DateTime
import uz.javokhirdev.moodiary.R
import uz.javokhirdev.moodiary.data.db.days.DayEntity
import uz.javokhirdev.moodiary.data.model.DayMonthly
import uz.javokhirdev.moodiary.utils.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import android.os.Parcelable
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.findViewTreeLifecycleOwner

@AndroidEntryPoint
class CalendarView : LinearLayout, MonthlyCalendarImpl.MonthlyCalendarCallback {

    @Inject
    lateinit var calendar: MonthlyCalendarImpl

    @Inject
    lateinit var layoutHelper: LayoutHelper

    private var listener: CalendarViewListener? = null

    private var codes: List<String> = ArrayList()
    private val events = ArrayList<DayEntity>()

    private var todayCode = ""
    private var currentDayCode = ""

    private var defaultMonthlyPage = 0

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr)

    override fun updateMonthlyCalendar(
        days: ArrayList<DayMonthly>,
        currTargetDate: DateTime
    ) {
        updateDays(days, currTargetDate)
    }

    init {
        orientation = VERTICAL
    }

    fun initView(listener: CalendarViewListener) {
        this.listener = listener

        configureDayCodes()
        configureCalendarImpl()
    }

    private fun configureDayCodes() {
        todayCode = DateUtils.getTodayCode()
        currentDayCode = todayCode
        codes = getMonths(currentDayCode)
        defaultMonthlyPage = codes.size / 2
    }

    private fun configureCalendarImpl() {
        val dateTime = DateUtils.getDateTimeFromCode(todayCode)

        calendar.apply {
            setListener(this@CalendarView)
            targetDateTime = dateTime

            listener?.changeMonth(dateTime)
        }
    }

    private fun updateDays(
        days: ArrayList<DayMonthly>,
        currTargetDate: DateTime
    ) {
        removeAllViews()

        var curId = 0

        for (y in 0 until ROW_COUNT) {
            val layout = getHorizontalLayout()

            for (x in 0 until COLUMN_COUNT) {
                days.getOrNull(curId)?.let { day ->
                    val dayLayout = getDayLayout(context, day)
                    layout.addView(dayLayout)

                    curId++
                }
            }

            addView(layout)
        }

        listener?.changeMonth(currTargetDate)
    }

    private fun getDayLayout(context: Context, day: DayMonthly): View {
        val layoutDay = View(context).apply {
            layoutParams = layoutHelper.createLinear(0, 52, 1f)

            val event = events.find { it.createdAt == day.code }

            when {
                day.isThisMonth && event != null && event.isGoodDay -> backRes(R.color.colorWhite)
                day.isThisMonth && event != null && !event.isGoodDay -> backRes(R.color.colorDarkBlack)
                else -> backRes(R.color.colorTransparent)
            }
        }

        return layoutDay
    }

    private fun getHorizontalLayout(): LinearLayout {
        return LinearLayout(context).apply {
            layoutParams = layoutHelper.createLinear(MATCH_PARENT, MATCH_PARENT)
            orientation = HORIZONTAL
        }
    }

    private fun getMonths(code: String): List<String> {
        val months = ArrayList<String>(PREFILLED_MONTHS)
        val today = DateUtils.getDateTimeFromCode(code).withDayOfMonth(1)
        for (i in -PREFILLED_MONTHS / 2..PREFILLED_MONTHS / 2) {
            months.add(DateUtils.getDayCodeFromDateTime(today.plusMonths(i)))
        }

        return months
    }

    fun leftTapped() {
        defaultMonthlyPage -= 1
        currentDayCode = codes[defaultMonthlyPage]

        listener?.changeMonth(DateUtils.getDateTimeFromCode(currentDayCode))
    }

    fun rightTapped() {
        defaultMonthlyPage += 1
        currentDayCode = codes[defaultMonthlyPage]

        listener?.changeMonth(DateUtils.getDateTimeFromCode(currentDayCode))
    }

    fun setEvents(days: ArrayList<DayEntity>? = null) {
        events.clear()

        days?.let {
            events.addAll(it)

            calendar.updateCalendar(DateUtils.getDateTimeFromCode(currentDayCode))
            calendar.getDays()
        }
    }

    interface CalendarViewListener {
        fun changeMonth(currTargetDate: DateTime)
    }
}