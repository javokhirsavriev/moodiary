package uz.javokhirdev.moodiary.presentation.ui.calendar

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import org.joda.time.DateTime
import uz.javokhirdev.moodiary.R
import uz.javokhirdev.moodiary.data.db.days.DayEntity
import uz.javokhirdev.moodiary.data.model.DayMonthly
import uz.javokhirdev.moodiary.data.model.PercentResponse
import uz.javokhirdev.moodiary.databinding.ActivityCalendarBinding
import uz.javokhirdev.moodiary.presentation.adapters.PercentAdapter
import uz.javokhirdev.moodiary.presentation.calendar.CalendarView
import uz.javokhirdev.moodiary.utils.*
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class CalendarActivity : AppCompatActivity(), CalendarView.CalendarViewListener {

    private val binding by lazy { ActivityCalendarBinding.inflate(layoutInflater) }

    private val viewModel by viewModels<CalendarVM>()

    private val percentAdapter = PercentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            buttonBack.setOnClickListener { finish() }
        }

        configureViewPager()
        configureCalendarView()
    }

    override fun changeMonth(currTargetDate: DateTime) {
        with(binding) {
            textMonth.text = currTargetDate.toString(MONTH_PATTERN)
            textYear.text = currTargetDate.toString(YEAR_PATTERN)
        }

        viewModel.getDays(currTargetDate.toString(DATE_FORMAT_3))
    }

    private fun configureViewPager() {
        with(binding) {
            viewPager.adapter = percentAdapter
            viewPager.autoScroll(
                lifecycleScope = lifecycleScope,
                interval = 10000L
            )
            viewPager.onScrollListener { setLayoutBack(this) }
            indicator.setViewPager(viewPager)

            percentAdapter.registerAdapterDataObserver(indicator.adapterDataObserver)
        }

        with(viewModel) {
            getPercent()

            lifecycleScope.launchWhenStarted { percentState.collect { onPercentState(it) } }
        }
    }

    private fun configureCalendarView() {
        with(binding) {
            calendarView.initView(this@CalendarActivity)

            layoutNext.setOnClickListener { calendarView.rightTapped() }
            layoutPrev.setOnClickListener { calendarView.leftTapped() }
        }

        with(viewModel) {
            lifecycleScope.launchWhenStarted { daysState.collect { onDaysState(it) } }
        }
    }

    private fun onPercentState(uiState: UIState<ArrayList<PercentResponse>>) {
        uiState onSuccess {
            percentAdapter.submitList(data)
        }
    }

    private fun onDaysState(uiState: UIState<java.util.ArrayList<DayEntity>>) {
        uiState onSuccess {
            binding.calendarView.setEvents(data)
        }
    }

    private fun setLayoutBack(position: Int) {
        with(binding) {
            when (position) {
                1 -> {
                    layoutDays.backRes(R.color.colorGoodDay)
                    buttonBack.imageTint(R.color.colorDarkBlack)
                }
                2 -> {
                    layoutDays.backRes(R.color.colorDarkBlack)
                    buttonBack.imageTint(R.color.colorWhite)
                }
                else -> {
                    layoutDays.backRes(R.color.colorBlack)
                    buttonBack.imageTint(R.color.colorWhite)
                }
            }
        }
    }
}