package com.robert.dailygratitude.ui.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robert.dailygratitude.data.DailyGratitudeRepository
import com.robert.dailygratitude.di.DatabaseRepository
import com.robert.dailygratitude.ui.components.EntryCardModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableMap
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
//    @InMemoryRepository
    @DatabaseRepository
    val dailyGratitudeRepository: DailyGratitudeRepository
) : ViewModel() {

    companion object {
        const val THIS_WEEK = "this_week"
        const val LAST_MONTH = "last_month"
        const val OLDER = "older"
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadEntries()
        }
    }

    private val _uiState = MutableStateFlow<HomeScreenState>(HomeScreenState.Loading)
    val uiState: StateFlow<HomeScreenState> = _uiState.asStateFlow()

    private suspend fun loadEntries() {
        delay(1000)

        val entriesFlow = dailyGratitudeRepository.getEntries()
        entriesFlow.collect { list ->
            val entriesMap = buildEntriesMap(list)

            _uiState.update { HomeScreenState.DataLoaded(entriesMap) }
        }
    }

    private fun buildEntriesMap(list: List<EntryCardModel>): Map<String, List<EntryCardModel>> {
        val entriesMap = mutableMapOf<String, List<EntryCardModel>>(
            THIS_WEEK to mutableListOf(),
            LAST_MONTH to mutableListOf(),
            OLDER to mutableListOf()
        )

        list.forEach {
            if (it.date.after(Calendar.getInstance().apply { add(Calendar.DATE, -7) }.time)) {
                entriesMap[THIS_WEEK] = entriesMap[THIS_WEEK]?.plus(it) ?: mutableListOf()
            } else if (it.date.after(
                    Calendar.getInstance().apply { add(Calendar.MONTH, -1) }.time
                )
            ) {
                entriesMap[LAST_MONTH] = entriesMap[LAST_MONTH]?.plus(it) ?: mutableListOf()
            } else {
                entriesMap[OLDER] = entriesMap[OLDER]?.plus(it) ?: mutableListOf()
            }
        }

        return entriesMap.toImmutableMap()
    }
}

sealed class HomeScreenState {
    object Loading : HomeScreenState()

    data class DataLoaded(
        val data: Map<String, List<EntryCardModel>>
    ) : HomeScreenState()
}