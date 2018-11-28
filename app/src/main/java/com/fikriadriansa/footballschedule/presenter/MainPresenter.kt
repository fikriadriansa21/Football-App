package com.fikriadriansa.footballschedule.presenter

import com.fikriadriansa.footballschedule.api.ApiRepository
import com.fikriadriansa.footballschedule.api.TheSportDBApi
import com.fikriadriansa.footballschedule.model.EventResponse
import com.fikriadriansa.footballschedule.view.MainView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson) {

    fun getListLastMatch() {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getLastMatch()),
                EventResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showListMatch(data.events)
            }
        }
    }


    fun getListNextMatch() {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getNextMatch()),
                EventResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showListMatch(data.events)
            }
        }
    }


}