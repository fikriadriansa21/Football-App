package com.fikriadriansa.footballschedule.api

import android.net.Uri
import com.fikriadriansa.footballschedule.BuildConfig

object TheSportDBApi {

    fun getLastMatch(): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("eventspastleague.php")
            .appendQueryParameter("id", "4328")
            .build()
            .toString()
    }

    fun getNextMatch(): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("eventsnextleague.php")
            .appendQueryParameter("id", "4328")
            .build()
            .toString()
    }

}