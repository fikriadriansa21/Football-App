package com.fikriadriansa.footballschedule.activity

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.fikriadriansa.footballschedule.R
import com.fikriadriansa.footballschedule.R.drawable.ic_add_to_favorites
import com.fikriadriansa.footballschedule.R.drawable.ic_added_to_favorites
import com.fikriadriansa.footballschedule.R.id.add_to_favorite
import com.fikriadriansa.footballschedule.R.menu.detail_menu
import com.fikriadriansa.footballschedule.api.ApiRepository
import com.fikriadriansa.footballschedule.model.Favorite
import com.fikriadriansa.footballschedule.db.database
import com.fikriadriansa.footballschedule.model.Event
import com.fikriadriansa.footballschedule.model.TeamDetail
import com.fikriadriansa.footballschedule.presenter.TeamDetailPresenter
import com.fikriadriansa.footballschedule.view.TeamDetailView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_event_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class MatchDetailActivity : AppCompatActivity(), TeamDetailView{

    private var teamDetail: MutableList<TeamDetail> = mutableListOf()
    private lateinit var presenter: TeamDetailPresenter
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var idEvents: String

    companion object {
        lateinit var getDataEvent: Event
        lateinit var getDataFav: Favorite
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)
        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getDataEvent = intent.getParcelableExtra("data")

        favoriteState()
        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getDetailTeamHome(getDataEvent.idHomeTeam)
        presenter.getDetailTeamAway(getDataEvent.idAwayTeam)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {
    }


    override fun showDetailHomeMatch(data: List<TeamDetail>) {
        teamDetail.clear()
        teamDetail.addAll(data)

        Picasso.get().load(data[0].strTeamBadge).into(img_home_team)
        tv_team_home_detail.text = getDataEvent.strHomeTeam
        tv_date_detail.text = getDataEvent.dateEvent
        tv_score_home_detail.text = getDataEvent.intHomeScore
        tv_goals_detail_home.text = getDataEvent.strHomeGoalDetails.toString().replace(";","\n")
        tv_home_gk.text = getDataEvent.strHomeLineupGoalkeeper.toString().replace(";","\n")
        tv_home_def.text = getDataEvent.strHomeLineupDefense.toString().replace(";","\n")
        tv_home_mid.text = getDataEvent.strHomeLineupMidfield.toString().replace(";","\n")
        tv_home_fwd.text = getDataEvent.strHomeLineupForward.toString().replace(";","\n")
        tv_home_sub.text = getDataEvent.strHomeLineupSubstitutes.toString().replace(";","\n")

    }

    override fun showDetailAwayMatch(data: List<TeamDetail>) {
        teamDetail.clear()
        teamDetail.addAll(data)
        Picasso.get().load(data[0].strTeamBadge).into(img_away_team)
        tv_team_away_detail.text = getDataEvent.strAwayTeam
        tv_score_away_detail.text = getDataEvent.intAwayScore
        tv_goals_detail_away.text = getDataEvent.strAwayGoalDetails.toString().replace(";","\n")
        tv_away_gk.text = getDataEvent.strAwayLineupGoalkeeper.toString().replace(";","\n")
        tv_away_def.text = getDataEvent.strAwayLineupDefense.toString().replace(";","\n")
        tv_away_mid.text = getDataEvent.strAwayLineupMidfield.toString().replace(";","\n")
        tv_away_fwd.text = getDataEvent.strAwayLineupForward.toString().replace(";","\n")
        tv_away_sub.text = getDataEvent.strAwayLineupSubstitutes.toString().replace(";","\n")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite()
                else
                    addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs("(TEAM_MATCH_EVENT_ID = {idEvents})",
                    "idEvents" to getDataEvent.idEvent.toString())
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.TEAM_MATCH_EVENT_ID to getDataEvent.idEvent,
                    Favorite.TEAM_MATCH_EVENT_DATE to getDataEvent.dateEvent,
                    Favorite.TEAM_HOME_ID to getDataEvent.idHomeTeam,
                    Favorite.TEAM_AWAY_ID to getDataEvent.idAwayTeam,
                    Favorite.TEAM_MATCH_EVENT_DATE to getDataEvent.dateEvent,
                    Favorite.TEAM_HOME_NAME to getDataEvent.strHomeTeam,
                    Favorite.TEAM_AWAY_NAME to getDataEvent.strAwayTeam,
                    Favorite.TEAM_HOME_SCORE to getDataEvent.intHomeScore,
                    Favorite.TEAM_AWAY_SCORE to getDataEvent.intAwayScore
                )
            }
            Toast.makeText(this,"Added to favorite", Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
            Toast.makeText(this,e.localizedMessage,Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(
                    Favorite.TABLE_FAVORITE,"(TEAM_MATCH_EVENT_ID = {idEvents})",
                    "idEvents" to getDataEvent.idEvent.toString()
                )
            }
            Toast.makeText(this,"Removed from favorite", Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
            Toast.makeText(this,e.localizedMessage,Toast.LENGTH_SHORT).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }


}
