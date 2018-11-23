package com.divisicodelabs.footballschedule.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.divisicodelabs.footballschedule.fragments.LastMatchFragment
import com.divisicodelabs.footballschedule.fragments.NextMatchFragment

class EventPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                LastMatchFragment()
            }else -> {
                NextMatchFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0 -> "Last Match"
            else -> "Next Match"
        }
    }

}