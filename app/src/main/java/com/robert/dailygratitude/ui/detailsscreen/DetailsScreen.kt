package com.robert.dailygratitude.ui.detailsscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.robert.dailygratitude.ui.components.EntryCardDetails
import com.robert.dailygratitude.ui.components.EntryDetailsTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    viewModel: DetailsScreenViewModel = hiltViewModel(),
    onBack: () -> Unit,
    showSnackbar: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            EntryDetailsTopAppBar(
                onBack = onBack,
                showSnackbar = { message -> showSnackbar(message) }
            )
        }
    ) { paddingValues ->
        when (uiState) {
            is DetailsScreenState.DataLoaded -> {
                EntryCardDetails(
                    modifier = Modifier.padding(paddingValues),
                    model = (uiState as DetailsScreenState.DataLoaded).entry
                )
            }

            DetailsScreenState.Loading -> {
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