package com.open.domain.usecases

import com.open.data.model.WeatherResponse
import com.open.data.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend operator fun invoke(city: String): Flow<WeatherResponse> {
        return repository.fetchWeather(city)
    }
}
