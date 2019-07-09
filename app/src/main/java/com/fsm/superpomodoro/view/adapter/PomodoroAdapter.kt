package com.fsm.superpomodoro.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fsm.superpomodoro.R
import com.fsm.superpomodoro.databinding.HistoryItemViewBinding
import com.fsm.superpomodoro.databinding.HistorySectionHeaderViewBinding
import com.fsm.superpomodoro.model.Pomodoro
import java.text.SimpleDateFormat
import java.util.*

class PomodoroAdapter(private var pomodoros: List<Pomodoro>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_VIEW) {
            val binding: HistoryItemViewBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.history_item_view, parent,
                false
            )

            PomodoroViewHolder(binding)
        } else {

            val binding: HistorySectionHeaderViewBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.history_section_header_view, parent,
                false
            )

            HeaderViewHolder(binding)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (pomodoros[position].id == 0L) {
            TYPE_HEADER
        } else {
            TYPE_VIEW
        }
    }

    override fun getItemCount(): Int {
        return pomodoros.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val pomodoro = pomodoros[position]
        if (holder.itemViewType == TYPE_VIEW) {
            (holder as PomodoroViewHolder).bind(pomodoro)
        } else {
            val diff = Date().time - pomodoro.creationDate.time
            val title = when {
                diff < DAY_IN_SEC -> "Today"
                diff < 2 * DAY_IN_SEC -> "Yesterday"
                else -> {
                    val dateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT)
                    dateFormat.format(pomodoro.creationDate)
                }
            }
            (holder as HeaderViewHolder).bind(title)
        }
    }

    fun updateList(newPomodoros: List<Pomodoro>) {
        pomodoros = newPomodoros
        notifyDataSetChanged()
    }

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_VIEW = 1
        private const val DAY_IN_SEC = 24 * 60 * 60 * 1000
    }
}