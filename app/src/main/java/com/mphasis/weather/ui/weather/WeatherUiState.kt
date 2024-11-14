package com.mphasis.weather.ui.weather

import com.mphasis.weather.data.model.WeatherResponse

data class WeatherUiState(
    val weatherResponse: WeatherResponse? = null,
    val isLoading: Boolean = false,
    val errorMessage: String = "",
)
