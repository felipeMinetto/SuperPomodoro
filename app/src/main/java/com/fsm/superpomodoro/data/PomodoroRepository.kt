package com.fsm.superpomodoro.data

import com.fsm.superpomodoro.model.Pomodoro
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class PomodoroRepository(private val pomodoroDAO: PomodoroDAO) {

    suspend fun createEntry(timerStart: Long) {
        val pomodoro = Pomodoro().apply {
            creationDate = Date()
            runTime = creationDate.time - timerStart
            finished = runTime >= Pomodoro.TOTAL_TIME
        }

        pomodoroDAO.insert(pomodoro)
    }

    suspend fun loadPomodoros(): List<Pomodoro> {
        return withContext(Dispatchers.Default) {
            val list = emptyList<Pomodoro>().toMutableList()
            var prevDate: Date? = null
            val pomoMap = pomodoroDAO.getAll().groupBy {
                DateTimeConverter.getHourlessDate(it.creationDate)
            }
            pomoMap.keys.forEach { date ->
                if (prevDate == null || prevDate != date) {
                    val headerPomo = Pomodoro().apply {
                        creationDate = date
                        id = 0
                    }
                    list.add(headerPomo)
                }
                list.addAll(pomoMap.getValue(date))
                prevDate = date
            }

            list
        }
    }

}