package com.fsm.superpomodoro

import com.fsm.superpomodoro.model.Pomodoro
import java.util.*
import kotlin.random.Random

object TestUtils {

    fun createPomodoros(size: Int): List<Pomodoro> {
        val pomodoros = emptyList<Pomodoro>().toMutableList()
        for (i in 1..size) {
            pomodoros.add(Pomodoro().apply {
                runTime = Random.nextLong(1, 25) * 60
                finished = (runTime % 2) == 0L
                creationDate = Date()
            })
        }
        return pomodoros
    }
}