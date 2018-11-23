package com.divisicodelabs.footballschedule.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.divisicodelabs.footballschedule.R
import com.divisicodelabs.footballschedule.adapter.EventPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentAdapter = EventPagerAdapter(supportFragmentManager)
        viewpager_event.adapter = fragmentAdapter

        tab_event.setupWithViewPager(viewpager_event)
    }
}
