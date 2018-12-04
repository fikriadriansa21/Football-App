package com.fikriadriansa.footballschedule.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.fikriadriansa.footballschedule.R
import com.fikriadriansa.footballschedule.activity.MatchDetailActivity
import com.fikriadriansa.footballschedule.adapter.EventAdapter
import com.fikriadriansa.footballschedule.api.ApiRepository
import com.fikriadriansa.footballschedule.utils.invisible
import com.fikriadriansa.footballschedule.model.Event
import com.fikriadriansa.footballschedule.presenter.MainPresenter
import com.fikriadriansa.footballschedule.utils.CoroutineContextProvider
import com.fikriadriansa.footballschedule.utils.EventUtils
import com.fikriadriansa.footballschedule.view.MainView
import com.fikriadriansa.footballschedule.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_event_match.*
import kotlinx.android.synthetic.main.fragment_team.*
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
class NextMatchFragment : Fragment(), MainView {


    private var events: MutableList<Event> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapterNextMatch: EventAdapter
    private lateinit var leagueName: String
    private lateinit var leagueId: String

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
        adapterNextMatch.notifyDataSetChanged()
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

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner_event.adapter = spinnerAdapter

        adapterNextMatch = EventAdapter(
            events
        ) { events:Event->partItemClicked(events)}
        rvEvent.adapter = adapterNextMatch

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        spinner_event.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner_event.selectedItem.toString()
                when(leagueName){
                    EventUtils.ENGLISH_PREMIER_LEAGUE_NAME ->{
                        leagueId = EventUtils.ENGLISH_PREMIER_LEAGUE_ID
                    }
                    EventUtils.ENGLISH_LEAGUE_CHAMPIONSHIP_NAME -> {
                        leagueId = EventUtils.ENGLISH_LEAGUE_CHAMPIONSHIP_ID
                    }
                    EventUtils.FRENCH_LIGUE_1_NAME -> {
                        leagueId = EventUtils.FRENCH_LIGUE_1_ID
                    }
                    EventUtils.GERMAN_BUNDESLIGA_NAME -> {
                        leagueId = EventUtils.GERMAN_BUNDESLIGA_ID
                    }
                    EventUtils.ITALIAN_SERIE_A_NAME -> {
                        leagueId = EventUtils.ITALIAN_SERIE_A_ID
                    }
                    EventUtils.SPANISH_LA_LIGA_NAME -> {
                        leagueId = EventUtils.SPANISH_LA_LIGA_ID
                    }
                }
                presenter.getListNextMatch(leagueId)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

//        presenter.getListNextMatch()
        swipe_event.onRefresh {
            presenter.getListNextMatch(leagueId)
        }
    }

    private fun partItemClicked(events: Event) {
        startActivity<MatchDetailActivity>(
            MatchDetailActivity.DATA to events

        )
    }


}
