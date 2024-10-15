package com.open.domain.repository


import com.open.domain.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeather(cityName: String): Flow<Weather>
}