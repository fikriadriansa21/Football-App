package com.fikriadriansa.footballschedule.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class TeamDetailResponse (
    @SerializedName("teams")
    @Expose
    var teams: List<TeamDetail>
)