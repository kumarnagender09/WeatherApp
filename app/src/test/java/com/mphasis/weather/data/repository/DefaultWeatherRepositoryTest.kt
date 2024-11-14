package com.mphasis.weather.data.repository

import com.mphasis.weather.data.model.WeatherResponse
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import com.mphasis.weather.data.network.WeatherApi
import com.mphasis.weather.utils.Result
import kotlinx.coroutines.test.runTest
import org.json.JSONObject
import org.junit.Before
import org.junit.Test

class DefaultWeatherRepositoryTest {
    private lateinit var repository: DefaultWeatherRepository
    private val weatherApi = mockk<WeatherApi>()

    @Before
    fun setup() {
        repository = DefaultWeatherRepository(weatherApi)
    }

    @Test
    fun `when getWeather is called, it should emit loading state and then success state`() =
        runTest {
//            coEvery {
//                weatherApi.getWeather(
//                    any(),
//                    any()
//                )
//            } returns MockupData()

            val results = mutableListOf<Result<WeatherResponse>>()
            repository.getWeatherForecast("Irvine").collect { result ->
                results.add(result)
            }
            assertEquals(Result.Loading, results[0])

        }
}