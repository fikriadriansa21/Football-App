package com.fikriadriansa.footballschedule.view

import com.fikriadriansa.footballschedule.model.Team
import com.fikriadriansa.footballschedule.model.TeamDetail

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showDetailHomeMatch(data: List<TeamDetail>)
    fun showDetailAwayMatch(data: List<TeamDetail>)
    fun showTeamDetail(data: List<Team>)
}