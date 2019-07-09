package com.fsm.superpomodoro

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.fsm.superpomodoro.data.PomodoroDatabase
import com.fsm.superpomodoro.model.Pomodoro
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class PomodoroDBTest {
    private lateinit var db: PomodoroDatabase

    @Before
    fun createDatabase() {
        db = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            PomodoroDatabase::class.java
        ).build()
    }

    @After
    fun closeDB() {
        db.close()
    }

    @Test
    fun insertPomodoroTest() {
        val pomo = Pomodoro().apply {
            runTime = 25 * 60
            finished = true
            creationDate = Calendar.getInstance().time
        }

        GlobalScope.launch {
            db.pomodoroDao().insert(pomo)
            val pomodoros = db.pomodoroDao().getAll()
            assert(pomodoros.isNotEmpty())
        }
    }

    @Test
    fun listPomodoroTest() {
        val size = 10
        val pomos = TestUtils.createPomodoros(size)

        GlobalScope.launch {
            for (pomo in pomos) {
                db.pomodoroDao().insert(pomo)
            }

            val pomodoros = db.pomodoroDao().getAll()
            Assert.assertEquals(pomodoros.size, size)
            for (i in 0 until size) {
                Assert.assertEquals(pomos[i].finished, pomodoros[i].finished)
                Assert.assertEquals(pomos[i].runTime, pomodoros[i].runTime)
                Assert.assertEquals(pomos[i].creationDate, pomodoros[i].creationDate)
                Assert.assertNotEquals(pomos[i].id, pomodoros[i].id)
            }
        }
    }
}