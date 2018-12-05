package com.fikriadriansa.footballschedule.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PlayerResponse (
    @SerializedName("player")
    @Expose
    val player: List<Player>
)
