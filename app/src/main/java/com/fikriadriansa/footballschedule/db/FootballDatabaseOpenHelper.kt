package com.fikriadriansa.footballschedule.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.fikriadriansa.footballschedule.model.Favorite
import org.jetbrains.anko.db.*

class FootballDatabaseOpenHelper(context: Context): ManagedSQLiteOpenHelper(context,"FavoriteTeam.db",null,1){
    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            Favorite.TABLE_FAVORITE, true,
            Favorite.TEAM_MATCH_EVENT_ID to TEXT + PRIMARY_KEY,
            Favorite.TEAM_MATCH_EVENT_DATE to TEXT,
            Favorite.TEAM_HOME_ID to TEXT,
            Favorite.TEAM_AWAY_ID to TEXT,
            Favorite.TEAM_HOME_NAME to TEXT,
            Favorite.TEAM_AWAY_NAME to TEXT,
            Favorite.TEAM_HOME_SCORE to TEXT,
            Favorite.TEAM_AWAY_SCORE to TEXT,
            Favorite.HOME_GOAL_DETAILS to TEXT,
            Favorite.AWAY_GOAL_DETAILS to TEXT,
            Favorite.HOME_LINEUP_GK to TEXT,
            Favorite.AWAY_LINEUP_GK to TEXT,
            Favorite.HOME_LINEUP_DEF to TEXT,
            Favorite.AWAY_LINEUP_DEF to TEXT,
            Favorite.HOME_LINEUP_MID to TEXT,
            Favorite.AWAY_LINEUP_MID to TEXT,
            Favorite.HOME_LINEUP_FWD to TEXT,
            Favorite.AWAY_LINEUP_FWD to TEXT,
            Favorite.HOME_LINEUP_SUB to TEXT,
            Favorite.AWAY_LINEUP_SUB to TEXT
        )

        db.createTable(Favorite.TABLE_TEAM_FAVORITE, true,
            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.TEAM_ID to TEXT,
            Favorite.TEAM_NAME to TEXT,
            Favorite.TEAM_BADGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }

    companion object {
        private var instance: FootballDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): FootballDatabaseOpenHelper {
            if (instance == null) {
                instance = FootballDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as FootballDatabaseOpenHelper
        }
    }
}

val Context.database: FootballDatabaseOpenHelper
    get() = FootballDatabaseOpenHelper.getInstance(applicationContext)