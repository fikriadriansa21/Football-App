package com.fikriadriansa.footballschedule.view

import com.fikriadriansa.footballschedule.model.Event
import com.fikriadriansa.footballschedule.model.TeamDetail

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showListMatch(data: List<Event>)
}