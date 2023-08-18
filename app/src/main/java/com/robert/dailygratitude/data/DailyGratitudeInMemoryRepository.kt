package com.robert.dailygratitude.data

import com.robert.dailygratitude.db.EntryCard
import com.robert.dailygratitude.ui.components.EntryCardDetailsModel
import com.robert.dailygratitude.ui.components.EntryCardModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Calendar
import javax.inject.Inject

class DailyGratitudeInMemoryRepository @Inject constructor() : DailyGratitudeRepository {

    private val entries = listOf(
        EntryCard(
            id = 0,
            date = Calendar.getInstance().apply { set(2023, 7, 17) }.time,
            description = "This is the first entry",
            images = listOf(
                "https://picsum.photos/300/200",
                "https://picsum.photos/300/200",
                "https://picsum.photos/300/200"
            ),
            tags = listOf(
                "#lots",
                "#of",
                "#tags",
                "#on",
                "#this",
                "#one",
                "#in",
                "#order",
                "#to",
                "#see",
                "#multiple",
                "#rows"
            )
        ),
        EntryCard(
            id = 1,
            date = Calendar.getInstance().apply { set(2023, 7, 10) }.time,
            description = "This the second one woo",
            tags = listOf("#one", "#two", "#three")
        ),
        EntryCard(
            id = 2,
            date = Calendar.getInstance().apply { set(2023, 6, 29) }.time,
            description = "Third one right here let's go",
            images = listOf("https://picsum.photos/300/200")
        ),
        EntryCard(
            id = 3,
            date = Calendar.getInstance().apply { set(2023, 3, 2) }.time,
            description = "Last entry"
        )
    )

    private val entriesFlow = MutableStateFlow(entries.map {
        EntryCardModel(
            id = it.id,
            date = it.date,
            description = it.description,
            image = it.images?.firstOrNull(),
            tags = it.tags
        )
    })

    override fun getEntries(): Flow<List<EntryCardModel>> = entriesFlow

    override fun getEntry(entryId: Int): EntryCardDetailsModel {
        val entry = entries.first { it.id == entryId }
        return EntryCardDetailsModel(
            id = entry.id,
            date = entry.date,
            description = entry.description,
            images = entry.images,
            tags = entry.tags
        )
    }

    override fun insertAll(vararg entries: EntryCard) {}
}