package com.mphasis.weather.data.network

import com.mphasis.weather.BuildConfig
import com.mphasis.weather.data.model.WeatherResponse
import com.mphasis.weather.utils.DEFAULT_WEATHER_DESTINATION
import com.mphasis.weather.utils.NUMBER_OF_DAYS
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getWeather(
        @Query("appid") key: String = BuildConfig.API_KEY,
        @Query("q") city: String = DEFAULT_WEATHER_DESTINATION,
        @Query("days") days: Int = NUMBER_OF_DAYS,
    ): WeatherResponse
}