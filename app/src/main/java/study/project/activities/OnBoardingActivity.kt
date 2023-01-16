package study.project.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import study.project.R
import study.project.adapters.OnBoardingViewPagerAdapter
import study.project.databinding.ActivityOnBoardingBinding


class OnBoardingActivity : AppCompatActivity() {

    private val binding = ActivityOnBoardingBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        val pages = arrayListOf("Page 1", "Page 2", "Page 3")

        binding.vpOnboarding.adapter = OnBoardingViewPagerAdapter(binding.root.context, pages)

        setDots(pages.size)

        binding.btnContinue.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun setDots(size: Int){
        binding.llSliderDots.removeAllViews()
        for (i in 0 until size){
            val iv = ImageView(this)
            iv.setImageDrawable(getDrawable(R.drawable.dot_empty))
            binding.llSliderDots.addView(iv)
        }
    }


}