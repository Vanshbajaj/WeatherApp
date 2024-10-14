package com.open.data

import com.open.data.network.WeatherApiService
import com.open.data.repositoryImplementation.WeatherRepositoryImpl
import com.open.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideWeatherRepository(
        weatherApiService: WeatherApiService
    ): WeatherRepository {
        return WeatherRepositoryImpl(weatherApiService)
    }
}
