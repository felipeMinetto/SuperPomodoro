package com.fsm.superpomodoro.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fsm.superpomodoro.model.Pomodoro

@Dao
interface PomodoroDAO {
    @Query("SELECT * FROM pomodoro")
    suspend fun getAll(): List<Pomodoro>

    @Insert
    suspend fun insert(pomodoro: Pomodoro)
}