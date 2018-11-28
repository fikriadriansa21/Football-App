package com.fikriadriansa.footballschedule.model

data class Favorite (
    val teamMatchEventId:String?,
    val teamMatchEventDate: String?,
    val teamHomeId: String?,
    val teamAwayId: String?,
    val teamHomeName: String?,
    val teamAwayName: String?,
    val teamHomeScore: String?,
    val teamAwayScore: String?
){
 companion object {
     const val TABLE_FAVORITE: String = "TABLE_FAVORITE"

     const val TEAM_HOME_ID: String = "TEAM_HOME_ID"
     const val TEAM_AWAY_ID: String = "TEAM_AWAY_ID"
     const val TEAM_HOME_NAME: String = "TEAM_HOME_NAME"
     const val TEAM_AWAY_NAME: String = "TEAM_AWAY_NAME"
     const val TEAM_MATCH_EVENT_ID: String = "TEAM_MATCH_EVENT_ID"
     const val TEAM_MATCH_EVENT_DATE: String = "TEAM_MATCH_EVENT_DATE"
     const val TEAM_HOME_SCORE: String = "TEAM_HOME_SCORE"
     const val TEAM_AWAY_SCORE: String = "TEAM_AWAY_SCORE"
 }
}