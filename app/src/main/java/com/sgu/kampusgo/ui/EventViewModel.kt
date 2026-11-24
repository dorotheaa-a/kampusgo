package com.sgu.kampusgo.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sgu.kampusgo.data.EventsUiState
import com.sgu.kampusgo.repository.EventRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EventViewModel(app: Application) : AndroidViewModel(app) {
    private val repository = EventRepository()
    private val _uiState = MutableStateFlow<EventsUiState>(EventsUiState.Loading)
    val uiState: StateFlow<EventsUiState> = _uiState.asStateFlow()
    init {
        refresh()
    }
    fun refresh() {
        viewModelScope.launch {
            _uiState.value = EventsUiState.Loading
            _uiState.value = repository.loadEvents()
        }
    }
}