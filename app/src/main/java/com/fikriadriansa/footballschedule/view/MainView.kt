package com.fikriadriansa.footballschedule.view

import com.fikriadriansa.footballschedule.model.Event

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showListMatch(data: List<Event>)
    fun showSearchMatch(data: List<Event>)

}