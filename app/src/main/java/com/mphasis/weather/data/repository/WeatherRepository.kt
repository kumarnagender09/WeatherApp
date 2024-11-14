package com.mphasis.weather.data.repository

import com.mphasis.weather.data.model.WeatherResponse
import com.mphasis.weather.utils.Result
import kotlinx.coroutines.flow.Flow

// Interface defining methods for fetching weather data
interface WeatherRepository {

    // Function to get weather forecast for a specific city, returning a Flow of Result types
    fun getWeatherForecast(city: String): Flow<Result<WeatherResponse>>
    // Function to get weather forecast by latitude and longitude, returning a Result of WeatherResponse
    suspend fun getWeatherByCoordinates(lat: Double, lon: Double): Result<WeatherResponse>

}
