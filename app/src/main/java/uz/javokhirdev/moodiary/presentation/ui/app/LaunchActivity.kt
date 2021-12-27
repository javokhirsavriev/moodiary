package uz.javokhirdev.moodiary.presentation.ui.app

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import uz.javokhirdev.moodiary.R
import uz.javokhirdev.moodiary.databinding.ActivityLaunchBinding
import uz.javokhirdev.moodiary.utils.start

@AndroidEntryPoint
class LaunchActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLaunchBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        scaleAnimate()

        lifecycleScope.launchWhenStarted {
            delay(1500L)

            start(AppActivity::class.java)
            finish()
        }
    }

    private fun scaleAnimate() {
        val anim = AnimationUtils.loadAnimation(this, R.anim.scale_animation)
        binding.imageLogo.startAnimation(anim)
    }
}