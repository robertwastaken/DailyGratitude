package com.robert.dailygratitude.data

import com.robert.dailygratitude.db.EntryCard
import com.robert.dailygratitude.ui.components.EntryCardDetailsModel
import com.robert.dailygratitude.ui.components.EntryCardModel
import kotlinx.coroutines.flow.Flow

interface DailyGratitudeRepository {
    fun getEntries(): Flow<List<EntryCardModel>>

    fun getEntry(entryId: Int): EntryCardDetailsModel

    fun updateEntry(entryCard: EntryCard)

    fun insertAll(vararg entries: EntryCard)

    fun delete(entryCard: EntryCard)
}