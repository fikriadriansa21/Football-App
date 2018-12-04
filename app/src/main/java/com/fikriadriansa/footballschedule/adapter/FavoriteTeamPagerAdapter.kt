package com.fikriadriansa.footballschedule.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.fikriadriansa.footballschedule.fragments.FavoriteFragment
import com.fikriadriansa.footballschedule.fragments.FavoriteTeamFragment

class FavoriteTeamPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                FavoriteFragment()
            }else -> {
                FavoriteTeamFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0 -> "EVENTS"
            else -> "TEAMS"
        }
    }

}