package com.fikriadriansa.footballschedule.view

import com.fikriadriansa.footballschedule.model.Event
import com.fikriadriansa.footballschedule.model.Player

interface PlayerView {
    fun showLoading()
    fun hideLoading()
    fun showListPlayer(data: List<Player>)
}