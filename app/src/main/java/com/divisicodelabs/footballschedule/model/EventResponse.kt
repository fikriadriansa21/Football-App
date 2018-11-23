package com.divisicodelabs.footballschedule.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EventResponse (
    @SerializedName("events")
    @Expose
    var events: List<Event>
)