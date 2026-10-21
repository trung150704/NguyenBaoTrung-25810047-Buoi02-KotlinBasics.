package com.example.d12.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.work.WorkInfo
import com.example.d12.D12Application
import com.example.d12.data.WorkRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn

data class MainUiState(
    val inputText: String = "Đồng bộ dữ liệu người dùng",
    val requiresNetwork: Boolean = true,
    val requiresCharging: Boolean = false
)

class MainViewModel(
    private val workRepository: WorkRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    val oneTimeWorkInfo: StateFlow<WorkInfo?> = workRepository.outputWorkInfo
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val periodicWorkInfo: StateFlow<WorkInfo?> = workRepository.periodicWorkInfo
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    fun updateInputText(text: String) {
        _uiState.value = _uiState.value.copy(inputText = text)
    }

    fun updateRequiresNetwork(required: Boolean) {
        _uiState.value = _uiState.value.copy(requiresNetwork = required)
    }

    fun updateRequiresCharging(required: Boolean) {
        _uiState.value = _uiState.value.copy(requiresCharging = required)
    }

    fun startOneTimeWork() {
        val current = _uiState.value
        if (current.inputText.isBlank()) return
        workRepository.applyWork(
            inputText = current.inputText,
            requiresNetwork = current.requiresNetwork,
            requiresCharging = current.requiresCharging
        )
    }

    fun startPeriodicWork() {
        workRepository.schedulePeriodicWork()
    }

    fun cancelAllWork() {
        workRepository.cancelWork()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as D12Application)
                val workRepository = application.container.workRepository
                MainViewModel(workRepository = workRepository)
            }
        }
    }
}
