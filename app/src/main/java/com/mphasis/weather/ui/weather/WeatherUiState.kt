package com.mphasis.weather.ui.weather

import com.mphasis.weather.data.model.WeatherResponse

// Data class representing the UI state for the WeatherScreen.
// It holds the necessary information to update the UI based on different states of weather data fetching.

data class WeatherUiState(
    // Holds the weather data response, which will be populated when the weather data is successfully fetched.
    // It can be null initially or in case of an error.
    val weatherResponse: WeatherResponse? = null,

    // A flag indicating whether the data is still being loaded.
    // It is set to true when the data fetch is in progress and false when the data has been fetched or failed.
    val isLoading: Boolean = false,

    // Holds any error message encountered while fetching the weather data.
    // It is an empty string if there is no error.
    val errorMessage: String = "",
)
