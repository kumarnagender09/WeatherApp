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
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class DefaultWeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : WeatherRepository {
    override fun getWeatherForecast(city: String): Flow<Result<WeatherResponse>> = flow {
        emit(Result.Loading)
        try {
            val result = weatherApi.getWeather(city = city)
            Timber.tag("response:: ").i(result.toString())
            emit(Result.Success(result))
        } catch (exception: HttpException) {
            emit(Result.Error(exception.message.orEmpty()))
        } catch (exception: IOException) {
            emit(Result.Error("Please check your network connection and try again!"))
        }
    }.flowOn(dispatcher)
}
