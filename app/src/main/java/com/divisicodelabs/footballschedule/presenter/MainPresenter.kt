package com.divisicodelabs.footballschedule.presenter

import android.util.Log
import com.divisicodelabs.footballschedule.api.ApiRepository
import com.divisicodelabs.footballschedule.api.TheSportDBApi
import com.divisicodelabs.footballschedule.model.EventResponse
import com.divisicodelabs.footballschedule.view.MainView
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