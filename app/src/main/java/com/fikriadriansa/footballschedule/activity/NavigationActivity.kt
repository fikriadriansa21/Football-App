package com.fikriadriansa.footballschedule.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fikriadriansa.footballschedule.R
import com.fikriadriansa.footballschedule.R.id.tab_events
import com.fikriadriansa.footballschedule.R.id.view_pager_events
import com.fikriadriansa.footballschedule.adapter.EventPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*


class NavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

    }
}
