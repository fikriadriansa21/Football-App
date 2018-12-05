package com.fikriadriansa.footballschedule.presenter

import com.fikriadriansa.footballschedule.api.ApiRepository
import com.fikriadriansa.footballschedule.api.TheSportDBApi
import com.fikriadriansa.footballschedule.model.EventResponse
import com.fikriadriansa.footballschedule.model.SearchEventResponse
import com.fikriadriansa.footballschedule.utils.CoroutineContextProvider
import com.fikriadriansa.footballschedule.view.MainView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson,
                    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {


    fun getListLastMatch(leagueId: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getLastMatch(leagueId!!)).await(),
                EventResponse::class.java
            )

            view.showListMatch(data.events)
            view.hideLoading()

        }
    }


    fun getListNextMatch(leagueId: String?) {
        view.showLoading()
        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getNextMatch(leagueId)).await(),
                EventResponse::class.java
            )

            view.showListMatch(data.events)
            view.hideLoading()

        }
    }

    fun getSearchMatch(eventName: String?) {
        view.showLoading()
        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getSearchEvents(eventName)).await(),
                SearchEventResponse::class.java
            )

            view.showSearchMatch(data.event)
            view.hideLoading()

        }
    }
}