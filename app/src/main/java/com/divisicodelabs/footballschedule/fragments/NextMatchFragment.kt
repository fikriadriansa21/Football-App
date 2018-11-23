package com.divisicodelabs.footballschedule.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.divisicodelabs.footballschedule.R
import com.divisicodelabs.footballschedule.adapter.LastMatchAdapter
import com.divisicodelabs.footballschedule.adapter.NextMatchAdapter
import com.divisicodelabs.footballschedule.api.ApiRepository
import com.divisicodelabs.footballschedule.invisible
import com.divisicodelabs.footballschedule.model.Event
import com.divisicodelabs.footballschedule.presenter.MainPresenter
import com.divisicodelabs.footballschedule.view.MainView
import com.divisicodelabs.footballschedule.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_last_match.*
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.jetbrains.anko.support.v4.onRefresh

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class NextMatchFragment : Fragment(), MainView{

    private var events: MutableList<Event> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapterNextMatch: NextMatchAdapter

    override fun showLoading() {
        progress_nextmatch.visible()
    }

    override fun hideLoading() {
        progress_nextmatch.invisible()
    }

    override fun showListMatch(data: List<Event>) {
        swipe_nextmatch.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapterNextMatch.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapterNextMatch = NextMatchAdapter(events)
        rvNextMatch.adapter = adapterNextMatch

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        presenter.getListNextMatch()
        swipe_nextmatch.onRefresh {
            presenter.getListNextMatch()
        }
    }


}
