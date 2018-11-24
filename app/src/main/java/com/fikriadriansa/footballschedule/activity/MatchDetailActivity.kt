package com.fikriadriansa.footballschedule.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.fikriadriansa.footballschedule.R
import com.fikriadriansa.footballschedule.adapter.MatchDetailAdapter
import com.fikriadriansa.footballschedule.api.ApiRepository
import com.fikriadriansa.footballschedule.model.Event
import com.fikriadriansa.footballschedule.model.TeamDetail
import com.fikriadriansa.footballschedule.presenter.MainPresenter
import com.fikriadriansa.footballschedule.view.MainView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_last_match.*

class MatchDetailActivity : AppCompatActivity(), MainView {

    private var events: MutableList<Event> = mutableListOf()
    private var teamDetail: MutableList<TeamDetail> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapterMatchDetail: MatchDetailAdapter

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showListMatch(data: List<Event>) {
        events.clear()
        events.addAll(data)
        adapterMatchDetail.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_last_match_detail)

        adapterMatchDetail = MatchDetailAdapter(events,teamDetail)


        val dateEvent: String? = intent.getStringExtra("date")
        Log.i("Cek data",dateEvent)



    }
}
