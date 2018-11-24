package com.fikriadriansa.footballschedule.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamDetail (
    @SerializedName("idTeam")
    @Expose
    var idTeam: String? = null,
    @SerializedName("idSoccerXML")
    @Expose
    var idSoccerXML: String? = null,
    @SerializedName("intLoved")
    @Expose
     var intLoved: String? = null,
    @SerializedName("strTeam")
    @Expose
     var strTeam: String? = null,
    @SerializedName("strTeamShort")
    @Expose
     var strTeamShort: String? = null,
    @SerializedName("strAlternate")
    @Expose
     var strAlternate: String? = null,
    @SerializedName("intFormedYear")
    @Expose
     var intFormedYear: String? = null,
    @SerializedName("strSport")
    @Expose
     var strSport: String? = null,
    @SerializedName("strLeague")
    @Expose
     var strLeague: String? = null,
    @SerializedName("idLeague")
    @Expose
     var idLeague: String? = null,
    @SerializedName("strDivision")
    @Expose
     var strDivision: String? = null,
    @SerializedName("strManager")
    @Expose
     var strManager: String? = null,
    @SerializedName("strStadium")
    @Expose
     var strStadium: String? = null,
    @SerializedName("strKeywords")
    @Expose
     var strKeywords: String? = null,
    @SerializedName("strRSS")
    @Expose
     var strRSS: String? = null,
    @SerializedName("strStadiumThumb")
    @Expose
     var strStadiumThumb: String? = null,
    @SerializedName("strStadiumDescription")
    @Expose
     var strStadiumDescription: String? = null,
    @SerializedName("strStadiumLocation")
    @Expose
     var strStadiumLocation: String? = null,
    @SerializedName("intStadiumCapacity")
    @Expose
     var intStadiumCapacity: String? = null,
    @SerializedName("strWebsite")
    @Expose
     var strWebsite: String? = null,
    @SerializedName("strFacebook")
    @Expose
     var strFacebook: String? = null,
    @SerializedName("strTwitter")
    @Expose
     var strTwitter: String? = null,
    @SerializedName("strInstagram")
    @Expose
     var strInstagram: String? = null,
    @SerializedName("strDescriptionEN")
    @Expose
     var strDescriptionEN: String? = null,
    @SerializedName("strDescriptionDE")
    @Expose
     var strDescriptionDE: String? = null,
    @SerializedName("strDescriptionFR")
    @Expose
     var strDescriptionFR: String? = null,
    @SerializedName("strDescriptionCN")
    @Expose
     var strDescriptionCN: String? = null,
    @SerializedName("strDescriptionIT")
    @Expose
     var strDescriptionIT: String? = null,
    @SerializedName("strDescriptionJP")
    @Expose
     var strDescriptionJP: String? = null,
    @SerializedName("strDescriptionRU")
    @Expose
     var strDescriptionRU: String? = null,
    @SerializedName("strDescriptionES")
    @Expose
     var strDescriptionES: String? = null,
    @SerializedName("strDescriptionPT")
    @Expose
     var strDescriptionPT: String? = null,
    @SerializedName("strDescriptionSE")
    @Expose
     var strDescriptionSE: String? = null,
    @SerializedName("strDescriptionNL")
    @Expose
     var strDescriptionNL: String? = null,
    @SerializedName("strDescriptionHU")
    @Expose
     var strDescriptionHU: String? = null,
    @SerializedName("strDescriptionNO")
    @Expose
     var strDescriptionNO: String? = null,
    @SerializedName("strDescriptionIL")
    @Expose
     var strDescriptionIL: String? = null,
    @SerializedName("strDescriptionPL")
    @Expose
     var strDescriptionPL: String? = null,
    @SerializedName("strGender")
    @Expose
     var strGender: String? = null,
    @SerializedName("strCountry")
    @Expose
     var strCountry: String? = null,
    @SerializedName("strTeamBadge")
    @Expose
     var strTeamBadge: String? = null,
    @SerializedName("strTeamJersey")
    @Expose
     var strTeamJersey: String? = null,
    @SerializedName("strTeamLogo")
    @Expose
     var strTeamLogo: String? = null,
    @SerializedName("strTeamFanart1")
    @Expose
     var strTeamFanart1: String? = null,
    @SerializedName("strTeamFanart2")
    @Expose
     var strTeamFanart2: String? = null,
    @SerializedName("strTeamFanart3")
    @Expose
     var strTeamFanart3: String? = null,
    @SerializedName("strTeamFanart4")
    @Expose
     var strTeamFanart4: String? = null,
    @SerializedName("strTeamBanner")
    @Expose
     var strTeamBanner: String? = null,
    @SerializedName("strYoutube")
    @Expose
     var strYoutube: String? = null,
    @SerializedName("strLocked")
    @Expose
     var strLocked: String? = null
): Parcelable
