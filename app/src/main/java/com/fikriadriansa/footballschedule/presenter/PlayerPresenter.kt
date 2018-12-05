package com.fikriadriansa.footballschedule.presenter


import com.fikriadriansa.footballschedule.api.ApiRepository
import com.fikriadriansa.footballschedule.api.TheSportDBApi
import com.fikriadriansa.footballschedule.model.PlayerResponse
import com.fikriadriansa.footballschedule.view.PlayerView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerPresenter(private val view: PlayerView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson
) {

    fun getListPlayer(teamName: String?) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getPlayer(teamName)).await(),
                PlayerResponse::class.java
            )
            view.showListPlayer(data.player)
            view.hideLoading()

        }
    }

}