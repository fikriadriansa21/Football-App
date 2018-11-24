package com.fikriadriansa.footballschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fikriadriansa.footballschedule.R
import com.fikriadriansa.footballschedule.model.Event


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

class EventViewHolder(view: View): RecyclerView.ViewHolder(view){
    private val dateEvent: TextView = view.findViewById(R.id.tv_date)
    private val homeTeamName: TextView = view.findViewById(R.id.tv_home_team)
    private val awayTeamName: TextView = view.findViewById(R.id.tv_away_team)
    private val homeScore: TextView = view.findViewById(R.id.tv_score_home)
    private val awayScore: TextView = view.findViewById(R.id.tv_score_away)


    fun bindItems(events: Event,clickListener:(Event)->Unit){
        dateEvent.text = events.strDate
        homeTeamName.text = events.strHomeTeam
        awayTeamName.text = events.strAwayTeam
        homeScore.text = events.intHomeScore
        awayScore.text = events.intAwayScore
        itemView.setOnClickListener {
            clickListener(events)
        }
    }
}