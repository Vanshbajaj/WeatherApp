package com.open.data.network

sealed class UiState<out T> {
    object Empty : UiState<Nothing>()  // Represents an initial, empty state
    object Loading : UiState<Nothing>() // Represents the loading state
    data class Success<out T>(val data: T) : UiState<T>() // Represents success with the data
    data class Error(val message: String) : UiState<Nothing>() // Represents error with an error message
}
