package com.robert.dailygratitude.ui.homescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.robert.dailygratitude.ui.components.EntryCard
import com.robert.dailygratitude.ui.components.HomeTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    onCardClick: (Int) -> Unit,
    onAddClick: () -> Unit,
    showSnackbar: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Surface {
        Scaffold(
            topBar = {
                HomeTopAppBar(
                    onAddClick = onAddClick,
                    showSnackbar = { message -> showSnackbar(message) }
                )
            }
        ) { paddingValues ->
            when (uiState) {
                is HomeScreenState.DataLoaded -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .padding(horizontal = 16.dp),
                        contentPadding = PaddingValues(vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        (uiState as HomeScreenState.DataLoaded).let { state ->
                            if (state.data[HomeScreenViewModel.THIS_WEEK]?.isNotEmpty() == true) {
                                item {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        color = Color.Gray,
                                        textAlign = TextAlign.Start,
                                        fontWeight = FontWeight.Bold,
                                        text = "This week"
                                    )
                                }

                                items(
                                    state.data[HomeScreenViewModel.THIS_WEEK] ?: emptyList()
                                ) { entry ->
                                    EntryCard(
                                        model = entry,
                                        onClick = { onCardClick(entry.id) }
                                    )
                                }
                            }

                            if (state.data[HomeScreenViewModel.LAST_MONTH]?.isNotEmpty() == true) {
                                item {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        color = Color.Gray,
                                        textAlign = TextAlign.Start,
                                        fontWeight = FontWeight.Bold,
                                        text = "Last month"
                                    )
                                }

                                items(
                                    state.data[HomeScreenViewModel.LAST_MONTH] ?: emptyList()
                                ) { entry ->
                                    EntryCard(
                                        model = entry,
                                        onClick = { onCardClick(entry.id) }
                                    )
                                }
                            }

                            if (state.data[HomeScreenViewModel.OLDER]?.isNotEmpty() == true) {
                                item {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        color = Color.Gray,
                                        textAlign = TextAlign.Start,
                                        fontWeight = FontWeight.Bold,
                                        text = "Older"
                                    )
                                }

                                items(
                                    state.data[HomeScreenViewModel.OLDER] ?: emptyList()
                                ) { entry ->
                                    EntryCard(
                                        model = entry,
                                        onClick = { onCardClick(entry.id) }
                                    )
                                }
                            }
                        }
                    }
                }

                HomeScreenState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}