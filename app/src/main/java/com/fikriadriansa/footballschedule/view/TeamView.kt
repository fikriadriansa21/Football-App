package com.fikriadriansa.footballschedule.view

import com.fikriadriansa.footballschedule.model.Team

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}