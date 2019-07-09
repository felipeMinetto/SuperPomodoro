package com.fsm.superpomodoro.data

import androidx.room.TypeConverter
import java.util.*
import kotlin.math.roundToLong


class DateTimeConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    companion object {
        fun getFormattedTime(timeInMillis: Long): String {
            val millisToSec = (timeInMillis / 1000.0).roundToLong()
            val minutes: Long = millisToSec / 60
            val seconds: Long = millisToSec % 60
            return "%02d:%02d".format(minutes, seconds)
        }

        fun getHourlessDate(date: Date): Date {
            val newDate = date.time / 1000 / 60 / 60 / 24
            val hourlessDate = newDate * 24 * 60 * 60 * 1000
            return Date(hourlessDate)
        }
    }
}
