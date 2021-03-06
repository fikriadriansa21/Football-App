package com.fikriadriansa.footballschedule.activity

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.fikriadriansa.footballschedule.R
import com.fikriadriansa.footballschedule.adapter.TeamPagerAdapter
import com.fikriadriansa.footballschedule.api.ApiRepository
import com.fikriadriansa.footballschedule.db.database
import com.fikriadriansa.footballschedule.model.FavoriteTeam
import com.fikriadriansa.footballschedule.model.Team
import com.fikriadriansa.footballschedule.model.TeamDetail
import com.fikriadriansa.footballschedule.presenter.TeamDetailPresenter
import com.fikriadriansa.footballschedule.utils.invisible
import com.fikriadriansa.footballschedule.utils.visible
import com.fikriadriansa.footballschedule.view.TeamDetailView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*
import kotlinx.android.synthetic.main.fragment_team_overview.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class TeamDetailActivity : AppCompatActivity(),TeamDetailView {

    private lateinit var teams: Team
    private lateinit var presenter: TeamDetailPresenter
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String
    private lateinit var teamName: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        val intent = intent
        id = intent.getStringExtra("id")
        teamName = intent.getStringExtra("teamName")


        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        view_pager_team_detail.adapter = TeamPagerAdapter(supportFragmentManager, teamName)
        tab_detail_team.setupWithViewPager(view_pager_team_detail)

        favoriteState()
        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getTeamDetail(id)

    }

    private fun favoriteState(){
        database.use {
            val result = select(FavoriteTeam.TABLE_TEAM_FAVORITE)
                .whereArgs("(TEAM_ID = {id})",
                    "id" to id)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun showLoading() {
        progress_detail_team.visible()
    }

    override fun hideLoading() {
        progress_detail_team.invisible()
    }

    override fun showTeamDetail(data: List<Team>) {
        teams = Team(data[0].teamId,
            data[0].teamName,
            data[0].teamBadge)
        Picasso.get().load(data[0].teamBadge).into(img_team_detail)
        tv_name_team_detail.text = data[0].teamName
        tv_formed_year.text = data[0].teamFormedYear
        tv_main_stadium.text = data[0].teamStadium
        tv_overview.text = data[0].teamDescription
    }

    override fun showDetailHomeMatch(data: List<TeamDetail>) {

    }

    override fun showDetailAwayMatch(data: List<TeamDetail>) {

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
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(FavoriteTeam.TABLE_TEAM_FAVORITE,
                    FavoriteTeam.TEAM_ID to teams.teamId,
                    FavoriteTeam.TEAM_NAME to teams.teamName,
                    FavoriteTeam.TEAM_BADGE to teams.teamBadge)
            }
            Toast.makeText(this,"Added to favorite", Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
            Toast.makeText(this,e.localizedMessage,Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteTeam.TABLE_TEAM_FAVORITE, "(TEAM_ID = {id})",
                    "id" to id)
            }
            Toast.makeText(this,"Removed from favorite", Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
            Toast.makeText(this,e.localizedMessage,Toast.LENGTH_SHORT).show()

        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }
}
