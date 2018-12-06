package com.fikriadriansa.footballschedule.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.fikriadriansa.footballschedule.R
import com.fikriadriansa.footballschedule.R.array.league
import com.fikriadriansa.footballschedule.activity.TeamDetailActivity
import com.fikriadriansa.footballschedule.adapter.EventAdapter
import com.fikriadriansa.footballschedule.adapter.TeamsAdapter
import com.fikriadriansa.footballschedule.api.ApiRepository
import com.fikriadriansa.footballschedule.model.Event
import com.fikriadriansa.footballschedule.model.Team
import com.fikriadriansa.footballschedule.presenter.TeamsPresenter
import com.fikriadriansa.footballschedule.utils.invisible
import com.fikriadriansa.footballschedule.utils.visible
import com.fikriadriansa.footballschedule.view.TeamView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_event.*
import kotlinx.android.synthetic.main.fragment_team.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class TeamFragment : Fragment(),TeamView {

    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamsPresenter
    private lateinit var adapter: TeamsAdapter
    private lateinit var leagueName: String

    override fun showLoading() {
        progress_team.visible()
    }

    override fun hideLoading() {
        progress_team.invisible()
    }

    override fun showTeamList(data: List<Team>) {
        swipe_team.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val spinnerItems = resources.getStringArray(league)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner_team.adapter = spinnerAdapter
        setHasOptionsMenu(true)

        adapter = TeamsAdapter(teams) {
            context?.startActivity<TeamDetailActivity>(
                "id" to "${it.teamId}",
                "teamName" to "${it.teamName}")
        }


        rvTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamsPresenter(this, request, gson)
        spinner_team.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner_team.selectedItem.toString()
                presenter.getTeamList(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swipe_team.onRefresh {
            presenter.getTeamList(leagueName)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu,menu)
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(eventName: String?): Boolean {
                spinner_team.visibility = View.GONE
                presenter.getSearchTeams(eventName)
                adapter = TeamsAdapter(teams) {
                    context?.startActivity<TeamDetailActivity>(
                        "id" to "${it.teamId}",
                        "teamName" to "${it.teamName}")
                }
                rvTeam.adapter = adapter
                return true
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })

        searchView.setOnCloseListener {
            view_pager_event.visibility = View.VISIBLE
            tab_event.visibility = View.VISIBLE
            true
        }
    }

}
