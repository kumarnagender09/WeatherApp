package com.mphasis.weather.ui.weather.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mphasis.weather.R
import com.mphasis.weather.ui.weather.WeatherUiState
import com.mphasis.weather.ui.weather.WeatherViewModel

@Composable
fun WeatherScreenContent(
    uiState: WeatherUiState,
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel?,
) {
    when {
        uiState.isLoading -> {
            Animation(modifier = Modifier.fillMaxSize(), animation = R.raw.animation_loading)
        }

        uiState.errorMessage.isNotEmpty() -> {
            WeatherErrorState(uiState = uiState, viewModel = viewModel)
        }

        else -> {
            WeatherSuccessState(modifier = modifier, uiState = uiState)
        }
    }
}