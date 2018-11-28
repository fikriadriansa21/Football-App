package com.fikriadriansa.footballschedule.activity

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.fikriadriansa.footballschedule.R
import com.fikriadriansa.footballschedule.api.ApiRepository
import com.fikriadriansa.footballschedule.db.database
import com.fikriadriansa.footballschedule.model.Favorite
import com.fikriadriansa.footballschedule.presenter.TeamDetailPresenter
import com.google.gson.Gson
import org.jetbrains.anko.db.insert

class FavoriteDetailActivity : AppCompatActivity() {

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_detail)
        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        MatchDetailActivity.getDataEvent = intent.getParcelableExtra("data")

//        favoriteState()
//        val request = ApiRepository()
//        val gson = Gson()
//        presenter = TeamDetailPresenter(this, request, gson)
//        presenter.getDetailTeamHome(MatchDetailActivity.getDataEvent.idHomeTeam)
//        presenter.getDetailTeamAway(MatchDetailActivity.getDataEvent.idAwayTeam)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                addToFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.TEAM_MATCH_EVENT_ID to MatchDetailActivity.getDataEvent.idEvent,
                    Favorite.TEAM_MATCH_EVENT_DATE to MatchDetailActivity.getDataEvent.dateEvent,
                    Favorite.TEAM_HOME_ID to MatchDetailActivity.getDataEvent.idHomeTeam,
                    Favorite.TEAM_AWAY_ID to MatchDetailActivity.getDataEvent.idAwayTeam,
                    Favorite.TEAM_MATCH_EVENT_DATE to MatchDetailActivity.getDataEvent.dateEvent,
                    Favorite.TEAM_HOME_NAME to MatchDetailActivity.getDataEvent.strHomeTeam,
                    Favorite.TEAM_AWAY_NAME to MatchDetailActivity.getDataEvent.strAwayTeam,
                    Favorite.TEAM_HOME_SCORE to MatchDetailActivity.getDataEvent.intHomeScore,
                    Favorite.TEAM_AWAY_SCORE to MatchDetailActivity.getDataEvent.intAwayScore
                )
            }
            Toast.makeText(this,"Added to favorite", Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
            Toast.makeText(this,e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }
}
