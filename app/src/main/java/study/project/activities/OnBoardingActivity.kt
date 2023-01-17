package study.project.activities

import android.app.ActionBar.LayoutParams
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import study.project.R
import study.project.adapters.OnBoardingViewPagerAdapter


class OnBoardingActivity : AppCompatActivity() {

    lateinit var llSliderDots: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        val vpOnboarding = findViewById<ViewPager>(R.id.vp_onboarding)
        val btnContinue = findViewById<Button>(R.id.btn_continue)
        llSliderDots = findViewById(R.id.llSliderDots)

        val pages = arrayListOf("Page 1", "Page 2", "Page 3", "Page 4")

        val adapter = OnBoardingViewPagerAdapter(this@OnBoardingActivity, pages)
        vpOnboarding.adapter = adapter

        vpOnboarding.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                setDots(pages.size, position)
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        }
        )

        setDots(pages.size, 0)

        btnContinue.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun setDots(size: Int, pos: Int){
        llSliderDots.removeAllViews()
        for (i in 0 until size) {
            val iv = ImageView(this)
            if (i == pos) {
                iv.setImageResource(R.drawable.dot_selected)
            } else {
                iv.setImageResource(R.drawable.dot_empty)
            }
            val params = LayoutParams(40, 40)
            if (i != 0)
                params.marginStart = 80
            iv.layoutParams = params
            llSliderDots.addView(iv)
        }
    }

}