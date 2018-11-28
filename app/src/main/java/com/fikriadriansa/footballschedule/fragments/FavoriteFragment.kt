package com.fikriadriansa.footballschedule.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import com.fikriadriansa.footballschedule.R
import com.fikriadriansa.footballschedule.activity.MatchDetailActivity
import com.fikriadriansa.footballschedule.adapter.EventAdapter
import com.fikriadriansa.footballschedule.adapter.FavoriteAdapter
import com.fikriadriansa.footballschedule.db.database
import com.fikriadriansa.footballschedule.invisible
import com.fikriadriansa.footballschedule.model.Event
import com.fikriadriansa.footballschedule.model.Favorite
import com.fikriadriansa.footballschedule.model.TeamDetail
import com.fikriadriansa.footballschedule.view.MainView
import com.fikriadriansa.footballschedule.view.TeamDetailView
import com.fikriadriansa.footballschedule.visible
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_last_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
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
class FavoriteFragment : Fragment(){

    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoriteAdapter

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoriteAdapter(favorites,{favorite: Favorite ->partItemClicked(favorite)})
        rvFav.adapter = adapter

        swipe_event.onRefresh {
            showFavorite()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    private fun partItemClicked(favorite: Favorite) {
        startActivity<MatchDetailActivity>(
            "data" to favorite
        )
    }

    private fun showFavorite(){
        favorites.clear()
        context?.database?.use {
            swipe_fav.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }
}
