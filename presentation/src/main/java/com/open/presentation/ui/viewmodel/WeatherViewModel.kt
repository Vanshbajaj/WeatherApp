package com.open.presentation.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.open.data.network.UiState
import com.open.domain.Weather
import com.open.domain.usecases.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _weatherState = MutableStateFlow<UiState<Weather>>(UiState.Empty)
    val weatherState: StateFlow<UiState<Weather>> get() = _weatherState

    fun getWeather(cityName: String) {
        if (cityName.isBlank()) {
            return
        }

        // Set the state to loading when the API call is about to be made
        _weatherState.value = UiState.Loading

        viewModelScope.launch {
            try {
                getWeatherUseCase(cityName).collect { weather ->
                    _weatherState.value = UiState.Success(weather) // Update to Success if data is received
                }
            } catch (e: Exception) {
                _weatherState.value = UiState.Error(e.localizedMessage ?: "Unknown error") // Handle error
            }
        }
    }
}





