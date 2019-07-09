package com.fsm.superpomodoro

import androidx.room.Room
import com.fsm.superpomodoro.data.PomodoroDatabase
import com.fsm.superpomodoro.data.PomodoroRepository
import org.koin.android.ext.koin.androidContext

val module = org.koin.dsl.module {
    single {
        Room.databaseBuilder(
            androidContext(),
            PomodoroDatabase::class.java,
            "Pomodoro"
        ).build()
    }
    single { get<PomodoroDatabase>().pomodoroDao() }
    single { PomodoroRepository(get()) }
}