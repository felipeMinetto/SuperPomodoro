package com.fsm.superpomodoro.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fsm.superpomodoro.data.PomodoroRepository
import com.fsm.superpomodoro.model.Pomodoro
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

class HistoryViewModel : ViewModel(), KoinComponent {

    private val repository: PomodoroRepository by inject()

    val pomodoros: MutableLiveData<List<Pomodoro>> by lazy { MutableLiveData<List<Pomodoro>>() }

    fun loadPomodoros() {
        GlobalScope.launch {
            val list = repository.loadPomodoros()
            withContext(Dispatchers.Main) {
                pomodoros.value = list
            }
        }
    }
}