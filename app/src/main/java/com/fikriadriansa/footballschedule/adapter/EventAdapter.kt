package com.fikriadriansa.footballschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fikriadriansa.footballschedule.R
import com.fikriadriansa.footballschedule.fragments.NextMatchFragment
import com.fikriadriansa.footballschedule.model.Event
import kotlinx.android.extensions.LayoutContainer


class EventAdapter(private val events: List<Event>, private val clickListener: (Event) -> Unit) :RecyclerView.Adapter<EventViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): EventViewHolder {
        return EventViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_event_match,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bindItems(events[position],clickListener)

    }


}

class EventViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView),LayoutContainer{
    private val dateEvent: TextView = containerView.findViewById(R.id.tv_date)
    private val homeTeamName: TextView = containerView.findViewById(R.id.tv_home_team)
    private val awayTeamName: TextView = containerView.findViewById(R.id.tv_away_team)
    private val homeScore: TextView = containerView.findViewById(R.id.tv_score_home)
    private val awayScore: TextView = containerView.findViewById(R.id.tv_score_away)


    fun bindItems(events: Event, clickListener: (Event) -> Unit){
        dateEvent.text = events.strDate
        homeTeamName.text = events.strHomeTeam
        awayTeamName.text = events.strAwayTeam
        homeScore.text = events.intHomeScore
        awayScore.text = events.intAwayScore
        containerView.setOnClickListener {
            clickListener(events)
        }
    }
}