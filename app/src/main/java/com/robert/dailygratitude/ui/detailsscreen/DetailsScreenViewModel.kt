package com.robert.dailygratitude.ui.detailsscreen

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robert.dailygratitude.data.DailyGratitudeRepository
import com.robert.dailygratitude.db.EntryCard
import com.robert.dailygratitude.di.DatabaseRepository
import com.robert.dailygratitude.ui.components.EntryCardDetailsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
//    @InMemoryRepository
    @DatabaseRepository
    val dailyGratitudeRepository: DailyGratitudeRepository
) : ViewModel() {

    private val entryId: String = checkNotNull(savedStateHandle["entryId"])
    private val isNewEntry: Boolean = checkNotNull(savedStateHandle["newEntry"])

    init {
        viewModelScope.launch(Dispatchers.IO) {
            if (isNewEntry) {
                addNewEntry()
            } else {
                loadEntry()
            }
        }
    }

    private val _uiState = MutableStateFlow<DetailsScreenState>(DetailsScreenState.Loading)
    val uiState: StateFlow<DetailsScreenState> = _uiState.asStateFlow()

    var description by mutableStateOf("")
        private set

    fun updateDescription(input: String) {
        description = input
    }

    var newTag by mutableStateOf("")
        private set

    fun updateNewTag(input: String) {
        newTag = input
    }

    private suspend fun loadEntry() {
        _uiState.update {
            DetailsScreenState.Loading
        }

        delay(1000)

        val entry = dailyGratitudeRepository.getEntry(entryId.toInt())

        _uiState.update {
            DetailsScreenState.DataLoaded(
                entry
            )
        }
    }

    private fun addNewEntry() {
        _uiState.update {
            DetailsScreenState.EditingData(
                isAddingNewEntry = true,
                entry = EntryCardDetailsModel(
                    id = 0,
                    date = Calendar.getInstance().time,
                    description = "",
                    images = emptyList(),
                    tags = emptyList()
                )
            )
        }
    }

    fun onAddClick(
        onFinishCallBack: () -> Unit
    ) {
        val entry = (_uiState.value as DetailsScreenState.EditingData).entry

        viewModelScope.launch(Dispatchers.IO) {
            dailyGratitudeRepository.insertAll(
                EntryCard(
                    id = entry.id,
                    date = entry.date,
                    description = description,
                    images = entry.images,
                    tags = entry.tags
                )
            )
        }
        // Navigate back
        onFinishCallBack()
    }

    fun startEditing() {
        if (_uiState.value !is DetailsScreenState.DataLoaded) {
            return
        }

        _uiState.update {
            description = (it as DetailsScreenState.DataLoaded).entry.description
            DetailsScreenState.EditingData(
                isAddingNewEntry = false,
                entry = it.entry.copy(date = Calendar.getInstance().time)
            )
        }
    }

    fun addTag(newTag: String) {
        val entry = (_uiState.value as DetailsScreenState.EditingData).entry

        _uiState.update {
            (it as DetailsScreenState.EditingData).copy(
                entry = entry.copy(
                    tags = entry.tags?.plus(
                        listOf(newTag)
                    )
                )
            )
        }
    }

    fun removeTag(index: Int) {
        val entry = (_uiState.value as DetailsScreenState.EditingData).entry

        val tags = entry.tags?.toMutableList()

        tags?.removeAt(index)

        _uiState.update {
            (it as DetailsScreenState.EditingData).copy(
                entry = entry.copy(
                    tags = tags
                )
            )
        }
    }

    fun addImage(imageUri: Uri) {
        val entry = (_uiState.value as DetailsScreenState.EditingData).entry

        _uiState.update {
            (it as DetailsScreenState.EditingData).copy(
                entry = entry.copy(
                    images = entry.images?.plus(
                        listOf(imageUri.toString())
                    )
                )
            )
        }
    }

    fun removeImage(index: Int) {
        val entry = (_uiState.value as DetailsScreenState.EditingData).entry

        val images = entry.images?.toMutableList()

        images?.removeAt(index)

        _uiState.update {
            (it as DetailsScreenState.EditingData).copy(
                entry = entry.copy(
                    images = images
                )
            )
        }
    }

    fun onSaveClick() {
        val entry = (_uiState.value as DetailsScreenState.EditingData).entry

        viewModelScope.launch(Dispatchers.IO) {
            dailyGratitudeRepository.updateEntry(
                entryCard = EntryCard(
                    id = entry.id,
                    date = entry.date,
                    description = description,
                    images = entry.images,
                    tags = entry.tags
                )
            )

            // Reload data
            loadEntry()
        }
    }

    fun onDeleteClick(
        onFinishCallback: () -> Unit,
        showSnackbar: (String) -> Unit
    ) {
        if (_uiState.value !is DetailsScreenState.DataLoaded) {
            showSnackbar("Can't delete while editing")
            return
        }

        val entry = (_uiState.value as DetailsScreenState.DataLoaded).entry

        viewModelScope.launch(Dispatchers.IO) {
            dailyGratitudeRepository.delete(
                entryCard = EntryCard(
                    id = entry.id,
                    date = entry.date,
                    description = description,
                    images = entry.images,
                    tags = entry.tags
                )
            )
        }

        // Navigate back
        onFinishCallback()
    }
}

sealed class DetailsScreenState {
    object Loading : DetailsScreenState()

    data class DataLoaded(
        val entry: EntryCardDetailsModel
    ) : DetailsScreenState()

    data class EditingData(
        val isAddingNewEntry: Boolean,
        val entry: EntryCardDetailsModel
    ) : DetailsScreenState()
}