package com.robert.dailygratitude.db

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun fromStringList(list: List<String>?): String = Json.encodeToString(list ?: emptyList())

    @TypeConverter
    fun toStringList(value: String?): List<String> =
        value?.let { Json.decodeFromString(it) } ?: emptyList()
}