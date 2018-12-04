package com.fikriadriansa.footballschedule.main

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fikriadriansa.footballschedule.R
import com.fikriadriansa.footballschedule.fragments.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadEventFragment(savedInstanceState)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.event -> {
                    loadEventFragment(savedInstanceState)
                }
                R.id.team -> {
                    loadTeamFragment(savedInstanceState)
                }
                R.id.favorite -> {
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }

    }

    private fun loadTeamFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, TeamFragment(), TeamFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadEventFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, EventFragment(), EventFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, MainFavoriteFragment(), MainFavoriteFragment::class.java.simpleName)
                .commit()
        }
    }
}
