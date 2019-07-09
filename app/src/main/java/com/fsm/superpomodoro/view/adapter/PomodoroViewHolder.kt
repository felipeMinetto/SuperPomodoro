package com.fsm.superpomodoro.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.fsm.superpomodoro.databinding.HistoryItemViewBinding
import com.fsm.superpomodoro.model.Pomodoro

class PomodoroViewHolder(private val bindding: HistoryItemViewBinding) :
    RecyclerView.ViewHolder(bindding.root) {

    fun bind(pomodoro: Pomodoro) {
        bindding.pomodoro = pomodoro
    }
}