package com.fikriadriansa.footballschedule.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fikriadriansa.footballschedule.R
import com.fikriadriansa.footballschedule.activity.MatchDetailActivity
import com.fikriadriansa.footballschedule.adapter.LastMatchAdapter
import com.fikriadriansa.footballschedule.api.ApiRepository
import com.fikriadriansa.footballschedule.invisible
import com.fikriadriansa.footballschedule.model.Event
import com.fikriadriansa.footballschedule.presenter.MainPresenter
import com.fikriadriansa.footballschedule.view.MainView
import com.fikriadriansa.footballschedule.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_last_match.*
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
    private lateinit var adapterLastMatch: LastMatchAdapter

    override fun showLoading() {
        progress_lastmatch.visible()
    }

    override fun hideLoading() {
        progress_lastmatch.invisible()
    }

    override fun showListMatch(data: List<Event>) {
        swipe_lastmatch.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapterLastMatch.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_last_match, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapterLastMatch = LastMatchAdapter(events)
        rvLastMatch.adapter = adapterLastMatch

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        presenter.getListLastMatch()
        swipe_lastmatch.onRefresh {
            presenter.getListLastMatch()
        }
    }

    private fun itemClicked(event: Event){
        startActivity<MatchDetailActivity>(

        )
    }

}
