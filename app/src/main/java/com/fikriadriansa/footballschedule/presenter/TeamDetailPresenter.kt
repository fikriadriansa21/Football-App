package com.fikriadriansa.footballschedule.presenter

import com.fikriadriansa.footballschedule.api.ApiRepository
import com.fikriadriansa.footballschedule.api.TheSportDBApi
import com.fikriadriansa.footballschedule.model.TeamDetailResponse
import com.fikriadriansa.footballschedule.utils.CoroutineContextProvider
import com.fikriadriansa.footballschedule.view.TeamDetailView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class TeamDetailPresenter(private val view: TeamDetailView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson,
                          private val context: CoroutineContextProvider = CoroutineContextProvider()
)  {

    fun getDetailTeamHome(idEvent: String?) {
        view.showLoading()
        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getDetailTeam(idEvent)).await(),
                TeamDetailResponse::class.java
            )


                view.showDetailHomeMatch(data.teams)
                view.hideLoading()


        }
    }

    fun getDetailTeamAway(idEvent: String?) {
        view.showLoading()
        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getDetailTeam(idEvent)).await(),
                TeamDetailResponse::class.java
            )


                view.showDetailAwayMatch(data.teams)
                view.hideLoading()

        }
    }
 }


