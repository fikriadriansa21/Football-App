package com.fikriadriansa.footballschedule.presenter

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
    fun testGetLastMatch(){
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)


        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getLastMatch()).await(),
                EventResponse::class.java
            )).thenReturn(response)

            presenter.getListLastMatch()

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showListMatch(events)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun testGetNextMatch(){
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)


        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getNextMatch()).await(),
                EventResponse::class.java
            )).thenReturn(response)

            presenter.getListNextMatch()

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showListMatch(events)
            Mockito.verify(view).hideLoading()
        }
    }
}