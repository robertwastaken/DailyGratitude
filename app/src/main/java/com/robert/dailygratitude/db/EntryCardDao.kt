package com.robert.dailygratitude.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryCardDao {
    @Query("SELECT * FROM entries")
    fun getAll(): Flow<List<EntryCard>>

    @Insert
    fun insertAll(vararg entries: EntryCard)
}