package com.fsm.superpomodoro.view

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.fsm.superpomodoro.R
import com.fsm.superpomodoro.databinding.FragmentTimerBinding
import com.fsm.superpomodoro.viewmodel.TimerViewModel

class TimerFragment : Fragment() {

    private lateinit var binding: FragmentTimerBinding
    private lateinit var timerModel: TimerViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_timer, container, false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        timerModel = ViewModelProviders.of(this).get(TimerViewModel::class.java)
        binding.viewModel = timerModel

        timerModel.resting.observe(viewLifecycleOwner, Observer { resting ->
            val red = resources.getColor(R.color.colorPrimary)
            val white = resources.getColor(R.color.white)
            if (resting) {
                binding.btnAction.setBackgroundResource(R.drawable.button_background_inverted)
                binding.btnAction.compoundDrawables[0].setColorFilter(
                    red,
                    PorterDuff.Mode.SRC_ATOP
                )
                binding.txvTimer.setTextColor(white)
                binding.btnAction.setTextColor(red)
                binding.root.setBackgroundColor(red)
            } else {
                binding.btnAction.setBackgroundResource(R.drawable.button_background)
                binding.btnAction.compoundDrawables[0].setColorFilter(
                    white,
                    PorterDuff.Mode.SRC_ATOP
                )
                binding.txvTimer.setTextColor(red)
                binding.btnAction.setTextColor(white)
                binding.root.setBackgroundColor(white)
            }
        })

        return binding.root
    }
}