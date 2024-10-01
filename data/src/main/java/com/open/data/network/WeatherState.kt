package com.open.data.network

import com.open.data.model.WeatherResponse


sealed class WeatherState {
    object Loading : WeatherState() // Represents the loading state
    data class Success(val data: WeatherResponse) :
        WeatherState() // Represents successful data fetching

    data class Error(val message: String) : WeatherState() //
}