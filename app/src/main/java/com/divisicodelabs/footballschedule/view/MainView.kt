package com.divisicodelabs.footballschedule.view

import android.util.EventLog
import com.divisicodelabs.footballschedule.model.Event

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showListMatch(data: List<Event>)
}