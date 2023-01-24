package study.project.activities

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager.widget.ViewPager
import study.project.R
import study.project.activities.ui.home.HomeFragment
import study.project.databinding.ActivityRegisterBinding

class RegisterActivity : FragmentActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        binding.vpRegister.adapter = pagerAdapter
    }

    override fun onBackPressed() {
        if (binding.vpRegister.currentItem == 0) {
            super.onBackPressed()
        } else {
            binding.vpRegister.currentItem = binding.vpRegister.currentItem - 1
        }
    }
    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = 2

        override fun getItem(position: Int): Fragment = HomeFragment()
    }
}