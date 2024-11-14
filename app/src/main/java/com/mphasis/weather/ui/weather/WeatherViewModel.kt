package com.mphasis.weather.ui.weather

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mphasis.weather.data.model.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import com.mphasis.weather.data.repository.WeatherRepository
import com.mphasis.weather.utils.DEFAULT_WEATHER_DESTINATION
import com.mphasis.weather.utils.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
// ViewModel for managing the UI state and business logic of the WeatherScreen.
// It interacts with the WeatherRepository to fetch weather data and updates the UI state accordingly.
// The ViewModel ensures that UI-related data survives configuration changes like screen rotations.

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<WeatherUiState> =
        MutableStateFlow(WeatherUiState(isLoading = true))
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> = mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    init {
        getWeather()
    }

    // Fetch weather by city name
    fun getWeather(city: String = DEFAULT_WEATHER_DESTINATION) {
        repository.getWeatherForecast(city).map { result ->
            handleResult(result)
        }.launchIn(viewModelScope)
    }

    // Fetch weather by coordinates
    fun getWeatherByCoordinates(lat: Double, lon: Double) {
        viewModelScope.launch {
            val result = repository.getWeatherByCoordinates(lat, lon)
            handleResult(result)
        }
    }

    // Helper to handle the result and update UI state
    private fun handleResult(result: Result<WeatherResponse>) {
        when (result) {
            is Result.Success -> _uiState.value = WeatherUiState(weatherResponse = result.data)
            is Result.Error -> _uiState.value = WeatherUiState(errorMessage = result.errorMessage)
            Result.Loading -> _uiState.value = WeatherUiState(isLoading = true)
        }
    }
}

