package com.robert.dailygratitude.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.robert.dailygratitude.ui.components.EntryDetailsTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryDetailsScreen(
    entryId: String,
    onBack: () -> Unit,
    showSnackbar: (String) -> Unit
) {
    Scaffold(
        topBar = {
            EntryDetailsTopAppBar(
                onBack = onBack,
                showSnackbar = { message -> showSnackbar(message) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("$entryId Screen")
        }
    }
}