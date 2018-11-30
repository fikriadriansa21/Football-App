package com.fikriadriansa.footballschedule.presenter

import com.fikriadriansa.footballschedule.api.ApiRepository
import com.fikriadriansa.footballschedule.api.TheSportDBApi
import com.fikriadriansa.footballschedule.model.EventResponse
import com.fikriadriansa.footballschedule.utils.CoroutineContextProvider
import com.fikriadriansa.footballschedule.view.MainView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson,
                    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {


    fun getListLastMatch(id: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getLastMatch(id)),
                EventResponse::class.java
            )

            view.hideLoading()
            view.showListMatch(data.events)

        }
    }


    fun getListNextMatch(id: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getNextMatch(id)),
                EventResponse::class.java
            )

                view.hideLoading()
                view.showListMatch(data.events)
        }
    }


}