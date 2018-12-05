package com.fikriadriansa.footballschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.fikriadriansa.footballschedule.R
import com.fikriadriansa.footballschedule.model.Player
import com.squareup.picasso.Picasso

import org.jetbrains.anko.find

class PlayerAdapter(private val player: List<Player>, private val clickListener: (Player) -> Unit) : RecyclerView.Adapter<PlayerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): PlayerViewHolder {
        return PlayerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_player,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = player.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItems(player[position],clickListener)

    }


}

class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val playerImage: ImageView = view.find(R.id.img_player)
    private val playerName: TextView = view.find(R.id.tv_player_name)
    private val playerPos: TextView = view.find(R.id.tv_player_position)

    fun bindItems(player: Player, clickListener: (Player) -> Unit){
        Picasso.get().load(player.strCutout).into(playerImage)
        playerName.text = player.strPlayer
        playerPos.text = player.strPosition

        itemView.setOnClickListener {
            clickListener(player)
        }
    }
}