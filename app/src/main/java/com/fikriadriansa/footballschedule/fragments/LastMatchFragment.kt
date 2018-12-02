package com.fikriadriansa.footballschedule.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fikriadriansa.footballschedule.R
import com.fikriadriansa.footballschedule.activity.MatchDetailActivity
import com.fikriadriansa.footballschedule.adapter.EventAdapter
import com.fikriadriansa.footballschedule.api.ApiRepository
import com.fikriadriansa.footballschedule.utils.invisible
import com.fikriadriansa.footballschedule.model.Event
import com.fikriadriansa.footballschedule.presenter.MainPresenter
import com.fikriadriansa.footballschedule.utils.CoroutineContextProvider
import com.fikriadriansa.footballschedule.view.MainView
import com.fikriadriansa.footballschedule.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_event_match.*
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
class LastMatchFragment : Fragment(), MainView {

    private var events: MutableList<Event> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapterLastMatch: EventAdapter


    override fun showLoading() {
        progress_match.visible()
    }

    override fun hideLoading() {
        progress_match.invisible()
    }

    override fun showListMatch(data: List<Event>) {
        swipe_event.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapterLastMatch.notifyDataSetChanged()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_match, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapterLastMatch = EventAdapter(events) { events:Event->partItemClicked(events)}
        rvEvent.adapter = adapterLastMatch

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        presenter.getListLastMatch()
        swipe_event.onRefresh {
            presenter.getListLastMatch()
        }
    }

    private fun partItemClicked(events: Event) {
        startActivity<MatchDetailActivity>(
            MatchDetailActivity.DATA to events
        )
    }



}
