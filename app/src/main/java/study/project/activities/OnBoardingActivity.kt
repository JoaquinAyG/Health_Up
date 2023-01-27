package study.project.activities

import android.app.ActionBar.LayoutParams
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.view.KeyEvent
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import study.project.R
import study.project.adapters.OnBoardingViewPagerAdapter


class OnBoardingActivity : AppCompatActivity() {

    private lateinit var llSliderDots: LinearLayout
    private lateinit var btnContinue: Button
    private lateinit var btnFinish: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        //no action bar
        supportActionBar?.hide()

        val vpOnboard = findViewById<ViewPager>(R.id.vp_onboarding)
        btnContinue = findViewById(R.id.btn_continue)
        btnFinish = findViewById(R.id.btn_finish)
        llSliderDots = findViewById(R.id.llSliderDots)

        val pages = arrayListOf(
            resources.getStringArray(R.array.onboarding_welcome).joinToString("\n \n"),
            resources.getStringArray(R.array.onboarding_terms_and_conditions).joinToString("\n \n"),
            resources.getStringArray(R.array.onboarding_usage).joinToString("\n \n"),
            resources.getStringArray(R.array.onboarding_authors).joinToString("\n \n"),
        )

        val adapter = OnBoardingViewPagerAdapter(this@OnBoardingActivity, pages)
        vpOnboard.adapter = adapter

        vpOnboard.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                setDots(pages.size, position)
                setButtonVisibility(pages.size, position)
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        }
        )

        setDots(pages.size, 0)

        btnContinue.setOnClickListener {
            vpOnboard.currentItem = vpOnboard.currentItem + 1
        }

        btnFinish.setOnClickListener {
            startActivity(Intent(this@OnBoardingActivity, LoginActivity::class.java))
            finish()
        }

        //TODO(Set activity to only show once)
    }

    private fun setButtonVisibility(size: Int, pos: Int) {
        if (pos == size - 1) {
            btnContinue.visibility = Button.GONE
            btnFinish.visibility = Button.VISIBLE
        } else {
            btnContinue.visibility = Button.VISIBLE
            btnFinish.visibility = Button.GONE
        }
    }

    private fun setDots(size: Int, pos: Int) {
        llSliderDots.removeAllViews()
        for (i in 0 until size) {
            val iv = ImageView(this)
            if (i == pos) {
                iv.setImageResource(R.drawable.dot_selected)
            } else {
                iv.setImageResource(R.drawable.dot_empty)
            }
            val params = LayoutParams(20, 20)
            if (i != 0)
                params.marginStart = 80
            iv.layoutParams = params
            llSliderDots.addView(iv)
        }
    }

    private fun cerrarAplicacion() {
        AlertDialog.Builder(this)
            .setIcon(R.drawable.logo)
            .setTitle("Are you sure you want to exit?")
            .setCancelable(false)
            .setNegativeButton("Cancel", null)
            .setPositiveButton(
                "exit"
            ) { _, _ ->
                Process.killProcess(Process.myPid())
            }.show()

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            cerrarAplicacion()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}