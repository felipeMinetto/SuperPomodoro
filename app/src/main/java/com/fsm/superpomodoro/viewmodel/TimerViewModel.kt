package com.fsm.superpomodoro.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fsm.superpomodoro.data.DateTimeConverter
import com.fsm.superpomodoro.data.PomodoroRepository
import com.fsm.superpomodoro.model.Pomodoro.Companion.REST_TIME
import com.fsm.superpomodoro.model.Pomodoro.Companion.TOTAL_TIME
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*

class TimerViewModel : ViewModel(), KoinComponent {

    private val repository: PomodoroRepository by inject()

    private var startingTime = 0L
    val running: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().apply {
            value = false
        }
    }
    val resting: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().apply {
            value = false
        }
    }
    val timerText: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            value = DateTimeConverter.getFormattedTime(TOTAL_TIME)
        }
    }

    private val timer = object : CountDownTimer(TOTAL_TIME, 1000) {
        override fun onFinish() {
            running.value = false
            GlobalScope.launch { repository.createEntry(startingTime) }
            resting.value = true
            restTimer.start()
        }

        override fun onTick(millisUntilFinished: Long) {
            timerText.value = DateTimeConverter.getFormattedTime(millisUntilFinished)
        }
    }

    private val restTimer = object : CountDownTimer(REST_TIME, 1000) {
        override fun onFinish() {
            cycleState()
        }

        override fun onTick(millisUntilFinished: Long) {
            timerText.value = DateTimeConverter.getFormattedTime(millisUntilFinished)
        }
    }

    fun cycleState() {
        if (resting.value!!) {
            stopTimer()
            resting.value = false
            restTimer.cancel()
        } else {
            if (running.value!!) {
                stopTimer()
            } else {
                startTimer()
            }
        }
    }

    private fun startTimer() {
        running.value = true
        timer.start()
        startingTime = Date().time
    }

    private fun stopTimer() {
        running.value = false
        if (resting.value!!.not())
            GlobalScope.launch { repository.createEntry(startingTime) }
        timer.onTick(TOTAL_TIME)
        timer.cancel()
    }
}