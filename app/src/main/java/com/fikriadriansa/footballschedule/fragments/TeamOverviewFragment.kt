package com.fikriadriansa.footballschedule.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.fikriadriansa.footballschedule.R
import com.fikriadriansa.footballschedule.adapter.PlayerAdapter
import com.fikriadriansa.footballschedule.adapter.TeamsAdapter
import com.fikriadriansa.footballschedule.model.Player
import com.fikriadriansa.footballschedule.model.Team
import com.fikriadriansa.footballschedule.model.TeamDetail
import com.fikriadriansa.footballschedule.presenter.PlayerPresenter
import com.fikriadriansa.footballschedule.presenter.TeamsPresenter
import com.fikriadriansa.footballschedule.view.TeamView
import kotlinx.android.synthetic.main.fragment_team_overview.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "description"

/**
 * A simple [Fragment] subclass.
 *
 */
class TeamOverviewFragment : Fragment() {
    private var getOverview: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            getOverview = it.getString(ARG_PARAM1)

        }
    }


    companion object {
        private var getOverview: String? = null
        private var  overview: Team? = null

        @JvmStatic
        fun newInstance(overview: String) =
            PlayerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, overview)
                }
            }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_overview, container, false)


    }




    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tv_overview.text = getOverview
    }

}
