package com.robert.dailygratitude.data

import com.robert.dailygratitude.ui.components.EntryCardModel
import java.util.Calendar
import javax.inject.Inject

class DailyGratitudeRepository @Inject constructor() {

    private val entries = listOf(
        EntryCardModel(
            date = Calendar.getInstance().apply { set(2023, 8, 17) }.time,
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
            date = Calendar.getInstance().apply { set(2023, 8, 17) }.time,
            description = "This the second one woo",
            tags = listOf("#one", "#two", "#three")
        ),
        EntryCardModel(
            date = Calendar.getInstance().apply { set(2023, 8, 17) }.time,
            description = "Third one right here let's go",
            images = "https://picsum.photos/300/200"
        ),
        EntryCardModel(
            date = Calendar.getInstance().apply { set(2023, 8, 17) }.time,
            description = "Last entry"
        )
    )

    fun getEntries(): List<EntryCardModel> = entries
}