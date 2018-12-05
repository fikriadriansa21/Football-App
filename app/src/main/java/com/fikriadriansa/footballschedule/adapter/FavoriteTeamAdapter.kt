package com.fikriadriansa.footballschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.fikriadriansa.footballschedule.R
import com.fikriadriansa.footballschedule.model.Favorite
import com.fikriadriansa.footballschedule.model.FavoriteTeam
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class FavoriteTeamAdapter(private val favorite: List<FavoriteTeam>, private val clickListener: (FavoriteTeam)->Unit)
    : RecyclerView.Adapter<FavoriteTeamViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTeamViewHolder{
        return FavoriteTeamViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_team,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: FavoriteTeamViewHolder, position: Int) {
        holder.bindItems(favorite[position],clickListener)
    }
}

class FavoriteTeamViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val teamBadge: ImageView = view.find(R.id.img_team)
    private val teamName: TextView = view.find(R.id.tv_team_name)



    fun bindItems(favorite: FavoriteTeam, clickListener: (FavoriteTeam) -> Unit){
        Picasso.get().load(favorite.teamBadge).into(teamBadge)
        teamName.text = favorite.teamName
        itemView.setOnClickListener { clickListener(favorite) }
    }
}