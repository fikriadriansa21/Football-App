package com.fikriadriansa.footballschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fikriadriansa.footballschedule.R
import com.fikriadriansa.footballschedule.model.Favorite

class FavoriteAdapter(private val favorite: List<Favorite>, private val clickListener: (Favorite)->Unit)
    :RecyclerView.Adapter<FavoriteViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_event_match,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItems(favorite[position],clickListener)
    }
}

class FavoriteViewHolder(view: View):RecyclerView.ViewHolder(view) {
    private val dateEvent: TextView = view.findViewById(R.id.tv_date)
    private val homeTeamName: TextView = view.findViewById(R.id.tv_home_team)
    private val awayTeamName: TextView = view.findViewById(R.id.tv_away_team)
    private val homeScore: TextView = view.findViewById(R.id.tv_score_home)
    private val awayScore: TextView = view.findViewById(R.id.tv_score_away)



    fun bindItems(favorite: Favorite, clickListener: (Favorite) -> Unit){
        dateEvent.text = favorite.teamMatchEventDate
        homeTeamName.text = favorite.teamHomeName
        awayTeamName.text = favorite.teamAwayName
        homeScore.text = favorite.teamHomeScore
        awayScore.text = favorite.teamAwayScore
        itemView.setOnClickListener {
            clickListener(favorite)
        }
    }
}
