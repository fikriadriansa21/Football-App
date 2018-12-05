package com.fikriadriansa.footballschedule.model

import com.google.gson.annotations.SerializedName

data class SearchEventResponse (
    @SerializedName("event")
    val event: List<Event>
)