package study.project.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import study.project.R
import study.project.databinding.OnboardingSliderBinding
import study.project.models.OnboardPresentation


class OnBoardingViewPagerAdapter(val context: Context, private val pagesList: ArrayList<OnboardPresentation>) : PagerAdapter() {
    override fun getCount(): Int = pagesList.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view === `object` as LinearLayout

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.onboarding_slider, container, false)
        val binding = OnboardingSliderBinding.bind(view)
        binding.tvDescription.text = pagesList[position].description
        binding.tvTitle.text = pagesList[position].title
        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}

