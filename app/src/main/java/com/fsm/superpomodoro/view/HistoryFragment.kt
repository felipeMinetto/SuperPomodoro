package com.fsm.superpomodoro.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.fsm.superpomodoro.R
import com.fsm.superpomodoro.databinding.FragmentHistoryBinding
import com.fsm.superpomodoro.view.adapter.PomodoroAdapter
import com.fsm.superpomodoro.viewmodel.HistoryViewModel

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private val adapter by lazy { PomodoroAdapter(emptyList()) }
    private var model: HistoryViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_history, container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.rvHistory.adapter = adapter
        model = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        model!!.pomodoros.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })

        return binding.root
    }

    fun loadData() {
        model?.loadPomodoros()
    }
}