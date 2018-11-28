package com.fikriadriansa.footballschedule.view

import com.fikriadriansa.footballschedule.model.TeamDetail

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showDetailHomeMatch(data: List<TeamDetail>)
    fun showDetailAwayMatch(data: List<TeamDetail>)

}