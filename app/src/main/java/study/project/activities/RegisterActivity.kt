package study.project.activities

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import study.project.fragments.register.CapableDaysFragment
import study.project.fragments.register.CheckFragment
import study.project.adapters.RegisterViewPageAdapter
import study.project.databinding.ActivityRegisterBinding
import study.project.fragments.register.AgeFragment
import study.project.fragments.register.GenderFragment
import study.project.fragments.register.HeightFragment
import study.project.fragments.register.MailFragment
import study.project.fragments.register.NameFragment
import study.project.fragments.register.PasswordFragment
import study.project.fragments.register.WeightFragment
import study.project.models.NonSweepViewPager

class RegisterActivity : FragmentActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        val fragments = listOf(
            //Insert here the fragments in order pls
            GenderFragment(),
            AgeFragment(),
            WeightFragment(),
            HeightFragment(),
            NameFragment(),
            MailFragment(),
            CapableDaysFragment(),
            CheckFragment(),
            PasswordFragment()
        )
        val adapter = RegisterViewPageAdapter(supportFragmentManager, fragments)

        binding.apply {
            vpRegister.adapter = adapter
            tabLayout.setupWithViewPager(vpRegister)

            btnNext.setOnClickListener{
                if(adapter.getFragment(vpRegister.currentItem).commitChanges()) {
                    vpRegister.currentItem = vpRegister.currentItem + 1
                }
            }

            progressBar.progress = 100 / vpRegister.adapter!!.count
            vpRegister.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
                override fun onPageSelected(position: Int) {
                    progressBar.progress = (position + 1) * 100 / vpRegister.adapter!!.count
                }
                override fun onPageScrollStateChanged(state: Int) {}
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