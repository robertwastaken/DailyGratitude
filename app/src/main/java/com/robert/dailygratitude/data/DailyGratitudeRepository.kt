package com.robert.dailygratitude.data

import com.robert.dailygratitude.db.EntryCard
import com.robert.dailygratitude.ui.components.EntryCardModel

interface DailyGratitudeRepository {
    fun getEntries(): List<EntryCardModel>

    fun insertAll(vararg entries: EntryCard)
}