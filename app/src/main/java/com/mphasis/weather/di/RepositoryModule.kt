package com.mphasis.weather.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.mphasis.weather.data.network.WeatherApi
import com.mphasis.weather.data.repository.DefaultWeatherRepository
import com.mphasis.weather.data.repository.WeatherRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    // Provides a singleton instance of WeatherRepository.
    @Singleton
    @Provides
    fun provideRepository(weatherApi: WeatherApi): WeatherRepository =
        DefaultWeatherRepository(weatherApi)
}
