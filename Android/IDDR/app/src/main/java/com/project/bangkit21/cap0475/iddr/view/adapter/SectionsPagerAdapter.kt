package com.project.bangkit21.cap0475.iddr.view.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.project.bangkit21.cap0475.iddr.R
import com.project.bangkit21.cap0475.iddr.view.fragment.ProfileDetailFragment
import com.project.bangkit21.cap0475.iddr.view.fragment.ReportedFragment


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.profiledetail, R.string.reported)
    }

    override fun getItem(position: Int): Fragment =
            when (position) {
                0 -> ProfileDetailFragment()
                1 -> ReportedFragment()
                else -> Fragment()
            }

    override fun getPageTitle(position: Int): CharSequence = mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = TAB_TITLES.size

}