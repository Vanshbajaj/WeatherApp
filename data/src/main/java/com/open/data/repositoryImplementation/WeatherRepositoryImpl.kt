package com.open.data.repositoryImplementation

import com.open.data.BuildConfig
import com.open.data.network.WeatherApiService
import com.open.domain.Weather
import com.open.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class WeatherRepositoryImpl(private val weatherService: WeatherApiService) : WeatherRepository {
    override fun getWeather(cityName: String): Flow<Weather> {
        return flow {
            val response = weatherService.getWeather(cityName,BuildConfig.API_KEY,"metric")
            emit(
              response
            )
        }
    }
}

//todo for time why to use flow
//todo shared flow for event flows
// why



