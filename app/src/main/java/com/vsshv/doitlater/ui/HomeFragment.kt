package com.vsshv.doitlater.ui

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.applandeo.materialcalendarview.CalendarView
import com.vsshv.doitlater.R

class HomeFragment: Fragment(){

    @BindView(R.id.recyclerView)
    lateinit var recycler: RecyclerView

    @BindView(R.id.calendarView)
    lateinit var calendar: CalendarView

    @BindView(R.id.fab)
    lateinit var fab: FloatingActionButton

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.frag_home, container, false)

        ButterKnife.bind(this, view)

        return view
    }

}