package com.fikriadriansa.footballschedule.presenter

import com.fikriadriansa.footballschedule.api.ApiRepository
import com.fikriadriansa.footballschedule.api.TheSportDBApi
import com.fikriadriansa.footballschedule.model.PlayerResponse
import com.fikriadriansa.footballschedule.model.TeamResponse
import com.fikriadriansa.footballschedule.view.PlayerView
import com.fikriadriansa.footballschedule.view.TeamView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchPresenter (private val view: TeamView,
                       private val view2: PlayerView,
                       private val apiRepository: ApiRepository,
                       private val gson: Gson
) {

    fun getSearchTeams(teamName: String?) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getSearchTeam(teamName)).await(),
                TeamResponse::class.java
            )

            view.showTeamList(data.teams)
            view.hideLoading()

        }
    }

    fun getSearchPlayers(playerName: String?) {
        view2.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getSearchTeam(playerName)).await(),
                PlayerResponse::class.java
            )

            view2.showListPlayer(data.player)
            view2.hideLoading()

        }
    }

}