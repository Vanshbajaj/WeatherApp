package com.open.data.repository

import com.open.data.BuildConfig
import com.open.data.model.WeatherResponse
import com.open.data.network.WeatherApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiService: WeatherApiService) {
    suspend fun fetchWeather(city: String): Flow<WeatherResponse> = flow {
        val response = apiService.getWeather(city, BuildConfig.API_KEY)
        emit(response)
    }.catch { emit(WeatherResponse()) } // Handle errors appropriately
}
