package com.mphasis.weather.data.repository

import com.mphasis.weather.data.model.WeatherResponse
import com.mphasis.weather.utils.Result
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeatherForecast(city: String): Flow<Result<WeatherResponse>>
}