package study.project.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import study.project.R
import study.project.adapters.MainViewPagerAdapter
import study.project.databinding.ActivityTabbedBinding

class TabbedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabbed)
        val binding = ActivityTabbedBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())

        val sectionsPagerAdapter = MainViewPagerAdapter(
            this,
            supportFragmentManager
        )
        val viewPager: ViewPager = binding.viewPager
//        ViewPager viewPager = findViewById(R.id.view_pager);
        //        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.adapter = sectionsPagerAdapter

        fun ExercisePage(view: View) {
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }
    }
}