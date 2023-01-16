package study.project.adapters

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.viewpager.widget.PagerAdapter
import study.project.databinding.OnboardingSliderBinding


class OnBoardingViewPagerAdapter(val context: Context, val pagesList: ArrayList<String>) : PagerAdapter() {

    private val binding : OnboardingSliderBinding = OnboardingSliderBinding.inflate(LayoutInflater.from(context))

    override fun getCount(): Int {
        return pagesList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        binding.tvTitle.text = pagesList[position]
        container.addView(binding.root)

        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }
}
