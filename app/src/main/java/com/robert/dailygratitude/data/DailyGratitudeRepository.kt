package com.robert.dailygratitude.data

import com.robert.dailygratitude.ui.components.EntryCardModel

interface DailyGratitudeRepository {
    fun getEntries(): List<EntryCardModel>
}