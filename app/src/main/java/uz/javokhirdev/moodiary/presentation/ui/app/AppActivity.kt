package uz.javokhirdev.moodiary.presentation.ui.app

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import uz.javokhirdev.moodiary.R
import uz.javokhirdev.moodiary.data.db.days.DayEntity
import uz.javokhirdev.moodiary.databinding.ActivityAppBinding
import uz.javokhirdev.moodiary.utils.*

@AndroidEntryPoint
class AppActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAppBinding.inflate(layoutInflater) }

    private val viewModel by viewModels<AppVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            textToday.text = DateUtils.getToday(DATE_FORMAT_2)

            layoutViewCalendar.onClick { navigateToCalendar() }

            imageGood.onClick { viewModel.insertOrUpdate(true) }
            imageBad.onClick { viewModel.insertOrUpdate(false) }
        }

        with(viewModel) {
            lifecycleScope.launchWhenStarted { todayState.collect { onTodayState(it) } }
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.getDay()
    }

    private fun onTodayState(uiState: UIState<DayEntity>) {
        uiState onSuccess {
            data?.let {
                if (it.isGoodDay) setGoodDay() else setBadDay()
            } ?: setUnmark()
        } onFailure {
            setUnmark()
        }
    }

    private fun setGoodDay() {
        with(binding) {
            layoutApp.backRes(R.color.colorWhite)
            imageLogo.imageRes(R.drawable.ic_logo_black)

            textStatus.textColor(R.color.colorGray500)
            textStatus.textSize = 16f
            textStatus.text = getString(R.string.marked_as_good_day)

            layoutUnmark.beGone()

            textQuote.textColor(R.color.colorDarkBlack)
            viewMark.backRes(R.color.colorDarkBlack)
            textAuthor.textColor(R.color.colorDarkBlack)

            textQuote.text = getString(R.string.quote_good_day)
            textAuthor.text = getString(R.string.author_good_day)

            layoutMark.beVisible()

            textViewCalendar.textColor(R.color.colorDarkBlack)
            viewCalendar.backRes(R.color.colorDarkBlack)
        }
    }

    private fun setBadDay() {
        with(binding) {
            layoutApp.backRes(R.color.colorDarkBlack)
            imageLogo.imageRes(R.drawable.ic_logo_white)

            textStatus.textColor(R.color.colorGray500)
            textStatus.textSize = 16f
            textStatus.text = getString(R.string.marked_as_bad_day)

            layoutUnmark.beGone()

            textQuote.textColor(R.color.colorWhite)
            viewMark.backRes(R.color.colorWhite)
            textAuthor.textColor(R.color.colorWhite)

            textQuote.text = getString(R.string.quote_bad_day)
            textAuthor.text = getString(R.string.author_bad_day)

            layoutMark.beVisible()

            textViewCalendar.textColor(R.color.colorWhite)
            viewCalendar.backRes(R.color.colorWhite)
        }
    }

    private fun setUnmark() {
        with(binding) {
            layoutApp.backRes(R.color.colorBlack)
            imageLogo.imageRes(R.drawable.ic_logo_white)

            textStatus.textColor(R.color.colorWhite)
            textStatus.textSize = 20f
            textStatus.text = getString(R.string.how_was_your_day_today)

            layoutMark.beGone()
            layoutUnmark.beVisible()

            textViewCalendar.textColor(R.color.colorWhite)
            viewCalendar.backRes(R.color.colorWhite)
        }
    }

    private fun navigateToCalendar() {

    }
}