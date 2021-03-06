package com.fikriadriansa.footballschedule.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fikriadriansa.footballschedule.R
import com.fikriadriansa.footballschedule.activity.FavoriteTeamDetailActivity
import com.fikriadriansa.footballschedule.activity.TeamDetailActivity
import com.fikriadriansa.footballschedule.adapter.FavoriteTeamAdapter
import com.fikriadriansa.footballschedule.db.database
import com.fikriadriansa.footballschedule.model.Favorite
import com.fikriadriansa.footballschedule.model.FavoriteTeam
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_favorite_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteTeamFragment : Fragment() {

private var favorites: MutableList<FavoriteTeam> = mutableListOf()
    private lateinit var adapter: FavoriteTeamAdapter


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoriteTeamAdapter(favorites) {
            context?.startActivity<FavoriteTeamDetailActivity>(
                "id" to "${it.teamId}",
                "teamName" to "${it.teamName}")
        }
//        { favorite: Favorite ->partItemClicked(favorite)}
        rvFavTeam.adapter = adapter
        showFavorite()

    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_team, container, false)
    }

//    private fun partItemClicked(favorite: Favorite) {
//        startActivity<FavoriteDetailActivity>(
//            "data" to favorite
//        )
//    }

    private fun showFavorite(){
        favorites.clear()
        context?.database?.use {
            val result = select(FavoriteTeam.TABLE_TEAM_FAVORITE)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }



}
