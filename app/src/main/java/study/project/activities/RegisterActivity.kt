package study.project.activities

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import study.project.activities.ui.register.CapableDaysFragment
import study.project.activities.ui.register.CheckFragment
import study.project.adapters.RegisterViewPageAdapter
import study.project.databinding.ActivityRegisterBinding

class RegisterActivity : FragmentActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        val fragments = listOf(
            //Insert here the fragments in order pls
            CapableDaysFragment(),
            CheckFragment(),
            CheckFragment(),
            CheckFragment(),
            CheckFragment(),
            CheckFragment(),

        )
        val adapter = RegisterViewPageAdapter(supportFragmentManager, fragments)

        binding.apply {
            vpRegister.adapter = adapter
            tabLayout.setupWithViewPager(vpRegister)
            btnNext.setOnClickListener{
                vpRegister.currentItem = vpRegister.currentItem + 1
            }
            progressBar.progress = 100 / vpRegister.adapter!!.count
            vpRegister.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                }

                override fun onPageSelected(position: Int) {
                    progressBar.progress = (position + 1) * 100 / vpRegister.adapter!!.count
                }

                override fun onPageScrollStateChanged(state: Int) {
                }
            })
            binding.btnBack.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (binding.vpRegister.currentItem == 0) {
                finish()
            } else {
                binding.vpRegister.currentItem = binding.vpRegister.currentItem - 1
            }
        }
    }
}