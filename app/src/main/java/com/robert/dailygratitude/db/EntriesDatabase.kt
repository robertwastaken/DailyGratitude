package com.robert.dailygratitude.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [EntryCard::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class EntriesDatabase : RoomDatabase() {
    abstract fun entryCardDao(): EntryCardDao
}