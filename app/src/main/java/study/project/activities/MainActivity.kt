package study.project.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import study.project.R
import study.project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())

        val sectionsPagerAdapter = SectionsPagerAdapter(
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