package com.fikriadriansa.footballschedule.presenter

import com.fikriadriansa.footballschedule.api.ApiRepository
import com.fikriadriansa.footballschedule.api.TheSportDBApi
import com.fikriadriansa.footballschedule.model.TeamDetailResponse
import com.fikriadriansa.footballschedule.view.TeamDetailView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamDetailPresenter(private val view: TeamDetailView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson
)  {
    fun getDetailTeamHome(idEvent: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getDetailTeam(idEvent)),
                TeamDetailResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showDetailHomeMatch(data.teams)
            }
        }
    }

    fun getDetailTeamAway(idEvent: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getDetailTeam(idEvent)),
                TeamDetailResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showDetailAwayMatch(data.teams)
            }
        }
    }


}