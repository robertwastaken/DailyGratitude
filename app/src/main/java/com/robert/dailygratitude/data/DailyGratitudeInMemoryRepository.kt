package com.robert.dailygratitude.data

import com.robert.dailygratitude.db.EntryCard
import com.robert.dailygratitude.ui.components.EntryCardModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Calendar
import javax.inject.Inject

class DailyGratitudeInMemoryRepository @Inject constructor() : DailyGratitudeRepository {

    private val entries = listOf(
        EntryCardModel(
            date = Calendar.getInstance().apply { set(2023, 7, 17) }.time,
            description = "This is the first entry",
            images = "https://picsum.photos/300/200",
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
        EntryCardModel(
            date = Calendar.getInstance().apply { set(2023, 7, 10) }.time,
            description = "This the second one woo",
            tags = listOf("#one", "#two", "#three")
        ),
        EntryCardModel(
            date = Calendar.getInstance().apply { set(2023, 6, 29) }.time,
            description = "Third one right here let's go",
            images = "https://picsum.photos/300/200"
        ),
        EntryCardModel(
            date = Calendar.getInstance().apply { set(2023, 3, 2) }.time,
            description = "Last entry"
        )
    )

    private val entriesFlow = MutableStateFlow(entries)

    override fun getEntries(): Flow<List<EntryCardModel>> = entriesFlow

    override fun insertAll(vararg entries: EntryCard) {}
}