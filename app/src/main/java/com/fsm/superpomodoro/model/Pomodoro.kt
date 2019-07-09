package com.fsm.superpomodoro.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.fsm.superpomodoro.data.DateTimeConverter
import java.text.SimpleDateFormat
import java.util.*

@Entity
@TypeConverters(DateTimeConverter::class)
class Pomodoro {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @ColumnInfo(name = "run_time")
    var runTime: Long = 0

    @ColumnInfo(name = "finished")
    var finished: Boolean = false

    @ColumnInfo(name = "creation_date")
    var creationDate: Date = Date()

    fun getRunTimeText(): String {
        return DateTimeConverter.getFormattedTime(runTime)
    }

    fun getStatusText(): String {
        return if (finished) "Finished" else "Stopped"
    }

    fun getCreationText(): String {
        var timeDelta = Date().time - (creationDate.time)
        timeDelta /= 1000

        if (timeDelta < 60) {
            return "${timeDelta}s ago"
        }

        timeDelta /= 60
        if (timeDelta < 60) {
            return "${timeDelta}m ago"
        }

        timeDelta /= 24
        if (timeDelta <= 5) {
            return "${timeDelta}h ago"
        }

        val formatter = SimpleDateFormat("ha", Locale.getDefault())
        return formatter.format(creationDate).toLowerCase()
    }

    companion object {
        const val TOTAL_TIME = 1_500_000L
        const val REST_TIME = 300_000L
    }
}