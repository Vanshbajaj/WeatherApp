package com.open.domain.repository


import com.open.domain.Weather
import dagger.Provides
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeather(cityName: String): Flow<Weather>
}