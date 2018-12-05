package com.fikriadriansa.footballschedule.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.fikriadriansa.footballschedule.fragments.PlayerFragment
import com.fikriadriansa.footballschedule.fragments.TeamOverviewFragment

class TeamPagerAdapter(fm: FragmentManager, teamName: String): FragmentPagerAdapter(fm) {
    val teamNames = teamName
//    val description = description

    override fun getItem(position: Int): Fragment {

        return when(position){
            0 -> {
                TeamOverviewFragment()
            }else -> {
                PlayerFragment.newInstance(teamNames)
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0 -> "OVERVIEW"
            else -> "PLAYERS"
        }
    }

}