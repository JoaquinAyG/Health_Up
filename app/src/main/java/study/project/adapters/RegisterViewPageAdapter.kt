package study.project.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import study.project.fragments.register.RegisterFragmentBase

class RegisterViewPageAdapter(fm: FragmentManager, private val fragments: List<Fragment>) :
    FragmentPagerAdapter(fm) {
    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence {
        return ""
    }

    fun getFragment(position: Int): RegisterFragmentBase {
        return fragments[position] as RegisterFragmentBase
    }
}