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


        _weatherState.value = UiState.Loading

        viewModelScope.launch {
            try {
                getWeatherUseCase(cityName).collect { weather ->
                    _weatherState.emit(UiState.Success(weather))
                }
            } catch (e: Exception) {
                _weatherState.emit(
                    UiState.Error(
                        e.localizedMessage ?: "Unknown error"
                    )
                )
            }
        }
    }
}





