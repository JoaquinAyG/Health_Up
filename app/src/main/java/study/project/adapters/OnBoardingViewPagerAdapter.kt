package study.project.adapters


import study.project.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter


class OnBoardingViewPagerAdapter(val context: Context, private val pagesList: ArrayList<String>) : PagerAdapter() {

    private var layoutInflater: LayoutInflater? = null

    override fun getCount(): Int {
        return pagesList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater!!.inflate(R.layout.onboarding_slider, container, false)
        val textview: TextView = view.findViewById(R.id.tvTitle)
        textview.text = pagesList[position]
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
