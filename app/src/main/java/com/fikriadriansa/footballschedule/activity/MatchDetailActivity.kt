package com.fikriadriansa.footballschedule.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.fikriadriansa.footballschedule.R
import com.fikriadriansa.footballschedule.adapter.EventAdapter
import com.fikriadriansa.footballschedule.api.ApiRepository
import com.fikriadriansa.footballschedule.model.Event
import com.fikriadriansa.footballschedule.model.TeamDetail
import com.fikriadriansa.footballschedule.presenter.MainPresenter
import com.fikriadriansa.footballschedule.presenter.TeamDetailPresenter
import com.fikriadriansa.footballschedule.view.MainView
import com.fikriadriansa.footballschedule.view.TeamDetailView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_event_detail.*
import kotlinx.android.synthetic.main.fragment_last_match.*
import kotlinx.android.synthetic.main.item_event_match.*

class MatchDetailActivity : AppCompatActivity(), TeamDetailView{

    private var events: MutableList<Event> = mutableListOf()
    private var teamDetail: MutableList<TeamDetail> = mutableListOf()
    private lateinit var presenter: TeamDetailPresenter
    private lateinit var idHomeTeam: String
    private lateinit var idAwayTeam: String
    private lateinit var TeamIdEvent: String

    companion object {
        lateinit var getDataEvent: Event
        lateinit var getDataDetail: TeamDetail
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)
        getDataEvent = intent.getParcelableExtra<Event>("data")

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getDetailTeamHome(getDataEvent.idHomeTeam)
        presenter.getDetailTeamAway(getDataEvent.idAwayTeam)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {
    }


    override fun showDetailHomeMatch(data: List<TeamDetail>) {
        teamDetail.clear()
        teamDetail.addAll(data)

        Picasso.get().load(data[0].strTeamBadge).into(img_home_team)
        tv_team_home_detail.text = getDataEvent.strHomeTeam
        tv_date_detail.text = getDataEvent.dateEvent
        tv_score_home_detail.text = getDataEvent.intHomeScore
        tv_goals_detail_home.text = getDataEvent.strHomeGoalDetails.toString().replace(";","\n")
        tv_home_gk.text = getDataEvent.strHomeLineupGoalkeeper.toString().replace(";","\n")
        tv_home_def.text = getDataEvent.strHomeLineupDefense.toString().replace(";","\n")
        tv_home_mid.text = getDataEvent.strHomeLineupMidfield.toString().replace(";","\n")
        tv_home_fwd.text = getDataEvent.strHomeLineupForward.toString().replace(";","\n")
        tv_home_sub.text = getDataEvent.strHomeLineupSubstitutes.toString().replace(";","\n")

    }

    override fun showDetailAwayMatch(data: List<TeamDetail>) {
        teamDetail.clear()
        teamDetail.addAll(data)
        Picasso.get().load(data[0].strTeamBadge).into(img_away_team)
        tv_team_away_detail.text = getDataEvent.strAwayTeam
        tv_score_away_detail.text = getDataEvent.intAwayScore
        tv_goals_detail_away.text = getDataEvent.strAwayGoalDetails.toString().replace(";","\n")
        tv_away_gk.text = getDataEvent.strAwayLineupGoalkeeper.toString().replace(";","\n")
        tv_away_def.text = getDataEvent.strAwayLineupDefense.toString().replace(";","\n")
        tv_away_mid.text = getDataEvent.strAwayLineupMidfield.toString().replace(";","\n")
        tv_away_fwd.text = getDataEvent.strAwayLineupForward.toString().replace(";","\n")
        tv_away_sub.text = getDataEvent.strAwayLineupSubstitutes.toString().replace(";","\n")
    }



}
