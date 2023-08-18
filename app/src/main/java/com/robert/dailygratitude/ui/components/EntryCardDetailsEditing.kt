package com.robert.dailygratitude.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SuggestionChip
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.robert.dailygratitude.R
import com.robert.dailygratitude.ui.theme.Typography
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun EntryCardDetailsEditing(
    modifier: Modifier = Modifier,
    model: EntryCardDetailsModel,
    description: String,
    updateDescription: (String) -> Unit,
    addTag: (String) -> Unit,
    newTag: String,
    updateNewTag: (String) -> Unit,
    removeTag: (Int) -> Unit,
    onSaveClick: () -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    )
    {
        item {
            Text(
                style = Typography.bodySmall.copy(color = Color.Gray),
                text = SimpleDateFormat("MMMM dd yyyy", Locale.US).format(model.date)
            )

            val maxLength = 50
            val focusManager = LocalFocusManager.current

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = description,
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                onValueChange = { input ->
                    if (input.length <= maxLength)
                        updateDescription(input)
                },
                textStyle = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                label = {
                    Text(
                        style = Typography.bodySmall,
                        text = "Description"
                    )
                }
            )

            Text(
                text = "${description.length} / $maxLength",
                textAlign = TextAlign.End,
                style = Typography.bodySmall.copy(color = Color.Gray),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp)
            )

            model.images?.let {
                if (model.images.size == 1) {
                    AsyncImage(
                        modifier = Modifier.fillMaxWidth(),
                        model = model.images.first(),
                        placeholder = painterResource(R.drawable.ic_launcher_background),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth
                    )
                } else {
                    val pagerState = rememberPagerState(pageCount = { model.images.size })
                    val coroutineScope = rememberCoroutineScope()

                    HorizontalPager(
                        modifier = Modifier.height(250.dp),
                        state = pagerState,
                        pageSpacing = 8.dp
                    ) { page ->
                        AsyncImage(
                            modifier = Modifier.fillMaxWidth(),
                            model = model.images[page],
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
                        itemsIndexed(model.images) { index, image ->
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

            Text(
                style = Typography.bodySmall,
                text = "Tap tags to remove them"
            )

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                model.tags?.forEachIndexed { index, tag ->
                    SuggestionChip(
                        onClick = { removeTag(index) },
                        label = {
                            Text(
                                text = tag
                            )
                        }
                    )
                }
            }

            Text(
                style = Typography.bodySmall,
                text = "Add new tag:"
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                    addTag("#$newTag")
                    updateNewTag("")
                }),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                value = newTag,
                onValueChange = { input -> updateNewTag(input) }
            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = onSaveClick
                ) {
                    Text(
                        text = "Save"
                    )
                }
            }
        }
    }
}