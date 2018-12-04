package com.fikriadriansa.footballschedule.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Favorite (
    var teamMatchEventId:String?,
    var teamMatchEventDate: String?,
    var teamHomeId: String?,
    var teamAwayId: String?,
    var teamHomeName: String?,
    var teamAwayName: String?,
    var teamHomeScore: String?,
    var teamAwayScore: String?,
    val strHomeGoalDetails: String?,
    val strAwayGoalDetails: String?,
    val strHomeLineupGoalkeeper: String?,
    val strAwayLineupGoalkeeper: String?,
    val strHomeLineupDefense: String?,
    val strAwayLineupDefense: String?,
    val strHomeLineupMidfield: String?,
    val strAwayLineupMidfield: String?,
    val strHomeLineupForward: String?,
    val strAwayLineupForward: String?,
    val strHomeLineupSubstitutes: String?,
    val strAwayLineupSubstitutes: String?,
    val id: Long?,
    val teamId: String?,
    val teamName: String?,
    val teamBadge: String?

):Parcelable
{
 companion object {
     const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
     const val ID: String = "ID_"
     const val TEAM_ID: String = "TEAM_ID"
     const val TEAM_NAME: String = "TEAM_NAME"
     const val TEAM_BADGE: String = "TEAM_BADGE"
     const val TEAM_HOME_ID: String = "TEAM_HOME_ID"
     const val TEAM_AWAY_ID: String = "TEAM_AWAY_ID"
     const val TEAM_HOME_NAME: String = "TEAM_HOME_NAME"
     const val TEAM_AWAY_NAME: String = "TEAM_AWAY_NAME"
     const val TEAM_MATCH_EVENT_ID: String = "TEAM_MATCH_EVENT_ID"
     const val TEAM_MATCH_EVENT_DATE: String = "TEAM_MATCH_EVENT_DATE"
     const val TEAM_HOME_SCORE: String = "TEAM_HOME_SCORE"
     const val TEAM_AWAY_SCORE: String = "TEAM_AWAY_SCORE"
     const val HOME_GOAL_DETAILS: String = "HOME_GOAL_DETAILS"
     const val AWAY_GOAL_DETAILS: String = "AWAY_GOAL_DETAILS"
     const val HOME_LINEUP_GK: String = "HOME_LINEUP_GK"
     const val AWAY_LINEUP_GK: String = "AWAY_LINEUP_GK"
     const val HOME_LINEUP_DEF: String = "HOME_LINEUP_DEF"
     const val AWAY_LINEUP_DEF: String = "AWAY_LINEUP_DEF"
     const val HOME_LINEUP_MID: String = "HOME_LINEUP_MID"
     const val AWAY_LINEUP_MID: String = "AWAY_LINEUP_MID"
     const val HOME_LINEUP_FWD: String = "HOME_LINEUP_FWD"
     const val AWAY_LINEUP_FWD: String = "AWAY_LINEUP_FWD"
     const val HOME_LINEUP_SUB: String = "HOME_LINEUP_SUB"
     const val AWAY_LINEUP_SUB: String = "AWAY_LINEUP_SUB"
     const val TABLE_TEAM_FAVORITE: String = "TABLE_TEAM_FAVORITE"


 }
}