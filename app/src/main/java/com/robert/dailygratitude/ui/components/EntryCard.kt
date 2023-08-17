package com.robert.dailygratitude.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SuggestionChip
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.robert.dailygratitude.R
import com.robert.dailygratitude.ui.theme.Typography
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun EntryCard(
    modifier: Modifier = Modifier,
    model: EntryCardModel,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        )
        {
            Text(
                style = Typography.bodySmall.copy(color = Color.Gray),
                text = SimpleDateFormat("MMMM dd yyyy", Locale.US).format(model.date)
            )

            Text(
                style = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                text = model.description
            )

            model.images?.let {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = "https://picsum.photos/300/200",
                    placeholder = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
            }

            model.tags?.let {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    it.forEach { tag ->
                        SuggestionChip(
                            onClick = { },
                            label = {
                                Text(
                                    text = tag
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

data class EntryCardModel(
    val date: Date,
    val description: String,
    val images: String? = null,
    val tags: List<String>? = null
)

@Preview
@Composable
fun EntryCardPreview() {
    val model = EntryCardModel(
        date = Calendar.getInstance().time,
        description = "This is the description of the thinggy",
        images = "images",
        tags = listOf("#one", "#two", "#three")
    )
    EntryCard(
        model = model,
        onClick = {}
    )
}