package com.robert.dailygratitude.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SuggestionChip
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class,
)
@Composable
fun EntryCardDetails(
    modifier: Modifier = Modifier,
    model: EntryCardDetailsModel
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    )
    {
        // Title
        DetailsTitle(
            date = model.date,
            description = model.description
        )

        // Images
        model.images?.let {
            DetailsImages(
                images = it
            )
        }

        // Tags
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

@Composable
fun DetailsTitle(
    date: Date,
    description: String
) {
    Text(
        style = Typography.bodySmall.copy(color = Color.Gray),
        text = SimpleDateFormat("MMMM dd yyyy", Locale.US).format(date)
    )

    Text(
        style = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
        text = description
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailsImages(
    images: List<String>
) {
    if (images.size == 1) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = images.first(),
            placeholder = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
    } else {
        val pagerState = rememberPagerState(pageCount = { images.size })
        val coroutineScope = rememberCoroutineScope()

        HorizontalPager(
            modifier = Modifier.height(250.dp),
            state = pagerState,
            pageSpacing = 8.dp
        ) { page ->
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = images[page],
                placeholder = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(images) { index, image ->
                AsyncImage(
                    modifier = Modifier
                        .width(70.dp)
                        .clickable {
                            coroutineScope.launch {
                                pagerState.scrollToPage(index)
                            }
                        },
                    model = image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

data class EntryCardDetailsModel(
    val id: Int,
    val date: Date,
    val description: String,
    val images: List<String>? = null,
    val tags: List<String>? = null
)

@Preview(
    showBackground = true
)
@Composable
fun EntryCardDetailsPreview() {
    val model = EntryCardDetailsModel(
        id = 0,
        date = Calendar.getInstance().time,
        description = "This is the description of the thinggy",
        images = listOf("one"),
        tags = listOf("#one", "#two", "#three")
    )
    EntryCardDetails(
        model = model
    )
}

@Preview(
    showBackground = true
)
@Composable
fun EntryCardDetailsWithPagerPreview() {
    val model = EntryCardDetailsModel(
        id = 0,
        date = Calendar.getInstance().time,
        description = "This is the description of the thinggy",
        images = listOf("one", "two", "three"),
        tags = listOf("#one", "#two", "#three")
    )
    EntryCardDetails(
        model = model
    )
}