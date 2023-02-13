package study.project.activities

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Process
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.viewpager.widget.ViewPager
import study.project.R
import study.project.adapters.OnBoardingViewPagerAdapter
import study.project.databinding.ActivityOnBoardBinding
import study.project.models.OnboardPresentation


class OnBoardActivity : AppCompatActivity(){

    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }

    private lateinit var binding: ActivityOnBoardBinding

    private lateinit var pages : ArrayList<OnboardPresentation>
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        super.onCreate(savedInstanceState)
        if (!sharedPreferences.getBoolean("first_time", true)) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        binding = ActivityOnBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        pages = arrayListOf(
            OnboardPresentation(resources.getString(R.string.onboarding_welcome_title),resources.getStringArray(R.array.onboarding_welcome).joinToString("\n \n")),
            OnboardPresentation(resources.getString(R.string.onboarding_terms_and_conditions_title),resources.getStringArray(R.array.onboarding_terms_and_conditions).joinToString("\n \n")),
            OnboardPresentation(resources.getString(R.string.onboarding_usage_title),resources.getStringArray(R.array.onboarding_usage).joinToString("\n \n")),
            OnboardPresentation(resources.getString(R.string.onboarding_authors_title),resources.getStringArray(R.array.onboarding_authors).joinToString("\n \n")),
        )

        binding.apply {
            val adapter = OnBoardingViewPagerAdapter(this@OnBoardActivity, pages)
            vpOnboarding.adapter = adapter
            wormDotsIndicator.attachTo(vpOnboarding)
        }

        setListeners()
    }

    private fun setListeners(){
        binding.apply {
            vpOnboarding.addOnPageChangeListener(
                object : ViewPager.OnPageChangeListener {
                    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
                    override fun onPageSelected(position: Int) {
                        setButtonVisibility(pages.size, position)
                    }
                    override fun onPageScrollStateChanged(state: Int) {}
                }
            )
            btnContinue.setOnClickListener {
                vpOnboarding.currentItem = vpOnboarding.currentItem + 1
            }

            btnFinish.setOnClickListener {
                goToLogin()
            }
        }
    }

    private fun setButtonVisibility(size: Int, pos: Int) {
        binding.btnContinue.isVisible = pos != size - 1
        binding.btnFinish.isVisible = pos == size - 1
    }

    private fun closeAppDialog() {
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
            closeAppDialog()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun goToLogin() {
        sharedPreferences.edit().putBoolean("first_time", false).apply()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

}