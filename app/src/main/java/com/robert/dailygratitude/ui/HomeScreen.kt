package com.robert.dailygratitude.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.robert.dailygratitude.ui.components.EntryCard
import com.robert.dailygratitude.ui.components.EntryCardModel
import com.robert.dailygratitude.ui.components.HomeTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onClick: (Int) -> Unit
) {
    val list = listOf(
        EntryCardModel(
            date = "17 August 2023",
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
            date = "17 August 2023",
            description = "This the second one woo",
            tags = listOf("#one", "#two", "#three")
        ),
        EntryCardModel(
            date = "17 August 2023",
            description = "Third one right here let's go",
            images = "https://picsum.photos/300/200"
        ),
        EntryCardModel(
            date = "17 August 2023",
            description = "Last entry"
        )
    )

    Scaffold(
        topBar = { HomeTopAppBar() }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(list) { index, entry ->
                EntryCard(
                    model = entry,
                    onClick = { onClick(index) }
                )
            }
        }
    }
}