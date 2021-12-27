package uz.javokhirdev.moodiary.presentation.ui.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.javokhirdev.moodiary.R
import uz.javokhirdev.moodiary.data.db.days.DayEntity
import uz.javokhirdev.moodiary.data.db.days.DaysRepository
import uz.javokhirdev.moodiary.data.model.PercentResponse
import uz.javokhirdev.moodiary.utils.UIState
import javax.inject.Inject

@HiltViewModel
class CalendarVM @Inject constructor(private val daysRepository: DaysRepository) : ViewModel() {

    private val _percentState = MutableStateFlow<UIState<ArrayList<PercentResponse>>>(UIState.Ready)
    val percentState = _percentState.asStateFlow()

    private val _daysState = MutableStateFlow<UIState<ArrayList<DayEntity>>>(UIState.Ready)
    val daysState = _daysState.asStateFlow()

    fun getPercent() {
        _percentState.value = UIState.loading(true)

        viewModelScope.launch {
            try {
                val days = daysRepository.getDays()

                val count = days.count()
                val goodDaysCount = days.count { it.isGoodDay }
                val badDaysCount = count - goodDaysCount

                val list = ArrayList<PercentResponse>()
                list.add(
                    PercentResponse(
                        1,
                        "Days",
                        count,
                        R.color.colorWhite
                    )
                )
                list.add(
                    PercentResponse(
                        2,
                        "Good days",
                        goodDaysCount,
                        R.color.colorBlack
                    )
                )
                list.add(
                    PercentResponse(
                        3,
                        "Bad days",
                        badDaysCount,
                        R.color.colorWhite
                    )
                )

                _percentState.value = UIState.loading(false)
                _percentState.value = UIState.success(list)
            } catch (ex: Exception) {
                _percentState.value = UIState.loading(false)
                _percentState.value = UIState.failure(ex.message.orEmpty(), 0)
            }
        }
    }

    fun getDays(monthAndYear: String) {
        _daysState.value = UIState.loading(true)

        viewModelScope.launch {
            try {
                val days = daysRepository.getDays(monthAndYear)

                _daysState.value = UIState.loading(false)
                _daysState.value = UIState.success(days as ArrayList<DayEntity>)
            } catch (ex: Exception) {
                _daysState.value = UIState.loading(false)
                _daysState.value = UIState.failure(ex.message.orEmpty(), 0)
            }
        }
    }
}