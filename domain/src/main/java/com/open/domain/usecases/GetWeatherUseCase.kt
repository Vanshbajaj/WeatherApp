package com.open.domain.usecases

import com.open.domain.Weather
import com.open.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor (private val repository: WeatherRepository) {
    suspend operator fun invoke(city: String): Flow<Weather> {
        return repository.getWeather(city)
    }
}

//todo impl will data only
//todo interface repo class will only in domain layer
//todo blueprint for
// di part domain only presentation layer only