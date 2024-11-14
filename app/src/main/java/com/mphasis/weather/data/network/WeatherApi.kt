package com.mphasis.weather.data.network

import com.mphasis.weather.BuildConfig
import com.mphasis.weather.data.model.WeatherResponse
import com.mphasis.weather.utils.DEFAULT_WEATHER_DESTINATION
import retrofit2.http.GET
import retrofit2.http.Query

// Interface for the Weather API endpoints
interface WeatherApi {
    // Defines a GET request to the "weather" endpoint
    @GET("weather")
    suspend fun getWeather(
        // API key query parameter, defaulting to the value in BuildConfig
        @Query("appid") key: String = BuildConfig.API_KEY,

        // City query parameter, defaulting to a constant destination
        @Query("q") city: String = DEFAULT_WEATHER_DESTINATION,
    ): WeatherResponse

    // Defines a GET request to the "weather" endpoint

    @GET("weather")
    suspend fun getWeatherByCoordinates(
        // API key query parameter, defaulting to the value in BuildConfig
        @Query("appid") key: String = BuildConfig.API_KEY,
        // Lat/long query parameter, defaulting to a constant destination
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
    ): WeatherResponse
}