package com.mphasis.weather.data.repository

import com.mphasis.weather.data.model.WeatherResponse
import com.mphasis.weather.data.network.WeatherApi
import com.mphasis.weather.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

// Repository implementation for fetching weather data
class DefaultWeatherRepository @Inject constructor(
    // Injected instance of WeatherApi for making network calls
    private val weatherApi: WeatherApi,

    // Injected CoroutineDispatcher, defaulting to IO for background work
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : WeatherRepository {

    // Function to get weather forecast as a Flow, emitting Result states
    override fun getWeatherForecast(city: String): Flow<Result<WeatherResponse>> = flow {
        emit(Result.Loading) // Emit loading state before making the network request
        try {
            val result = weatherApi.getWeather(city = city)
            emit(Result.Success(result)) // Emit success state with the fetched data
        } catch (exception: HttpException) {
            emit(Result.Error(exception.message.orEmpty())) // Emit error for HTTP exceptions
        } catch (exception: IOException) {
            emit(Result.Error("Please check your network connection and try again!")) // Network error handling
        }
    }.flowOn(dispatcher) // Run the flow on the IO dispatcher

    override suspend fun getWeatherByCoordinates(lat: Double, lon: Double): Result<WeatherResponse> {
        return try {
            val response = weatherApi.getWeatherByCoordinates(lat = lat, lon = lon)
            Result.Success(response)
        } catch (e: HttpException) {
            Result.Error("Error: ${e.message}")
        } catch (e: IOException) {
            Result.Error("Please check your network connection and try again!")
        }
    }
}
