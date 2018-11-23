package com.fikriadriansa.footballschedule.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event (
    @SerializedName("idEvent")
    @Expose
    var idEvent: String? = null,
    @SerializedName("idSoccerXML")
    @Expose
    var idSoccerXMl: String? = null,
    @SerializedName("strEvent")
    @Expose
     var strEvent: String? = null,
    @SerializedName("strFilename")
    @Expose
     var strFilename: String? = null,
    @SerializedName("strSport")
    @Expose
     var strSport: String? = null,
    @SerializedName("idLeague")
    @Expose
     var idLeague: String? = null,
    @SerializedName("strLeague")
    @Expose
     var strLeague: String? = null,
    @SerializedName("strSeason")
    @Expose
     var strSeason: String? = null,
    @SerializedName("strDescriptionEN")
    @Expose
     var strDescriptionEN: String? = null,
    @SerializedName("strHomeTeam")
    @Expose
     var strHomeTeam: String? = null,
    @SerializedName("strAwayTeam")
    @Expose
     var strAwayTeam: String? = null,
    @SerializedName("intHomeScore")
    @Expose
     var intHomeScore: String? = null,
    @SerializedName("intRound")
    @Expose
     var intRound: String? = null,
    @SerializedName("intAwayScore")
    @Expose
     var intAwayScore: String? = null,
    @SerializedName("intSpectators")
    @Expose
     var intSpectators: String? = null,
    @SerializedName("strHomeGoalDetails")
    @Expose
     var strHomeGoalDetails: String? = null,
    @SerializedName("strHomeRedCards")
    @Expose
     var strHomeRedCards: String? = null,
    @SerializedName("strHomeYellowCards")
    @Expose
     var strHomeYellowCards: String? = null,
    @SerializedName("strHomeLineupGoalkeeper")
    @Expose
     var strHomeLineupGoalkeeper: String? = null,
    @SerializedName("strHomeLineupDefense")
    @Expose
     var strHomeLineupDefense: String? = null,
    @SerializedName("strHomeLineupMidfield")
    @Expose
     var strHomeLineupMidfield: String? = null,
    @SerializedName("strHomeLineupForward")
    @Expose
     var strHomeLineupForward: String? = null,
    @SerializedName("strHomeLineupSubstitutes")
    @Expose
     var strHomeLineupSubstitutes: String? = null,
    @SerializedName("strHomeFormation")
    @Expose
     var strHomeFormation: String? = null,
    @SerializedName("strAwayRedCards")
    @Expose
     var strAwayRedCards: String? = null,
    @SerializedName("strAwayYellowCards")
    @Expose
     var strAwayYellowCards: String? = null,
    @SerializedName("strAwayGoalDetails")
    @Expose
     var strAwayGoalDetails: String? = null,
    @SerializedName("strAwayLineupGoalkeeper")
    @Expose
     var strAwayLineupGoalkeeper: String? = null,
    @SerializedName("strAwayLineupDefense")
    @Expose
     var strAwayLineupDefense: String? = null,
    @SerializedName("strAwayLineupMidfield")
    @Expose
     var strAwayLineupMidfield: String? = null,
    @SerializedName("strAwayLineupForward")
    @Expose
     var strAwayLineupForward: String? = null,
    @SerializedName("strAwayLineupSubstitutes")
    @Expose
     var strAwayLineupSubstitutes: String? = null,
    @SerializedName("strAwayFormation")
    @Expose
     var strAwayFormation: String? = null,
    @SerializedName("intHomeShots")
    @Expose
     var intHomeShots: String? = null,
    @SerializedName("intAwayShots")
    @Expose
     var intAwayShots: String? = null,
    @SerializedName("dateEvent")
    @Expose
     var dateEvent: String? = null,
    @SerializedName("strDate")
    @Expose
     var strDate: String? = null,
    @SerializedName("strTime")
    @Expose
     var strTime: String? = null,
    @SerializedName("strTVStation")
    @Expose
     var strTVStation: String? = null,
    @SerializedName("idHomeTeam")
    @Expose
     var idHomeTeam: String? = null,
    @SerializedName("idAwayTeam")
    @Expose
     var idAwayTeam: String? = null,
    @SerializedName("strResult")
    @Expose
     var strResult: String? = null,
    @SerializedName("strCircuit")
    @Expose
     var strCircuit: String? = null,
    @SerializedName("strCountry")
    @Expose
     var strCountry: String? = null,
    @SerializedName("strCity")
    @Expose
     var strCity: String? = null,
    @SerializedName("strPoster")
    @Expose
     var strPoster: String? = null,
    @SerializedName("strFanart")
    @Expose
     var strFanart: String? = null,
    @SerializedName("strThumb")
    @Expose
     var strThumb: String? = null,
    @SerializedName("strBanner")
    @Expose
     var strBanner: String? = null,
    @SerializedName("strMap")
    @Expose
     var strMap: String? = null,
    @SerializedName("strLocked")
    @Expose
     var strLocked: String? = null
): Parcelable

