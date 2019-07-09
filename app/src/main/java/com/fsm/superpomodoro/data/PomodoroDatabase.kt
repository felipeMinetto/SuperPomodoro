package com.fsm.superpomodoro.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fsm.superpomodoro.model.Pomodoro

@Database(entities = [Pomodoro::class], version = 1)
abstract class PomodoroDatabase : RoomDatabase() {
    abstract fun pomodoroDao(): PomodoroDAO
}