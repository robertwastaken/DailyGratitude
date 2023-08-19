package com.robert.dailygratitude.data

import com.robert.dailygratitude.db.EntryCard
import com.robert.dailygratitude.db.EntryCardDao
import com.robert.dailygratitude.ui.components.EntryCardDetailsModel
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
                    id = it.id,
                    date = it.date,
                    description = it.description,
                    image = it.images?.firstOrNull(),
                    tags = it.tags
                )
            }
        }
    }

    override fun getEntry(entryId: Int): EntryCardDetailsModel {
        val entry = entriesDao.getEntry(entryId)
        return EntryCardDetailsModel(
            id = entry.id,
            date = entry.date,
            description = entry.description,
            images = if (entry.images?.isEmpty() == true)
                null
            else
                entry.images,
            tags = entry.tags
        )
    }

    override fun updateEntry(entryCard: EntryCard) {
        entriesDao.updateEntry(entryCard)
    }

    override fun insertAll(vararg entries: EntryCard) {
        entriesDao.insertAll(*entries)
    }

    override fun delete(entryCard: EntryCard) {
        entriesDao.delete(entryCard)
    }
}