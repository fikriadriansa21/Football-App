package com.fikriadriansa.footballschedule.activity

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.fikriadriansa.footballschedule.R
import com.fikriadriansa.footballschedule.api.ApiRepository
import com.fikriadriansa.footballschedule.db.database
import com.fikriadriansa.footballschedule.model.Favorite
import com.fikriadriansa.footballschedule.model.Team
import com.fikriadriansa.footballschedule.model.TeamDetail
import com.fikriadriansa.footballschedule.presenter.TeamDetailPresenter
import com.fikriadriansa.footballschedule.view.TeamDetailView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_event_detail.*
import org.jetbrains.anko.db.delete

class FavoriteDetailActivity : AppCompatActivity(),TeamDetailView {

    private var detailTeam: MutableList<TeamDetail> = mutableListOf()
    private lateinit var detailPresenter: TeamDetailPresenter
    private var menuItem: Menu? = null


    companion object {
        lateinit var getDataFav: Favorite
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showTeamDetail(data: List<Team>) {

    }


    override fun showDetailHomeMatch(data: List<TeamDetail>) {
        detailTeam.clear()
        detailTeam.addAll(data)

        Picasso.get().load(data[0].strTeamBadge).into(img_home_team)
        tv_team_home_detail.text = data[0].strTeam
        tv_date_detail.text = getDataFav.teamMatchEventDate
        tv_score_home_detail.text = getDataFav.teamHomeScore
        tv_goals_detail_home.text = getDataFav.strHomeGoalDetails.toString().replace(";","\n")
        tv_home_gk.text = getDataFav.strHomeLineupGoalkeeper.toString().replace(";","\n")
        tv_home_def.text = getDataFav.strHomeLineupDefense.toString().replace(";","\n")
        tv_home_mid.text = getDataFav.strHomeLineupMidfield.toString().replace(";","\n")
        tv_home_fwd.text = getDataFav.strHomeLineupForward.toString().replace(";","\n")
        tv_home_sub.text = getDataFav.strHomeLineupSubstitutes.toString().replace(";","\n")
    }

    override fun showDetailAwayMatch(data: List<TeamDetail>) {
        detailTeam.clear()
        detailTeam.addAll(data)

        Picasso.get().load(data[0].strTeamBadge).into(img_away_team)
        tv_team_away_detail.text = data[0].strTeam
        tv_date_detail.text = getDataFav.teamMatchEventDate
        tv_score_away_detail.text = getDataFav.teamAwayScore
        tv_goals_detail_away.text = getDataFav.strAwayGoalDetails.toString().replace(";","\n")
        tv_away_gk.text = getDataFav.strAwayLineupGoalkeeper.toString().replace(";","\n")
        tv_away_def.text = getDataFav.strAwayLineupDefense.toString().replace(";","\n")
        tv_away_mid.text = getDataFav.strAwayLineupMidfield.toString().replace(";","\n")
        tv_away_fwd.text = getDataFav.strAwayLineupForward.toString().replace(";","\n")
        tv_away_sub.text = getDataFav.strAwayLineupSubstitutes.toString().replace(";","\n")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)
        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getDataFav = intent.getParcelableExtra("data")

        println(getDataFav)
        val request = ApiRepository()
        val gson = Gson()
        detailPresenter = TeamDetailPresenter(this, request, gson)
        detailPresenter.getDetailTeamHome(getDataFav.teamHomeId)
        detailPresenter.getDetailTeamAway(getDataFav.teamAwayId)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
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
            R.id.add_to_favorite -> {
                removeFromFavorite()

                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(
                    Favorite.TABLE_FAVORITE,"(TEAM_MATCH_EVENT_ID = {idEvents})",
                    "idEvents" to getDataFav.teamMatchEventId.toString()
                )
            }
            Toast.makeText(this,"Removed from favorite", Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
            Toast.makeText(this,e.localizedMessage,Toast.LENGTH_SHORT).show()
        }
    }

    private fun setFavorite() {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
    }
}
