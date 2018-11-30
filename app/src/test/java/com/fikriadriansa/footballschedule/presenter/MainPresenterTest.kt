package com.fikriadriansa.footballschedule.presenter

import android.util.EventLog
import com.fikriadriansa.footballschedule.TestContextProvider
import com.fikriadriansa.footballschedule.api.ApiRepository
import com.fikriadriansa.footballschedule.api.TheSportDBApi
import com.fikriadriansa.footballschedule.model.Event
import com.fikriadriansa.footballschedule.model.EventResponse
import com.fikriadriansa.footballschedule.view.MainView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MainPresenterTest{
    @Mock
    private lateinit var view: MainView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var presenter: MainPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetLastMatch(id: String?){
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)
        val id = "4328"

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getLastMatch(id)),
                EventResponse::class.java
            )).thenReturn(response)

            presenter.getListLastMatch(id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showListMatch(events)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun testGetNextMatch(id: String?){
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)
        val id = "4328"

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getNextMatch(id)),
                EventResponse::class.java
            )).thenReturn(response)

            presenter.getListNextMatch(id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showListMatch(events)
            Mockito.verify(view).hideLoading()
        }
    }
}