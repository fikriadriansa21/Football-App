package com.divisicodelabs.footballschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.divisicodelabs.footballschedule.R
import com.divisicodelabs.footballschedule.model.Event

class NextMatchAdapter(private val events: List<Event>) : RecyclerView.Adapter<NextMatchViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): NextMatchViewHolder {
        return NextMatchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_event_nextmatch,parent,false))
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: NextMatchViewHolder, position: Int) {
        holder.bindItems(events[position])
    }
}

class NextMatchViewHolder(view: View): RecyclerView.ViewHolder(view){
    private val dateEvent: TextView = view.findViewById(R.id.tv_date_nextmatch)
    private val homeTeamName: TextView = view.findViewById(R.id.tv_home_team)
    private val awayTeamName: TextView = view.findViewById(R.id.tv_away_team)
    private val homeScore: TextView = view.findViewById(R.id.tv_score_home)
    private val awayScore: TextView = view.findViewById(R.id.tv_score_away)


    fun bindItems(events: Event){
        dateEvent.text = events.strDate
        homeTeamName.text = events.strHomeTeam
        awayTeamName.text = events.strAwayTeam
        homeScore.text = events.intHomeScore
        awayScore.text = events.intAwayScore

    }
}