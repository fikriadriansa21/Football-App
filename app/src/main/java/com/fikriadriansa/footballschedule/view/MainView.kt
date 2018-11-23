package com.fikriadriansa.footballschedule.view

import android.util.EventLog
import com.fikriadriansa.footballschedule.model.Event

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showListMatch(data: List<Event>)
}