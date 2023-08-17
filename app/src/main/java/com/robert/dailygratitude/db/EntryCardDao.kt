package com.robert.dailygratitude.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EntryCardDao {
    @Query("SELECT * FROM entries")
    fun getAll(): List<EntryCard>

    @Insert
    fun insertAll(vararg entries: EntryCard)
}