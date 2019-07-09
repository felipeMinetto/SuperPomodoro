package com.fsm.superpomodoro.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.fsm.superpomodoro.view.HistoryFragment
import com.fsm.superpomodoro.view.TimerFragment

class PomodoroPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    private var timerFragment: TimerFragment = TimerFragment()
    private var historyFragment: HistoryFragment = HistoryFragment()

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0)
            "Timer"
        else
            "History"
    }

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            timerFragment
        } else {
            historyFragment
        }
    }

    override fun getCount(): Int {
        return 2
    }

    fun updatePage(position: Int) {
        if (position == 1) {
            historyFragment.loadData()
        }
    }
}