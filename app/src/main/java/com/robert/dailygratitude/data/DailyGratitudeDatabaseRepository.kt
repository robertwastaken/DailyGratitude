package com.robert.dailygratitude.data

import com.robert.dailygratitude.db.EntryCard
import com.robert.dailygratitude.db.EntryCardDao
import com.robert.dailygratitude.ui.components.EntryCardModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DailyGratitudeDatabaseRepository @Inject constructor(
    val entriesDao: EntryCardDao
) : DailyGratitudeRepository {

    override fun getEntries(): Flow<List<EntryCardModel>> {
        val entries = entriesDao.getAll()
        return entries.map { list ->
            list.map {
                EntryCardModel(
                    date = it.date,
                    description = it.description,
                    images = it.images?.firstOrNull(),
                    tags = it.tags
                )
            }
        }
    }

    override fun insertAll(vararg entries: EntryCard) {
        entriesDao.insertAll(*entries)
    }
}