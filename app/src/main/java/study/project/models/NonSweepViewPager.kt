package study.project.models

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager


class NonSweepViewPager : ViewPager {

    constructor(context: Context) : super(context) {
        // Your code here
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        // Your code here
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return false
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return false
    }
}