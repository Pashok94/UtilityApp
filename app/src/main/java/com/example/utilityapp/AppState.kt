package com.example.utilityapp

import com.example.utilityapp.model.Note

sealed class AppState {
    data class Success(val results: List<Note>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}