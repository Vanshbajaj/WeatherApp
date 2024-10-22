package com.open.domain.usecases

import com.open.domain.Weather
import com.open.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor (private val repository: WeatherRepository) {
    operator fun invoke(city: String): Flow<Weather> {
        return repository.getWeather(city)
    }
}

