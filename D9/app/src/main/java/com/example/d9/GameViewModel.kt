package com.example.d9

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())

    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    fun increaseScore() {
        _uiState.update { currentState ->
            currentState.copy(score = currentState.score + 1)
        }
    }

    fun updatePlayerName(newName: String) {
        _uiState.update { currentState ->
            currentState.copy(playerName = newName)
        }
    }
}