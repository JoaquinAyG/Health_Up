package study.project.activities

import android.app.ActionBar.LayoutParams
import android.app.AlertDialog
import android.content.DialogInterface
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

    lateinit var llSliderDots: LinearLayout
    lateinit var btnContinue: Button
    lateinit var btnFinish: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        //no action bar
        supportActionBar?.hide();

        val vpOnboarding = findViewById<ViewPager>(R.id.vp_onboarding)
        btnContinue = findViewById(R.id.btn_continue)
        btnFinish = findViewById(R.id.btn_finish)
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
                setButtonVisibility(pages.size, position)
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        }
        )

        setDots(pages.size, 0)

        btnContinue.setOnClickListener {
            vpOnboarding.currentItem = vpOnboarding.currentItem + 1
        }

        btnFinish.setOnClickListener{
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
    private fun cerrarAplicacion() {
        AlertDialog.Builder(this)
            .setIcon(R.drawable.logo)
            .setTitle("Are you sure you want to exit?")
            .setCancelable(false)
            .setNegativeButton("Cancel", null)
            .setPositiveButton(
                "exit",
                DialogInterface.OnClickListener { dialog, which ->

                    Process.killProcess(Process.myPid())   }).show()

    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            cerrarAplicacion()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}