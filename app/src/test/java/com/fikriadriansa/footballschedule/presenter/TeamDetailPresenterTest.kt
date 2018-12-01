package com.fikriadriansa.footballschedule.presenter

import com.fikriadriansa.footballschedule.TestContextProvider
import com.fikriadriansa.footballschedule.api.ApiRepository
import com.fikriadriansa.footballschedule.api.TheSportDBApi
import com.fikriadriansa.footballschedule.model.TeamDetail
import com.fikriadriansa.footballschedule.model.TeamDetailResponse
import com.fikriadriansa.footballschedule.view.TeamDetailView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamDetailPresenterTest {
    @Mock
    private lateinit var view: TeamDetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var presenter: TeamDetailPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = TeamDetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetDetailHome(){
        val detail: MutableList<TeamDetail> = mutableListOf()
        val response = TeamDetailResponse(detail)
        val idEvent = "441613"


        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getDetailTeam(idEvent)).await(),
                    TeamDetailResponse::class.java
                )
            ).thenReturn(response)

            presenter.getDetailTeamHome(idEvent)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showDetailHomeMatch(detail)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun testGetDetailAway(){
        val detail: MutableList<TeamDetail> = mutableListOf()
        val response = TeamDetailResponse(detail)
        val idEvent = "441613"

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getDetailTeam(idEvent)).await(),
                    TeamDetailResponse::class.java
                )
            ).thenReturn(response)

            presenter.getDetailTeamHome(idEvent)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showDetailAwayMatch(detail)
            Mockito.verify(view).hideLoading()
        }
    }
}