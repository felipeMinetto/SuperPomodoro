package com.fsm.superpomodoro.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.fsm.superpomodoro.databinding.HistorySectionHeaderViewBinding

class HeaderViewHolder(private val binding: HistorySectionHeaderViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(title: String) {
        binding.title = title
    }
}