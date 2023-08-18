package com.robert.dailygratitude.ui.detailsscreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robert.dailygratitude.data.DailyGratitudeRepository
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
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
//    @InMemoryRepository
    @DatabaseRepository
    val dailyGratitudeRepository: DailyGratitudeRepository
) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadEntry()
        }
    }

    private val entryId: String = checkNotNull(savedStateHandle["entryId"])

    private val _uiState = MutableStateFlow<DetailsScreenState>(DetailsScreenState.Loading)
    val uiState: StateFlow<DetailsScreenState> = _uiState.asStateFlow()

    private suspend fun loadEntry() {
        delay(1000)

        val entry = dailyGratitudeRepository.getEntry(entryId.toInt())

        _uiState.update {
            DetailsScreenState.DataLoaded(
                entry
            )
        }
    }

}

sealed class DetailsScreenState {
    object Loading : DetailsScreenState()

    data class DataLoaded(
        val entry: EntryCardDetailsModel
    ) : DetailsScreenState()
}