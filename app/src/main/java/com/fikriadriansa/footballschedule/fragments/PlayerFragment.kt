package com.fikriadriansa.footballschedule.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fikriadriansa.footballschedule.R
import com.fikriadriansa.footballschedule.activity.MatchDetailActivity
import com.fikriadriansa.footballschedule.activity.PlayerDetailActivity
import com.fikriadriansa.footballschedule.activity.TeamDetailActivity
import com.fikriadriansa.footballschedule.adapter.PlayerAdapter
import com.fikriadriansa.footballschedule.adapter.TeamsAdapter
import com.fikriadriansa.footballschedule.api.ApiRepository
import com.fikriadriansa.footballschedule.model.Player
import com.fikriadriansa.footballschedule.model.Team
import com.fikriadriansa.footballschedule.presenter.PlayerPresenter
import com.fikriadriansa.footballschedule.utils.invisible
import com.fikriadriansa.footballschedule.utils.visible
import com.fikriadriansa.footballschedule.view.PlayerView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*
import kotlinx.android.synthetic.main.fragment_player.*
import kotlinx.android.synthetic.main.item_player.*
import kotlinx.android.synthetic.main.item_team.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "teamName"

/**
 * A simple [Fragment] subclass.
 *
 */
class PlayerFragment : Fragment(),PlayerView {
    private var getTeamName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            getTeamName = it.getString(ARG_PARAM1)

        }
    }

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
        @JvmStatic
        fun newInstance(teamName: String) =
            PlayerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, teamName)
                }
            }

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

        adapter = PlayerAdapter(players) {
            context?.startActivity<PlayerDetailActivity>(
                "id" to "${it.idPlayer}",
                "playerName" to "${it.strPlayer}",
                "strDescription" to "${it.strDescriptionEN}",
                "fanart" to "${it.strThumb}",
                "weight" to "${it.strWeight}",
                "height" to "${it.strHeight}",
                "position" to "${it.strPosition}")
        }


        rvPlayer.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayerPresenter(this, request, gson)
        presenter.getListPlayer(getTeamName)

        swipe_player.onRefresh {
            presenter.getListPlayer(getTeamName)
        }
    }

//    private fun partItemClicked(player: Player) {
//        startActivity<MatchDetailActivity>(
//            MatchDetailActivity.DATA to player
//        )
//    }



}
