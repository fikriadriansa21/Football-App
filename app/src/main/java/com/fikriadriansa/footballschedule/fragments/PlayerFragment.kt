package com.fikriadriansa.footballschedule.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fikriadriansa.footballschedule.R
import com.fikriadriansa.footballschedule.activity.MatchDetailActivity
import com.fikriadriansa.footballschedule.adapter.PlayerAdapter
import com.fikriadriansa.footballschedule.api.ApiRepository
import com.fikriadriansa.footballschedule.model.Player
import com.fikriadriansa.footballschedule.model.Team
import com.fikriadriansa.footballschedule.presenter.PlayerPresenter
import com.fikriadriansa.footballschedule.utils.invisible
import com.fikriadriansa.footballschedule.utils.visible
import com.fikriadriansa.footballschedule.view.PlayerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_player.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PlayerFragment : Fragment(),PlayerView {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    private var players: MutableList<Player> = mutableListOf()
    private lateinit var presenter: PlayerPresenter
    private lateinit var adapter: PlayerAdapter

    companion object {
        var playerTeam: Team? = null
    }

    override fun showLoading() {
        progress_player.visible()
    }

    override fun hideLoading() {
        progress_player.invisible()
    }

    override fun showListPlayer(data: List<Player>) {
        swipe_player.isRefreshing = false
        players.clear()
        players.addAll(data)
        adapter.notifyDataSetChanged()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = PlayerAdapter(players) { player: Player ->partItemClicked(player)}
        rvPlayer.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayerPresenter(this, request, gson)


        presenter.getListPlayer(playerTeam?.teamName)

        swipe_player.onRefresh {
            presenter.getListPlayer(playerTeam?.teamName)
        }
    }

    private fun partItemClicked(player: Player) {
        startActivity<MatchDetailActivity>(
            MatchDetailActivity.DATA to player
        )
    }

}
