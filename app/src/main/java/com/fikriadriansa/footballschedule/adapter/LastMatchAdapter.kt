package com.fikriadriansa.footballschedule.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.divisicodelabs.footballschedule.R
import com.fikriadriansa.footballschedule.model.Event
import org.jetbrains.anko.find

class LastMatchAdapter(private val events: List<Event>) :RecyclerView.Adapter<LastMatchViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): LastMatchViewHolder {
        return LastMatchViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_event_lastmatch,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: LastMatchViewHolder, position: Int) {
        holder.bindItems(events[position])

    }


}

class LastMatchViewHolder(view: View): RecyclerView.ViewHolder(view){
    private val dateEvent: TextView = view.findViewById(R.id.tv_date_lastmatch)
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