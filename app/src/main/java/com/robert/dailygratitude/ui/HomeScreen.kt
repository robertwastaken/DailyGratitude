package com.robert.dailygratitude.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.robert.dailygratitude.ui.components.EntryCard
import com.robert.dailygratitude.ui.components.EntryCardModel
import com.robert.dailygratitude.ui.components.HomeTopAppBar
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onCardClick: (Int) -> Unit,
    showSnackbar: (String) -> Unit
) {
    val list = listOf(
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

    Scaffold(
        topBar = {
            HomeTopAppBar(
                showSnackbar = { message -> showSnackbar(message) }
            )
        }
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
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Gray,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    text = "This week"
                )
            }
            itemsIndexed(list) { index, entry ->
                EntryCard(
                    model = entry,
                    onClick = { onCardClick(index) }
                )
            }

            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Gray,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    text = "Last month"
                )
            }

            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Gray,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    text = "Older"
                )
            }
        }
    }
}