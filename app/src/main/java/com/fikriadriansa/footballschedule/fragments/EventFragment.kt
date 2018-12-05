package com.fikriadriansa.footballschedule.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.view.*
import com.fikriadriansa.footballschedule.R
import com.fikriadriansa.footballschedule.activity.MatchDetailActivity
import com.fikriadriansa.footballschedule.adapter.EventAdapter
import com.fikriadriansa.footballschedule.adapter.EventPagerAdapter
import com.fikriadriansa.footballschedule.api.ApiRepository
import com.fikriadriansa.footballschedule.model.Event
import com.fikriadriansa.footballschedule.presenter.MainPresenter
import com.fikriadriansa.footballschedule.view.MainView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_event.*
import kotlinx.android.synthetic.main.fragment_event_match.*
import org.jetbrains.anko.support.v4.startActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class EventFragment : Fragment(), MainView {
    override fun showSearchMatch(data: List<Event>) {
        swipe_event.isRefreshing = false
        event.clear()
        event.addAll(data)
        adapter.notifyDataSetChanged()

    }

    private lateinit var adapter: EventAdapter
    private var event: MutableList<Event> = mutableListOf()
    private lateinit var presenter: MainPresenter

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showListMatch(data: List<Event>) {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)
        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        childFragmentManager.let { view_pager_event.adapter = EventPagerAdapter(it) }
        tab_event.setupWithViewPager(view_pager_event)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu,menu)
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(eventName: String?): Boolean {
                view_pager_event.visibility = View.GONE
                tab_event.visibility = View.GONE
                presenter.getSearchMatch(eventName)
                adapter = EventAdapter(event) { event: Event -> partItemClicked(event) }
                rvSearchEvent.adapter = adapter
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

    private fun partItemClicked(events: Event) {
        startActivity<MatchDetailActivity>(
            MatchDetailActivity.DATA to events
        )
    }

}
