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
import com.robert.dailygratitude.ui.components.EntryCardDetailsEditing
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
                onEditClick = { viewModel.startEditing() },
                onDeleteClick = {
                    viewModel.onDeleteClick(
                        onFinishCallback = onBack,
                        showSnackbar = { message -> showSnackbar(message) }
                    )
                }
            )
        }
    ) { paddingValues ->
        when (uiState) {
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

            is DetailsScreenState.DataLoaded -> {
                EntryCardDetails(
                    modifier = Modifier.padding(paddingValues),
                    model = (uiState as DetailsScreenState.DataLoaded).entry
                )
            }

            is DetailsScreenState.EditingData -> {
                EntryCardDetailsEditing(
                    modifier = Modifier.padding(paddingValues),
                    isAddingNewEntry = (uiState as DetailsScreenState.EditingData).isAddingNewEntry,
                    description = viewModel.description,
                    updateDescription = { input -> viewModel.updateDescription(input) },
                    model = (uiState as DetailsScreenState.EditingData).entry,
                    addTag = { newTag -> viewModel.addTag(newTag) },
                    newTag = viewModel.newTag,
                    updateNewTag = { input -> viewModel.updateNewTag(input) },
                    removeTag = { index -> viewModel.removeTag(index) },
                    onSaveClick = { viewModel.onSaveClick() },
                    onAddClick = {
                        viewModel.onAddClick(
                            onFinishCallBack = onBack
                        )
                    },
                    addImage = { imageUri -> viewModel.addImage(imageUri) },
                    removeImage = { index -> viewModel.removeImage(index) }
                )
            }
        }
    }
}