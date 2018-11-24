package com.fikriadriansa.footballschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.fikriadriansa.footballschedule.R
import com.fikriadriansa.footballschedule.model.Event
import com.fikriadriansa.footballschedule.model.TeamDetail
import com.squareup.picasso.Picasso

class MatchDetailAdapter(private val events: List<Event>,private val teamDetail: List<TeamDetail>): RecyclerView.Adapter<DetailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): DetailViewHolder {
        return DetailViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.activity_last_match_detail,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bindItems(events[position],teamDetail[position])
    }
}

class DetailViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val dateEvent: TextView = view.findViewById(R.id.tv_date_detail)
    private val imgHomeTeam: ImageView = view.findViewById(R.id.img_home_team)
    private val tvHomeTeam: TextView = view.findViewById(R.id.tv_team_home_detail)
    private val imgAwayTeam: ImageView = view.findViewById(R.id.img_away_team)
    private val tvAwayTeam: TextView = view.findViewById(R.id.tv_team_away_detail)
    private val tvScoreHome: TextView = view.findViewById(R.id.tv_score_home_detail)
    private val tvScoreAway: TextView = view.findViewById(R.id.tv_score_away_detail)
    private val tvGoalDetailsHome: TextView = view.findViewById(R.id.tv_goals_detail_home)
    private val tvGoalDetailsAway: TextView = view.findViewById(R.id.tv_goals_detail_away)
    private val tvShotsHome: TextView = view.findViewById(R.id.tv_home_shots)
    private val tvShotsAway: TextView = view.findViewById(R.id.tv_away_shots)
    private val tvHomeGK: TextView = view.findViewById(R.id.tv_home_gk)
    private val tvAwayGK: TextView = view.findViewById(R.id.tv_away_gk)
    private val tvHomeDef: TextView = view.findViewById(R.id.tv_home_def)
    private val tvAwayDef: TextView = view.findViewById(R.id.tv_away_def)
    private val tvHomeMid: TextView = view.findViewById(R.id.tv_home_mid)
    private val tvAwayMid: TextView = view.findViewById(R.id.tv_away_mid)
    private val tvHomeFwd: TextView = view.findViewById(R.id.tv_home_fwd)
    private val tvAwayFwd: TextView = view.findViewById(R.id.tv_away_fwd)
    private val tvHomeSub: TextView = view.findViewById(R.id.tv_home_sub)
    private val tvAwaySub: TextView = view.findViewById(R.id.tv_away_sub)


    fun bindItems(event: Event, teamDetail: TeamDetail){
        dateEvent.text = event.dateEvent
        Picasso.get().load(teamDetail.strTeamBadge).into(imgHomeTeam)
        tvHomeTeam.text = event.strHomeTeam
        Picasso.get().load(teamDetail.strTeamBadge).into(imgAwayTeam)
        tvAwayTeam.text = event.strAwayTeam
        tvScoreHome.text = event.intHomeScore
        tvScoreAway.text = event.intAwayScore
        tvGoalDetailsHome.text = event.strHomeGoalDetails
        tvGoalDetailsAway.text = event.strAwayGoalDetails
        tvShotsHome.text = event.intHomeShots
        tvShotsAway.text = event.intAwayShots
        tvHomeGK.text = event.strHomeLineupGoalkeeper
        tvAwayGK.text = event.strAwayLineupGoalkeeper
        tvHomeDef.text = event.strHomeLineupDefense
        tvAwayDef.text = event.strAwayLineupDefense
        tvHomeMid.text = event.strHomeLineupMidfield
        tvAwayMid.text = event.strAwayLineupMidfield
        tvHomeFwd.text = event.strHomeLineupForward
        tvAwayFwd.text = event.strAwayLineupForward
        tvHomeSub.text = event.strHomeLineupSubstitutes
        tvAwaySub.text = event.strAwayLineupSubstitutes

    }
}
